package com.ins.pos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class TimeTable {
	
	@Id
	@GeneratedValue
	@Column
	private Long timeTableId;
	
	@Column
	private int dayNum;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private Boolean session;
	
	@Column
	private int sessionStartTime;
	
	@Column
	private String sessionCloseTime;
	
	
	@Column
	private int sessionEndTime;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "facility_id", nullable = false)
	private Facility facilityId;
	
	public int getSessionEndTime() {
		return sessionEndTime;
	}

	public void setSessionEndTime(int sessionEndTime) {
		this.sessionEndTime = sessionEndTime;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subFacilityId", nullable = false)
	private SubFacility subFacilityId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "timeSlotsId", nullable = false)
	@JsonIgnore
	private TimeSlots timeSlotsId;
	
	public TimeSlots getTimeSlotsId() {
		return timeSlotsId;
	}

	public void setTimeSlotsId(TimeSlots timeSlotsId) {
		this.timeSlotsId = timeSlotsId;
	}

	@Column(columnDefinition="tinyint(1) default 1")
	private Boolean active;
	
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "timeTableId")
//	private Set<Booking> bookRole = new HashSet<Booking>(0);

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

	public Long getTimeTableId() {
		return timeTableId;
	}

	public void setTimeTableId(Long timeTableId) {
		this.timeTableId = timeTableId;
	}

	public Boolean getSession() {
		return session;
	}

	public void setSession(Boolean session) {
		this.session = session;
	}
//
//	public String getSessionStartTime() {
//		return sessionStartTime;
//	}
//
//	public void setSessionStartTime(String sessionStartTime) {
//		this.sessionStartTime = sessionStartTime;
//	}

	public int getSessionStartTime() {
		return sessionStartTime;
	}

	public void setSessionStartTime(int sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}

	public String getSessionCloseTime() {
		return sessionCloseTime;
	}

	public void setSessionCloseTime(String sessionCloseTime) {
		this.sessionCloseTime = sessionCloseTime;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
//
//	public Set<Booking> getBookRole() {
//		return bookRole;
//	}
//
//	public void setBookRole(Set<Booking> bookRole) {
//		this.bookRole = bookRole;
//	}

	public int getDayNum() {
		return dayNum;
	}

	public void setDayNum(int dayNum) {
		this.dayNum = dayNum;
	}

	

}
