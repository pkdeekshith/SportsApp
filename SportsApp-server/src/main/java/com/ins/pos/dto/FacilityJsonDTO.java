package com.ins.pos.dto;

public class FacilityJsonDTO {
	private Long facilityId;
	private String facilityName;
	private String facilityPhoto;
	private Double rateMonthly;
	private String centerName;
	private Long centreId;;
	

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

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public Long getCentreId() {
		return centreId;
	}

	public void setCentreId(Long centreId) {
		this.centreId = centreId;
	}
}
