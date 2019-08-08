package com.ins.pos.dto;

public class TimeTableJsonDTO {
	
	private Long timeTableId;
	
	private int sessionStartTime;
		
	private int sessionEndTime;
	
	private int totalSlots;
	
	private int bookedSlots;

	public Long getTimeTableId() {
		return timeTableId;
	}

	public void setTimeTableId(Long timeTableId) {
		this.timeTableId = timeTableId;
	}

	public int getSessionStartTime() {
		return sessionStartTime;
	}

	public void setSessionStartTime(int sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}

	public int getSessionEndTime() {
		return sessionEndTime;
	}

	public void setSessionEndTime(int sessionEndTime) {
		this.sessionEndTime = sessionEndTime;
	}

	public int getTotalSlots() {
		return totalSlots;
	}

	public void setTotalSlots(int totalAvailableSlots) {
		this.totalSlots = totalAvailableSlots;
	}

	public int getBookedSlots() {
		return bookedSlots;
	}

	public void setBookedSlots(int bookedSlots) {
		this.bookedSlots = bookedSlots;
	}

}
