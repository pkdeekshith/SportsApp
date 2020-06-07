package com.ins.pos.dto;

public class SubFacilityDetailJsonDTO {

	private Long subFacilityId;

	private String subFacilityName;

	private Boolean active;

	private Boolean onlineActive;

	public Long getSubFacilityId() {
		return subFacilityId;
	}

	public void setSubFacilityId(Long subFacilityId) {
		this.subFacilityId = subFacilityId;
	}

	public String getSubFacilityName() {
		return subFacilityName;
	}

	public void setSubFacilityName(String subFacilityName) {
		this.subFacilityName = subFacilityName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getOnlineActive() {
		return onlineActive;
	}

	public void setOnlineActive(Boolean onlineActive) {
		this.onlineActive = onlineActive;
	}

}
