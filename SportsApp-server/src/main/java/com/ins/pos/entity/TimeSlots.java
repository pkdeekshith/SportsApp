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
public class TimeSlots {
	
	@Id
	@GeneratedValue
	@Column
	private Long timeSlotsId;
	@Column
	private String sessionStartTime;
	@Column
	private String sessionCloseTime;
	
	@Column
	private int dayNum;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "facilityId", nullable = false)
	private Facility facilityId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subFacilityId", nullable = false)
	private SubFacility subFacilityId;

	
	public SubFacility getSubFacilityId() {
		return subFacilityId;
	}

	public void setSubFacilityId(SubFacility subFacilityId) {
		this.subFacilityId = subFacilityId;
	}

	@Column
	private Boolean isActive;
	
	@Column
	private Boolean active;

	public Long getTimeSlotsId() {
		return timeSlotsId;
	}

	public void setTimeSlotsId(Long timeSlotsId) {
		this.timeSlotsId = timeSlotsId;
	}

	public String getSessionStartTime() {
		return sessionStartTime;
	}

	public void setSessionStartTime(String sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}

	public String getSessionCloseTime() {
		return sessionCloseTime;
	}

	public void setSessionCloseTime(String sessionCloseTime) {
		this.sessionCloseTime = sessionCloseTime;
	}

	public int getDayNum() {
		return dayNum;
	}

	public void setDayNum(int dayNum) {
		this.dayNum = dayNum;
	}

	public Facility getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Facility facilityId) {
		this.facilityId = facilityId;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	

}
