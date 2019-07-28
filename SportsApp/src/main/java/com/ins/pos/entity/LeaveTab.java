package com.ins.pos.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class LeaveTab {
	
	@Id
	@GeneratedValue
	@Column
	private Long leaveTabId;
	
	@Column
	private String sessionType;
	@Column
	private Date leaveDate;
	@Column
	private String sesionType;
	
	@Column
	private Boolean active;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id", nullable = false)
	@JsonIgnore
	private Branch branchId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "center_id", nullable = false)
	private Center centerId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "facilityId", nullable = false)
	private Facility facilityId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subFacilityId", nullable = false)
	private SubFacility subFacilityId;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "leaveTabId")
	private Set<Booking> BookingRole = new HashSet<Booking>(0);	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "leaveTypeId", nullable = false)
	private LeaveType leaveTypeId;
	
	public LeaveType getLeaveTypeId() {
		return leaveTypeId;
	}
	public void setLeaveTypeId(LeaveType leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}
	public Long getLeaveTabId() {
		return leaveTabId;
	}
	public void setLeaveTabId(Long leaveTabId) {
		this.leaveTabId = leaveTabId;
	}
	public String getSessionType() {
		return sessionType;
	}
	public void setSessionType(String sessionType) {
		this.sessionType = sessionType;
	}
	public Date getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	public String getSesionType() {
		return sesionType;
	}
	public void setSesionType(String sesionType) {
		this.sesionType = sesionType;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Branch getBranchId() {
		return branchId;
	}
	public void setBranchId(Branch branchId) {
		this.branchId = branchId;
	}
	public Center getCenterId() {
		return centerId;
	}
	public void setCenterId(Center centerId) {
		this.centerId = centerId;
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
	public Set<Booking> getBookingRole() {
		return BookingRole;
	}
	public void setBookingRole(Set<Booking> bookingRole) {
		BookingRole = bookingRole;
	}
	

}
