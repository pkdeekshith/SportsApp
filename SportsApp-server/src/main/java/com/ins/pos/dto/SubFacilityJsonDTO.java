package com.ins.pos.dto;


public class SubFacilityJsonDTO {
	
	private Long subFacilityId;
	
	private Double rateMonthly;
	
	private String subFacilityName;
	
	public Long getSubFacilityId() {
		return subFacilityId;
	}

	public void setSubFacilityId(Long subFacilityId) {
		this.subFacilityId = subFacilityId;
	}

	public Double getRateMonthly() {
		return rateMonthly;
	}

	public void setRateMonthly(Double rateMonthly) {
		this.rateMonthly = rateMonthly;
	}

	public String getSubFacilityName() {
		return subFacilityName;
	}

	public void setSubFacilityName(String subFacilityName) {
		this.subFacilityName = subFacilityName;
	}

}
