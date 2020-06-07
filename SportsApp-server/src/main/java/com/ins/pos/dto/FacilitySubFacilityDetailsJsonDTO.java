package com.ins.pos.dto;

import java.util.List;

public class FacilitySubFacilityDetailsJsonDTO {

	private Long facilityId;
	private String facilityName;
	private List<SubFacilityDetailJsonDTO> subFacility;

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

	public List<SubFacilityDetailJsonDTO> getSubFacility() {
		return subFacility;
	}

	public void setSubFacility(List<SubFacilityDetailJsonDTO> subFacility) {
		this.subFacility = subFacility;
	}

}
