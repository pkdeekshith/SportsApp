package com.ins.pos.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SubFacilityTimeTableJsonDTO {
	
	private Long subFacilityId;
	
	private Double rateMonthly;
	
	private String subFacilityName;
	
	@JsonFormat(pattern = "dd MMM yyyy")
	private Date bookingStartDate;
	@JsonFormat(pattern = "dd MMM yyyy")
	private Date bookingEndDate;
	
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

	public Date getBookingStartDate() {
		return bookingStartDate;
	}

	public void setBookingStartDate(Date bookingStartDate) {
		this.bookingStartDate = bookingStartDate;
	}

	public Date getBookingEndDate() {
		return bookingEndDate;
	}

	public void setBookingEndDate(Date bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}
		
}
