package com.ins.pos.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ins.pos.dto.FacilitySubFacilityTimeTableJsonDTO;
import com.ins.pos.dto.SubFacilityTimeTableJsonDTO;
import com.ins.pos.dto.TimeTableJsonDTO;
import com.ins.pos.entity.AccountsSubSector;
import com.ins.pos.entity.Facility;
import com.ins.pos.entity.OnlineBookingWindow;
import com.ins.pos.entity.Price;
import com.ins.pos.entity.SubFacility;
import com.ins.pos.entity.TimeTable;
import com.ins.pos.repository.AccountsSubSectorRepository;
import com.ins.pos.repository.FacilityRepository;
import com.ins.pos.repository.OnlineBookingWindowRepository;
import com.ins.pos.repository.PriceRepository;
import com.ins.pos.repository.SubFacilityRepository;
import com.ins.pos.repository.TimeTableRepository;
import com.ins.pos.service.TimetableService;

@Service
public class TimetableServiceImpl implements TimetableService {
	
	@Autowired
	TimeTableRepository timeTableRepository;
	
	@Autowired
	FacilityRepository facilityRepository;
	
	@Autowired
	SubFacilityRepository subFacilityRepository;
	
	@Autowired
	PriceRepository priceRepository;
	
	@Autowired
	private AccountsSubSectorRepository accountsSubSectorRepository;
	
	@Autowired
	private OnlineBookingWindowRepository onlineBookingWindowRepository;
	
	@Value("${sportsapp.facility.photos.path}")
	private String facilityPhotoPath;
	
	@Value("${sportsapp.subfacility.photos.path}")
	private String subFacilityPhotoPath;

