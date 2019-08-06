package com.ins.pos.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ins.pos.dto.FacilityJsonDTO;
import com.ins.pos.dto.FacilityTypeJsonDTO;
import com.ins.pos.entity.Booking;
import com.ins.pos.entity.Center;
import com.ins.pos.entity.Facility;
import com.ins.pos.entity.FacilityType;
import com.ins.pos.entity.OnlineBookingWindow;
import com.ins.pos.entity.SubFacility;
import com.ins.pos.entity.TimeTable;
import com.ins.pos.repository.BookingRepository;
import com.ins.pos.repository.CenterRepository;
import com.ins.pos.repository.FacilityRepository;
import com.ins.pos.repository.FacilityTypeRepository;
import com.ins.pos.repository.OnlineBookingWindowRepository;
import com.ins.pos.repository.SubFacilityRepository;
import com.ins.pos.repository.TimeTableRepository;
import com.ins.pos.service.FacilityService;

@Service
public class FacilicyServiceImpl implements FacilityService{

	@Autowired
	private FacilityTypeRepository facilityTypeRepository;
	
	@Autowired
	private FacilityRepository facilityRepository;
	
	@Autowired
	private CenterRepository centerRepository;
	
	@Autowired
	private OnlineBookingWindowRepository onlineBookingWindowRepository;
	
	@Autowired
	SubFacilityRepository subFacilityRepository;
	
	@Autowired
	TimeTableRepository timeTableRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Override
	public List<FacilityTypeJsonDTO> getAllFacilityTypes() {
		List<FacilityTypeJsonDTO> facilityTypeJsonDTOList= new ArrayList<FacilityTypeJsonDTO>();
		for(FacilityType facilityType:facilityTypeRepository.findByActiveAndOnlineActive(true, true)) {
			FacilityTypeJsonDTO facilityTypeJsonDTO = new FacilityTypeJsonDTO();
			BeanUtils.copyProperties(facilityType,facilityTypeJsonDTO );
			facilityTypeJsonDTOList.add(facilityTypeJsonDTO);
		}
		return facilityTypeJsonDTOList;
	}

	@Override
	public List<FacilityJsonDTO> getFacilityForPreferredSport(String data) {
		List<FacilityJsonDTO> facilityJsonDTOList = new ArrayList<FacilityJsonDTO>();
		try {
			JSONObject jsonData = new JSONObject(data);
			String centerId = jsonData.getString("centerId");
			JSONArray facilityTypeIdJSONArray = jsonData.getJSONArray("facilityTypeId");
			Optional<Center> center = centerRepository.findById(Long.parseLong(centerId));
			List<Long> facilityTypeIdArray = new ArrayList<Long>();
			for (Object value : facilityTypeIdJSONArray) {
				facilityTypeIdArray.add(Long.valueOf((String) value));
			}
			Iterable<FacilityType> facilityType = facilityTypeRepository.findAllById(facilityTypeIdArray);
			if (center.isPresent() && facilityType != null) {
				for (FacilityType facType : facilityType) {
					List<Facility> facilityList = facilityRepository
							.findByCenterIdAndFacilityTypeIdAndOnlineActiveAndActive(center.get(), facType, true,true);
					for (Facility facility : facilityList) {
						FacilityJsonDTO facilityJsonDTO = new FacilityJsonDTO();
						BeanUtils.copyProperties(facility, facilityJsonDTO);
						facilityJsonDTOList.add(facilityJsonDTO);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return facilityJsonDTOList;
	}

	@Override
	public String checkFacilityAvailabilty(String data) {
		JSONObject reponseJSON = new JSONObject();
		JSONArray facilityDetails = new JSONArray();
		reponseJSON.put("facility", facilityDetails);
		try {
			JSONObject jsonData = new JSONObject(data);
			JSONArray facilityList = jsonData.getJSONArray("facility");
			for (Object value : facilityList) {
				boolean isSlotAvailable = false;
				JSONObject facilityJSON = new JSONObject();
				facilityDetails.put(facilityJSON);
				facilityJSON.put("facilityId", (String) value);
				Optional<Facility> facility = facilityRepository.findById(Long.valueOf((String) value));
				if (facility.isPresent()) {
					boolean isValid = false;
					OnlineBookingWindow onlineBookingWindow = onlineBookingWindowRepository
							.findByActiveAndFacilityId(true, facility.get());
					Calendar date = Calendar.getInstance();
					Calendar startCalender = Calendar.getInstance();
					Calendar endCalender = Calendar.getInstance();
					if (onlineBookingWindow != null) {
						startCalender.set(Calendar.DAY_OF_MONTH, onlineBookingWindow.getBookingStartDate());
						startCalender.set(Calendar.HOUR_OF_DAY, 0);
						startCalender.set(Calendar.MINUTE, 0);
						startCalender.set(Calendar.SECOND, 0);
						startCalender.set(Calendar.MILLISECOND, 0);
						endCalender.set(Calendar.DAY_OF_MONTH, onlineBookingWindow.getBookingEndDate());
						endCalender.set(Calendar.HOUR_OF_DAY, 23);
						endCalender.set(Calendar.MINUTE, 59);
						endCalender.set(Calendar.SECOND, 59);
						endCalender.set(Calendar.MILLISECOND, 0);
						if(onlineBookingWindow.getBookingStartDate()>onlineBookingWindow.getBookingEndDate()) {
							if(date.get(Calendar.DAY_OF_MONTH)>=onlineBookingWindow.getBookingStartDate()) {
								endCalender.add(Calendar.MONTH, 1);
							} else {
								startCalender.add(Calendar.MONTH, -1);
							}
						
						}
						
						isValid = true;
					}
					if (isValid) {
						if (date.getTime().compareTo(startCalender.getTime()) >= 0
								&& date.getTime().compareTo(endCalender.getTime()) <= 0) {
							isSlotAvailable = isSlotAvailableForFacility(facility.get());
						} else {
							facilityJSON.put("validation","Booking window for the facility - " + facility.get().getFacilityName() + " was between "
									+ startCalender.getTime() + " and " + endCalender.getTime() + "!!!");
						}
					} else {
						isSlotAvailable = isSlotAvailableForFacility(facility.get());
					}
				}
				if(isSlotAvailable) {
					facilityJSON.put("slotAavailable", "Y");
				}
				else {
					facilityJSON.put("slotAavailable", "N");
				}
			}
			
			reponseJSON.put("status", "Success");
		} catch (JSONException e) {
			e.printStackTrace();
			reponseJSON.put("status", "Failure");
		}
		return reponseJSON.toString();

	}

	private boolean isSlotAvailableForFacility(Facility facility) {
		boolean isAvailable = false;
		Date selectedDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(selectedDate);
		int daynum = calendar.get(Calendar.DAY_OF_WEEK);
		List<TimeTable> timeTableList = timeTableRepository.findByActiveAndDayNumAndFacilityId(true, daynum, facility);
		List<SubFacility> subFacilityList = subFacilityRepository.findByFacilityIdAndActiveAndOnlineActive(facility, true,true);
		for(SubFacility subFacility:subFacilityList) {
			for(TimeTable timetable:timeTableList) {
				List<Booking> bookingLst = bookingRepository.findByBookingStartDateGreaterThanEqualAndBookingEndDateLessThanEqualAndSubFacilityIdAndTimeTableId(selectedDate, selectedDate, subFacility,timetable);
				if(bookingLst.size()<subFacility.getSlotLimit()) {
					isAvailable =true;
					break;
				}
			}
			if(isAvailable) {
				break;
			}
		}
		return isAvailable;
	}

}
