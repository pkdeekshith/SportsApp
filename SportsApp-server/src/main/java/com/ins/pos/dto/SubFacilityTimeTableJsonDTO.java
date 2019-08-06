package com.ins.pos.dto;

import java.util.List;

public class SubFacilityTimeTableJsonDTO {
	
	private Long subFacilityId;
	
	private Double rateMonthly;
	
	private String subFacilityName;
	
	private List<TimeTableJsonDTO> timetable;

	public Long getSubFacilityId() {
		return subFacilityId;
	}

	public void setSubFacilityId(Long subFacilityId) {
		this.subFacilityId = subFacilityId;
	}

	public Double getRateMonthly() {
		return rateMonthly;
	}

	public void setRateMonthly(Double rateMonthly) {
		this.rateMonthly = rateMonthly;
	}

	public String getSubFacilityName() {
		return subFacilityName;
	}

	public void setSubFacilityName(String subFacilityName) {
		this.subFacilityName = subFacilityName;
	}

	public List<TimeTableJsonDTO> getTimetable() {
		return timetable;
	}

	public void setTimetable(List<TimeTableJsonDTO> timetable) {
		this.timetable = timetable;
	}
		
}
