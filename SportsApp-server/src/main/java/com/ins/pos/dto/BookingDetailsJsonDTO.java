package com.ins.pos.dto;

import java.util.List;

public class BookingDetailsJsonDTO {

	private Long bookingId;

	private Long memberId;

	private Boolean active;

	private Long centerId;

	private FacilitySubFacilityTimeTableJsonDTO facility;

	private List<MemberDetailsJsonDTO> otherMembers;

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

	public FacilitySubFacilityTimeTableJsonDTO getFacility() {
		return facility;
	}

	public void setFacility(FacilitySubFacilityTimeTableJsonDTO facility) {
		this.facility = facility;
	}

	public List<MemberDetailsJsonDTO> getOtherMembers() {
		return otherMembers;
	}

	public void setOtherMembers(List<MemberDetailsJsonDTO> otherMembers) {
		this.otherMembers = otherMembers;
	}
}
