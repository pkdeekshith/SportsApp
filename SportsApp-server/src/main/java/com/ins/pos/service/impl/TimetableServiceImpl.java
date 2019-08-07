package com.ins.pos.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ins.pos.dto.FacilitySubFacilityTimeTableJsonDTO;
import com.ins.pos.dto.SubFacilityTimeTableJsonDTO;
import com.ins.pos.dto.TimeTableJsonDTO;
import com.ins.pos.entity.Facility;
import com.ins.pos.entity.Price;
import com.ins.pos.entity.SubFacility;
import com.ins.pos.entity.TimeTable;
import com.ins.pos.repository.FacilityRepository;
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

	@Override
	public List<FacilitySubFacilityTimeTableJsonDTO> getAvailableTimeTables(String data) {
		List<FacilitySubFacilityTimeTableJsonDTO> facilitySubFacilityTimeTableJsonDTOList = new ArrayList<FacilitySubFacilityTimeTableJsonDTO>();
		
		try {
			JSONObject requestJSON = new JSONObject(data);
			JSONArray facilityJSONArray = requestJSON.getJSONArray("facility");
			Date selectedDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(selectedDate);
			int daynum = calendar.get(Calendar.DAY_OF_WEEK);
			for (Object key : facilityJSONArray) {
				Long facilityId = Long.parseLong((String) key);
				Optional<Facility> facilityOpt = facilityRepository.findById(facilityId);
				if (facilityOpt.isPresent()) {
					FacilitySubFacilityTimeTableJsonDTO facilitySubFacilityTimeTableJsonDTO = new FacilitySubFacilityTimeTableJsonDTO();
					List<TimeTable> timeTableList = timeTableRepository.findByActiveAndDayNumAndFacilityId(true, daynum,
							facilityOpt.get());
					List<TimeTableJsonDTO> timeTableJsonArray = new ArrayList<TimeTableJsonDTO>();
					for(TimeTable timeTable:timeTableList) {
						TimeTableJsonDTO timeTableJsonDTO = new TimeTableJsonDTO();
						BeanUtils.copyProperties(timeTable, timeTableJsonDTO);
						timeTableJsonArray.add(timeTableJsonDTO);
					}
					
					BeanUtils.copyProperties(facilityOpt.get(), facilitySubFacilityTimeTableJsonDTO);
					List<SubFacility> subFacilityList = subFacilityRepository.findByFacilityIdAndActiveAndOnlineActive(facilityOpt.get(), true,true);
					List<SubFacilityTimeTableJsonDTO> subFacilityTimeTableJsonDTOList = new ArrayList<SubFacilityTimeTableJsonDTO>();
					for(SubFacility subFacility:subFacilityList) {
						List<Price> priceList = priceRepository.findByActiveAndSubFacilityId(true, subFacility);
						SubFacilityTimeTableJsonDTO subFacilityTimeTableJsonDTO = new SubFacilityTimeTableJsonDTO();
						BeanUtils.copyProperties(subFacility, subFacilityTimeTableJsonDTO);
						subFacilityTimeTableJsonDTO.setTimetable(timeTableJsonArray);
						if(priceList!=null&&!priceList.isEmpty()) {
							subFacilityTimeTableJsonDTO.setRateMonthly(priceList.get(0).getRatePerMonth());
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
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			int daynum = calendar.get(Calendar.DAY_OF_WEEK);
			for (Object key : subFacilityJSONArray) {
				Long subFacilityId = Long.parseLong((String) key);
				Optional<SubFacility> subFacilityOpt = subFacilityRepository.findById(subFacilityId);
				if (subFacilityOpt.isPresent()) {
					SubFacilityTimeTableJsonDTO subFacilityTimeTableJsonDTO = new SubFacilityTimeTableJsonDTO();
					List<TimeTable> timeTableList = timeTableRepository.findByActiveAndDayNumAndFacilityId(true, daynum,
							subFacilityOpt.get().getFacilityId());
					List<TimeTableJsonDTO> timeTableJsonArray = new ArrayList<TimeTableJsonDTO>();
					for (TimeTable timeTable : timeTableList) {
						TimeTableJsonDTO timeTableJsonDTO = new TimeTableJsonDTO();
						BeanUtils.copyProperties(timeTable, timeTableJsonDTO);
						timeTableJsonArray.add(timeTableJsonDTO);
					}
					BeanUtils.copyProperties(subFacilityOpt.get(), subFacilityTimeTableJsonDTO);
					List<Price> priceList = priceRepository.findByActiveAndSubFacilityId(true, subFacilityOpt.get());
					subFacilityTimeTableJsonDTO.setTimetable(timeTableJsonArray);
					if (priceList != null && !priceList.isEmpty()) {
						subFacilityTimeTableJsonDTO.setRateMonthly(priceList.get(0).getRatePerMonth());
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
