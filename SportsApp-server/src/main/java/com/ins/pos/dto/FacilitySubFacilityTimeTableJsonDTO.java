package com.ins.pos.dto;

import java.util.List;

public class FacilitySubFacilityTimeTableJsonDTO {

	private Long facilityId;
	private String facilityName;
	private String facilityPhoto;
	private String centerName;
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

	public String getFacilityPhoto() {
		return facilityPhoto;
	}

	public void setFacilityPhoto(String facilityPhoto) {
		this.facilityPhoto = facilityPhoto;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
}
