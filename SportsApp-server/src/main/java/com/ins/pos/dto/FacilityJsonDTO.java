package com.ins.pos.dto;

public class FacilityJsonDTO {
	private Long facilityId;
	private String facilityName;
	private String facilityPhoto;
	private Double rateMonthly;
	

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

	public String getFacilityPhoto() {
		return facilityPhoto;
	}

	public void setFacilityPhoto(String facilityPhoto) {
		this.facilityPhoto = facilityPhoto;
	}

	public Double getRateMonthly() {
		return rateMonthly;
	}

	public void setRateMonthly(Double rateMonthly) {
		this.rateMonthly = rateMonthly;
	}
}