	@Override
	public List<FacilitySubFacilityTimeTableJsonDTO> getAvailableTimeTables(String data) {
		List<FacilitySubFacilityTimeTableJsonDTO> facilitySubFacilityTimeTableJsonDTOList = new ArrayList<FacilitySubFacilityTimeTableJsonDTO>();
		
		try {
			JSONObject requestJSON = new JSONObject(data);
			JSONArray facilityJSONArray = requestJSON.getJSONArray("facility");
			
			for (Object key : facilityJSONArray) {
				Long facilityId = Long.parseLong((String) key);
				Optional<Facility> facilityOpt = facilityRepository.findById(facilityId);
				if (facilityOpt.isPresent()) {
					OnlineBookingWindow onlineBookingWindow = onlineBookingWindowRepository.findByActiveAndFacilityId(true,
							facilityOpt.get());
					
					Calendar bookedDate = Calendar.getInstance();
					Calendar bookingStartDate = Calendar.getInstance();
					Calendar bookingEndDate = Calendar.getInstance();

					if (onlineBookingWindow != null) {
						if (onlineBookingWindow.getBookingStartDate() > onlineBookingWindow.getBookingEndDate()) {
							if (bookedDate.get(Calendar.DAY_OF_MONTH) >= onlineBookingWindow.getBookingStartDate()) {
								bookingStartDate.add(Calendar.MONTH, 1);
								bookingStartDate.set(Calendar.DAY_OF_MONTH, 1);
								bookingStartDate.set(Calendar.HOUR_OF_DAY, 0);
								bookingStartDate.set(Calendar.MINUTE, 0);
								bookingStartDate.set(Calendar.SECOND, 0);
								bookingStartDate.set(Calendar.MILLISECOND, 0);
								bookingEndDate.add(Calendar.MONTH, 2);
								bookingEndDate.set(Calendar.DAY_OF_MONTH, 4);
								bookingEndDate.set(Calendar.HOUR_OF_DAY, 23);
								bookingEndDate.set(Calendar.MINUTE, 59);
								bookingEndDate.set(Calendar.SECOND, 59);
								bookingEndDate.set(Calendar.MILLISECOND, 0);
							} else {
								bookingStartDate.set(Calendar.HOUR_OF_DAY, 0);
								bookingStartDate.set(Calendar.MINUTE, 0);
								bookingStartDate.set(Calendar.SECOND, 0);
								bookingStartDate.set(Calendar.MILLISECOND, 0);
								bookingEndDate.add(Calendar.MONTH, 1);
								bookingEndDate.set(Calendar.DAY_OF_MONTH, 4);
								bookingEndDate.set(Calendar.HOUR_OF_DAY, 23);
								bookingEndDate.set(Calendar.MINUTE, 59);
								bookingEndDate.set(Calendar.SECOND, 59);
								bookingEndDate.set(Calendar.MILLISECOND, 0);
							}
						} else {
							bookingStartDate.set(Calendar.HOUR_OF_DAY, 0);
							bookingStartDate.set(Calendar.MINUTE, 0);
							bookingStartDate.set(Calendar.SECOND, 0);
							bookingStartDate.set(Calendar.MILLISECOND, 0);
							bookingEndDate.add(Calendar.MONTH, 1);
							bookingEndDate.set(Calendar.DAY_OF_MONTH, 4);
							bookingEndDate.set(Calendar.HOUR_OF_DAY, 23);
							bookingEndDate.set(Calendar.MINUTE, 59);
							bookingEndDate.set(Calendar.SECOND, 59);
							bookingEndDate.set(Calendar.MILLISECOND, 0);
						}

					} else {
						throw new Exception("No booking window is defined for the facility");

					}
					int daynum = bookingStartDate.get(Calendar.DAY_OF_WEEK);
					FacilitySubFacilityTimeTableJsonDTO facilitySubFacilityTimeTableJsonDTO = new FacilitySubFacilityTimeTableJsonDTO();
					List<TimeTable> timeTableList = timeTableRepository.findByActiveAndDayNumAndFacilityId(true, daynum,
							facilityOpt.get());
										
					BeanUtils.copyProperties(facilityOpt.get(), facilitySubFacilityTimeTableJsonDTO);
					try {
						File file = new File(facilityPhotoPath+facilityOpt.get().getFacilityId().toString()+".png");
						if(!file.exists()) {
							file = new File(facilityPhotoPath+"noimage.png");
						}
						byte[] fileContent = FileUtils.readFileToByteArray(file);
						String encodedString = Base64.getEncoder().encodeToString(fileContent);
						facilitySubFacilityTimeTableJsonDTO.setFacilityPhoto(encodedString);
					}catch(Exception e) {
						e.printStackTrace();
					}
					
					
					
					List<SubFacility> subFacilityList = subFacilityRepository.findByFacilityIdAndActiveAndOnlineActive(facilityOpt.get(), true,true);
					List<SubFacilityTimeTableJsonDTO> subFacilityTimeTableJsonDTOList = new ArrayList<SubFacilityTimeTableJsonDTO>();
					for(SubFacility subFacility:subFacilityList) {
						List<TimeTableJsonDTO> timeTableJsonArray = new ArrayList<TimeTableJsonDTO>();
						for(TimeTable timeTable:timeTableList) {
							List<AccountsSubSector> accountSubSectorList = accountsSubSectorRepository.findActiveBookingForSubFacility(bookingStartDate.getTime(), bookingEndDate.getTime(), timeTable.getSessionStartTime(), timeTable.getSessionEndTime(), "Monthly", true, subFacility);
							if(accountSubSectorList.size()<subFacility.getSlotLimit()) {
								TimeTableJsonDTO timeTableJsonDTO = new TimeTableJsonDTO();
								BeanUtils.copyProperties(timeTable, timeTableJsonDTO);
								timeTableJsonDTO.setTotalSlots(subFacility.getSlotLimit());
								timeTableJsonDTO.setBookedSlots(accountSubSectorList.size());
								timeTableJsonArray.add(timeTableJsonDTO);
							}
						}
						List<Price> priceList = priceRepository.findByActiveAndSubFacilityId(true, subFacility);
						SubFacilityTimeTableJsonDTO subFacilityTimeTableJsonDTO = new SubFacilityTimeTableJsonDTO();
						BeanUtils.copyProperties(subFacility, subFacilityTimeTableJsonDTO);
						subFacilityTimeTableJsonDTO.setTimetable(timeTableJsonArray);
						subFacilityTimeTableJsonDTO.setBookingStartDate(bookingStartDate.getTime());
						subFacilityTimeTableJsonDTO.setBookingEndDate(bookingEndDate.getTime());
						if(priceList!=null&&!priceList.isEmpty()) {
							subFacilityTimeTableJsonDTO.setRateMonthly(priceList.get(0).getRatePerMonth());
						}
						
						try {
							File file = new File(subFacilityPhotoPath+subFacility.getSubFacilityId().toString()+".png");
							if(!file.exists()) {
								file = new File(subFacilityPhotoPath+"noimage.png");
							}
							byte[] fileContent = FileUtils.readFileToByteArray(file);
							String encodedString = Base64.getEncoder().encodeToString(fileContent);
							subFacilityTimeTableJsonDTO.setSubFacilityPhoto(encodedString);
						}catch(Exception e) {
							e.printStackTrace();
						}
						
						subFacilityTimeTableJsonDTOList.add(subFacilityTimeTableJsonDTO);
					}
					facilitySubFacilityTimeTableJsonDTO.setSubFacility(subFacilityTimeTableJsonDTOList);
					facilitySubFacilityTimeTableJsonDTOList.add(facilitySubFacilityTimeTableJsonDTO);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return facilitySubFacilityTimeTableJsonDTOList;
	}

	@Override
	public List<SubFacilityTimeTableJsonDTO> getTimeSlotsForSubFacility(String data) {
		List<SubFacilityTimeTableJsonDTO> subFacilityTimeTableJsonDTOList = new ArrayList<SubFacilityTimeTableJsonDTO>();
		try {
			JSONObject requestJSON = new JSONObject(data);
			JSONArray subFacilityJSONArray = requestJSON.getJSONArray("subFacility");
			for (Object key : subFacilityJSONArray) {
				Long subFacilityId = Long.parseLong((String) key);
				Optional<SubFacility> subFacilityOpt = subFacilityRepository.findById(subFacilityId);
				if (subFacilityOpt.isPresent()) {
					
					OnlineBookingWindow onlineBookingWindow = onlineBookingWindowRepository.findByActiveAndFacilityId(true,
							subFacilityOpt.get().getFacilityId());
					
					Calendar bookedDate = Calendar.getInstance();
					Calendar bookingStartDate = Calendar.getInstance();
					Calendar bookingEndDate = Calendar.getInstance();

					if (onlineBookingWindow != null) {
						if (onlineBookingWindow.getBookingStartDate() > onlineBookingWindow.getBookingEndDate()) {
							if (bookedDate.get(Calendar.DAY_OF_MONTH) >= onlineBookingWindow.getBookingStartDate()) {
								bookingStartDate.add(Calendar.MONTH, 1);
								bookingStartDate.set(Calendar.DAY_OF_MONTH, 1);
								bookingStartDate.set(Calendar.HOUR_OF_DAY, 0);
								bookingStartDate.set(Calendar.MINUTE, 0);
								bookingStartDate.set(Calendar.SECOND, 0);
								bookingStartDate.set(Calendar.MILLISECOND, 0);
								bookingEndDate.add(Calendar.MONTH, 2);
								bookingEndDate.set(Calendar.DAY_OF_MONTH, 4);
								bookingEndDate.set(Calendar.HOUR_OF_DAY, 23);
								bookingEndDate.set(Calendar.MINUTE, 59);
								bookingEndDate.set(Calendar.SECOND, 59);
								bookingEndDate.set(Calendar.MILLISECOND, 0);
							} else {
								bookingStartDate.set(Calendar.HOUR_OF_DAY, 0);
								bookingStartDate.set(Calendar.MINUTE, 0);
								bookingStartDate.set(Calendar.SECOND, 0);
								bookingStartDate.set(Calendar.MILLISECOND, 0);
								bookingEndDate.add(Calendar.MONTH, 1);
								bookingEndDate.set(Calendar.DAY_OF_MONTH, 4);
								bookingEndDate.set(Calendar.HOUR_OF_DAY, 23);
								bookingEndDate.set(Calendar.MINUTE, 59);
								bookingEndDate.set(Calendar.SECOND, 59);
								bookingEndDate.set(Calendar.MILLISECOND, 0);
							}
						} else {
							bookingStartDate.set(Calendar.HOUR_OF_DAY, 0);
							bookingStartDate.set(Calendar.MINUTE, 0);
							bookingStartDate.set(Calendar.SECOND, 0);
							bookingStartDate.set(Calendar.MILLISECOND, 0);
							bookingEndDate.add(Calendar.MONTH, 1);
							bookingEndDate.set(Calendar.DAY_OF_MONTH, 4);
							bookingEndDate.set(Calendar.HOUR_OF_DAY, 23);
							bookingEndDate.set(Calendar.MINUTE, 59);
							bookingEndDate.set(Calendar.SECOND, 59);
							bookingEndDate.set(Calendar.MILLISECOND, 0);
						}

					} else {
						throw new Exception("No booking window is defined for the facility");

					}
					int daynum = bookingStartDate.get(Calendar.DAY_OF_WEEK);
					
					SubFacilityTimeTableJsonDTO subFacilityTimeTableJsonDTO = new SubFacilityTimeTableJsonDTO();
					List<TimeTable> timeTableList = timeTableRepository.findByActiveAndDayNumAndFacilityId(true, daynum,
							subFacilityOpt.get().getFacilityId());
					List<TimeTableJsonDTO> timeTableJsonArray = new ArrayList<TimeTableJsonDTO>();
					for (TimeTable timeTable : timeTableList) {
						List<AccountsSubSector> accountSubSectorList = accountsSubSectorRepository.findActiveBookingForSubFacility(bookingStartDate.getTime(), bookingEndDate.getTime(), timeTable.getSessionStartTime(), timeTable.getSessionEndTime(), "Monthly", true, subFacilityOpt.get());
						if(accountSubSectorList.size()<subFacilityOpt.get().getSlotLimit()) {
						TimeTableJsonDTO timeTableJsonDTO = new TimeTableJsonDTO();
						timeTableJsonDTO.setTotalSlots(subFacilityOpt.get().getSlotLimit());
						timeTableJsonDTO.setBookedSlots(accountSubSectorList.size());
						BeanUtils.copyProperties(timeTable, timeTableJsonDTO);
						timeTableJsonArray.add(timeTableJsonDTO);
						}
					}
					BeanUtils.copyProperties(subFacilityOpt.get(), subFacilityTimeTableJsonDTO);
					List<Price> priceList = priceRepository.findByActiveAndSubFacilityId(true, subFacilityOpt.get());
					subFacilityTimeTableJsonDTO.setBookingStartDate(bookingStartDate.getTime());
					subFacilityTimeTableJsonDTO.setBookingEndDate(bookingEndDate.getTime());
					subFacilityTimeTableJsonDTO.setTimetable(timeTableJsonArray);
					if (priceList != null && !priceList.isEmpty()) {
						subFacilityTimeTableJsonDTO.setRateMonthly(priceList.get(0).getRatePerMonth());
					}
					
					try {
						File file = new File(subFacilityPhotoPath+subFacilityOpt.get().getSubFacilityId().toString()+".png");
						if(!file.exists()) {
							file = new File(subFacilityPhotoPath+"noimage.png");
						}
						byte[] fileContent = FileUtils.readFileToByteArray(file);
						String encodedString = Base64.getEncoder().encodeToString(fileContent);
						subFacilityTimeTableJsonDTO.setSubFacilityPhoto(encodedString);
					}catch(Exception e) {
						e.printStackTrace();
					}
					
					subFacilityTimeTableJsonDTOList.add(subFacilityTimeTableJsonDTO);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return subFacilityTimeTableJsonDTOList;
	}
	

}
