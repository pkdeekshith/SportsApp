package com.ins.pos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Price {
	
	@Id
	@GeneratedValue
	@Column
	private Long priceId;
	
	@Column
	private Double ratePerHour;
	
	@Column
	private Double ratePerDay;
	
	@Column
	private Double ratePerWeek;
	
	@Column
	private Double ratePerMonth;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "masterTypeId", nullable = true)
	private MasterType masterTypeId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "centreId", nullable = true)
	private Center  centreId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "facilityId", nullable = true)
	private Facility facilityId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subFacilityId", nullable = false)
	private SubFacility subFacilityId;
	
	
	@Column
	private Boolean active;


	public Long getPriceId() {
		return priceId;
	}


	public void setPriceId(Long priceId) {
		this.priceId = priceId;
	}


	public Double getRatePerHour() {
		return ratePerHour;
	}


	public void setRatePerHour(Double ratePerHour) {
		this.ratePerHour = ratePerHour;
	}


	public Double getRatePerDay() {
		return ratePerDay;
	}


	public void setRatePerDay(Double ratePerDay) {
		this.ratePerDay = ratePerDay;
	}


	public Double getRatePerWeek() {
		return ratePerWeek;
	}


	public void setRatePerWeek(Double ratePerWeek) {
		this.ratePerWeek = ratePerWeek;
	}


	public Double getRatePerMonth() {
		return ratePerMonth;
	}


	public void setRatePerMonth(Double ratePerMonth) {
		this.ratePerMonth = ratePerMonth;
	}


	public MasterType getMasterTypeId() {
		return masterTypeId;
	}


	public void setMasterTypeId(MasterType masterTypeId) {
		this.masterTypeId = masterTypeId;
	}


	public Center getCentreId() {
		return centreId;
	}


	public void setCentreId(Center centreId) {
		this.centreId = centreId;
	}


	public Facility getFacilityId() {
		return facilityId;
	}


	public void setFacilityId(Facility facilityId) {
		this.facilityId = facilityId;
	}


	public SubFacility getSubFacilityId() {
		return subFacilityId;
	}


	public void setSubFacilityId(SubFacility subFacilityId) {
		this.subFacilityId = subFacilityId;
	}


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	
	

}
