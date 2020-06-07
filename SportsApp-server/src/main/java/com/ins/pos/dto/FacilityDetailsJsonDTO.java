package com.ins.pos.dto;

public class FacilityDetailsJsonDTO {
	private Long facilityId;
	private String facilityName;
	private Boolean onlineActive;

	private Boolean active;

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

	public Boolean getOnlineActive() {
		return onlineActive;
	}

	public void setOnlineActive(Boolean onlineActive) {
		this.onlineActive = onlineActive;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
