package com.ins.pos.dto;

import java.util.List;

public class BookingJsonDTO {

	private Long bookingId;

	private Long memberId;

	private Boolean active;

	private Long centerId;

	private Long facilityId;
	
	private Long subFacilityId;

	private List<Long> timeTableId;
	
	private List<Long> otherMemberId;

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getCenterId() {
		return centerId;
	}

	public void setCenterId(Long centerId) {
		this.centerId = centerId;
	}

	public Long getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Long facilityId) {
		this.facilityId = facilityId;
	}

	public Long getSubFacilityId() {
		return subFacilityId;
	}

	public void setSubFacilityId(Long subFacilityId) {
		this.subFacilityId = subFacilityId;
	}

	public List<Long> getTimeTableId() {
		return timeTableId;
	}

	public void setTimeTableId(List<Long> timeTableId) {
		this.timeTableId = timeTableId;
	}

	public List<Long> getOtherMemberId() {
		return otherMemberId;
	}

	public void setOtherMemberId(List<Long> otherMemberId) {
		this.otherMemberId = otherMemberId;
	}

}
