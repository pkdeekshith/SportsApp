package com.ins.pos.dto;

import java.util.List;

public class FacilitySubFacilityTimeTableJsonDTO {

	private Long facilityId;
	private String facilityName;
	private List<SubFacilityTimeTableJsonDTO> subFacility;

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

	public List<SubFacilityTimeTableJsonDTO> getSubFacility() {
		return subFacility;
	}

	public void setSubFacility(List<SubFacilityTimeTableJsonDTO> subFacility) {
		this.subFacility = subFacility;
	}

}
