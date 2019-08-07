package com.ins.pos.dto;

import java.util.List;

public class FacilitySubFacilityJsonDTO {

	private Long facilityId;
	private String facilityName;
	private List<SubFacilityJsonDTO> subFacility;

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

	public List<SubFacilityJsonDTO> getSubFacility() {
		return subFacility;
	}

	public void setSubFacility(List<SubFacilityJsonDTO> subFacility) {
		this.subFacility = subFacility;
	}

}
