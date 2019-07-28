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
public class Facility {

	@Id
	@GeneratedValue
	@Column
	private Long facilityId;
	@Column
	private String facilityName;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "centerId", nullable = false)
	private Center centerId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchId", nullable = false)
	@JsonIgnore
	private Branch branchId;

	@Column
	private String rateHour;
	@Column
	private String rateWeekly;
	@Column
	private String rateDaily;
	@Column
	private String rateMonthly;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private Boolean onlineActive;

	@Column(columnDefinition = "tinyint(1) default 1")
	private Boolean active;
	@Column
	private Date lastModifiedDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lastModifiedUserId", nullable = true)
	private Member lastModifiedUserId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "facilityTypeId", nullable = false)
	private FacilityType facilityTypeId;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "facilityId")
	private Set<SubFacility> SubFacilityRole = new HashSet<SubFacility>(0);

//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "facilityId")
//	private Set<Booking> bookRole = new HashSet<Booking>(0);
//	
//	
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "facilityId")
//	private Set<LeaveTab> leaveTypeRole = new HashSet<LeaveTab>(0);
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "facilityId")
//	private Set<Leave> leaveRole = new HashSet<Leave>(0);


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


	public Center getCenterId() {
		return centerId;
	}


	public void setCenterId(Center centerId) {
		this.centerId = centerId;
	}


	public Branch getBranchId() {
		return branchId;
	}


	public void setBranchId(Branch branchId) {
		this.branchId = branchId;
	}


	public String getRateHour() {
		return rateHour;
	}


	public void setRateHour(String rateHour) {
		this.rateHour = rateHour;
	}


	public String getRateWeekly() {
		return rateWeekly;
	}


	public void setRateWeekly(String rateWeekly) {
		this.rateWeekly = rateWeekly;
	}


	public String getRateDaily() {
		return rateDaily;
	}


	public void setRateDaily(String rateDaily) {
		this.rateDaily = rateDaily;
	}


	public String getRateMonthly() {
		return rateMonthly;
	}


	public void setRateMonthly(String rateMonthly) {
		this.rateMonthly = rateMonthly;
	}


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}


	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}


	public Member getLastModifiedUserId() {
		return lastModifiedUserId;
	}


	public void setLastModifiedUserId(Member lastModifiedUserId) {
		this.lastModifiedUserId = lastModifiedUserId;
	}


	public FacilityType getFacilityTypeId() {
		return facilityTypeId;
	}


	public void setFacilityTypeId(FacilityType facilityTypeId) {
		this.facilityTypeId = facilityTypeId;
	}


	public Set<SubFacility> getSubFacilityRole() {
		return SubFacilityRole;
	}


	public void setSubFacilityRole(Set<SubFacility> subFacilityRole) {
		SubFacilityRole = subFacilityRole;
	}


	public Boolean getOnlineActive() {
		return onlineActive;
	}


	public void setOnlineActive(Boolean onlineActive) {
		this.onlineActive = onlineActive;
	}


	/*public Set<Booking> getBookRole() {
		return bookRole;
	}


	public void setBookRole(Set<Booking> bookRole) {
		this.bookRole = bookRole;
	}


	public Set<LeaveTab> getLeaveTypeRole() {
		return leaveTypeRole;
	}


	public void setLeaveTypeRole(Set<LeaveTab> leaveTypeRole) {
		this.leaveTypeRole = leaveTypeRole;
	}*/

}
