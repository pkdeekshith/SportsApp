package com.ins.pos.entity;

import java.util.Date;

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
public class SubFacility {

	@Id
	@GeneratedValue
	@Column
	private Long subFacilityId;
	
	@Column
	private String rateHour;
	
	@Column
	private String rateDaily;
	
	@Column
	private String rateWeekly;
	
	@Column
	private String rateMonthly;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private Boolean active;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private Boolean onlineActive;
	
	@Column
	private Date lastModifiedDate;
	
	@Column
	private int slotLimit;
	
	@Column
	private String subFacilityName;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "facilityId", nullable = false)
	@JsonIgnore
	private Facility facilityId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "centerId", nullable = false)
//	@JsonIgnore
	private Center centerId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchId", nullable = false)
	@JsonIgnore
	private Branch branchId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "lastModifiedUserId", nullable = false)
	private Member lastModifiedUserId;

	/*@OneToMany(fetch = FetchType.EAGER, mappedBy = "subFacilityId")
	private Set<Booking> bookRole = new HashSet<Booking>(0);ff
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "subFacilityId")
	private Set<LeaveTab> leaveTabRole = new HashSet<LeaveTab>(0);*/

	public Long getSubFacilityId() {
		return subFacilityId;
	}

	public void setSubFacilityId(Long subFacilityId) {
		this.subFacilityId = subFacilityId;
	}

	public String getRateHour() {
		return rateHour;
	}

	public void setRateHour(String rateHour) {
		this.rateHour = rateHour;
	}

	public String getRateDaily() {
		return rateDaily;
	}

	public void setRateDaily(String rateDaily) {
		this.rateDaily = rateDaily;
	}

	public String getRateWeekly() {
		return rateWeekly;
	}

	public void setRateWeekly(String rateWeekly) {
		this.rateWeekly = rateWeekly;
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

	public String getSubFacilityName() {
		return subFacilityName;
	}

	public void setSubFacilityName(String subFacilityName) {
		this.subFacilityName = subFacilityName;
	}

	public Facility getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Facility facilityId) {
		this.facilityId = facilityId;
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

	public Member getLastModifiedUserId() {
		return lastModifiedUserId;
	}

	public void setLastModifiedUserId(Member lastModifiedUserId) {
		this.lastModifiedUserId = lastModifiedUserId;
	}

	public Boolean getOnlineActive() {
		return onlineActive;
	}

	public void setOnlineActive(Boolean onlineActive) {
		this.onlineActive = onlineActive;
	}

	public int getSlotLimit() {
		return slotLimit;
	}

	public void setSlotLimit(int slotLimit) {
		this.slotLimit = slotLimit;
	}

	/*public Set<Booking> getBookRole() {
		return bookRole;
	}

	public void setBookRole(Set<Booking> bookRole) {
		this.bookRole = bookRole;
	}

	public Set<LeaveTab> getLeaveTabRole() {
		return leaveTabRole;
	}

	public void setLeaveTabRole(Set<LeaveTab> leaveTabRole) {
		this.leaveTabRole = leaveTabRole;
	}*/

}