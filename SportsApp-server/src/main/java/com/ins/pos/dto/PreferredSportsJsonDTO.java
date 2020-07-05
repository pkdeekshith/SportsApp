package com.ins.pos.dto;

import java.util.List;

public class PreferredSportsJsonDTO {

	private Long facilityTypeId;

	private String facilityTypeName;
	
	private List<FacilityJsonDTO> facilities;

	public Long getFacilityTypeId() {
		return facilityTypeId;
	}

	public void setFacilityTypeId(Long facilityTypeId) {
		this.facilityTypeId = facilityTypeId;
	}

	public String getFacilityTypeName() {
		return facilityTypeName;
	}

	public void setFacilityTypeName(String facilityTypeName) {
		this.facilityTypeName = facilityTypeName;
	}

	public List<FacilityJsonDTO> getFacilities() {
		return facilities;
	}

	public void setFacilities(List<FacilityJsonDTO> facilities) {
		this.facilities = facilities;
	}
}
