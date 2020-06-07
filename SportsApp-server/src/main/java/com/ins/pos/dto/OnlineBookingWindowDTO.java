package com.ins.pos.dto;

public class OnlineBookingWindowDTO {

	private int bookingStartDate;

	private int bookingEndDate;

	private Boolean active;

	private Long facilityId;

	private String facilityName;

	public int getBookingStartDate() {
		return bookingStartDate;
	}

	public void setBookingStartDate(int bookingStartDate) {
		this.bookingStartDate = bookingStartDate;
	}

	public int getBookingEndDate() {
		return bookingEndDate;
	}

	public void setBookingEndDate(int bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}

	public String getFacilityName() {
		return facilityName;
	}

	public void setFacilityName(String facilityName) {
		this.facilityName = facilityName;
	}

}
