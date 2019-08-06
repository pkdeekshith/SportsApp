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
public class Center {

	@Id
	@GeneratedValue
	@Column
	private Long centreId;
	@Column
	private String centreName;
	
	@Column 
	private String rateHour;
	@Column
	private String rateDaily;
	@Column
	private String rateWeekly;
	@Column 
	private String rateMonthly;
	@Column
	private Date lastModifiedDate;
	@Column(columnDefinition="tinyint(1) default 1")
	private Boolean active;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private Boolean onlineActive;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id", nullable = false)
	@JsonIgnore
	private Branch branchId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lastModifiedUserId", nullable = false)
	private Member lastModifiedUserId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "centerTypeId", nullable = false)
	private CenterType centerTypeId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "locationId", nullable = false)
	private Location locationId;
	@Column
	private Date centerStartDate;
	@Column
	private Date centerEndDate;
	
	/*@OneToMany(fetch = FetchType.EAGER, mappedBy = "centerId")
	private Set<Facility> FacilityRole = new HashSet<Facility>(0);
	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "centerId")
	private Set<SubFacility> SubFacilityRole = new HashSet<SubFacility>(0);
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "centerId")
	private Set<Booking> bookRole = new HashSet<Booking>(0);
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "centerId")
	private Set<LeaveTab> leaveTabRole = new HashSet<LeaveTab>(0);*/

	public Long getCentreId() {
		return centreId;
	}

	public void setCentreId(Long centreId) {
		this.centreId = centreId;
	}

	public String getCentreName() {
		return centreName;
	}

	public void setCentreName(String centreName) {
		this.centreName = centreName;
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

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
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

	public Member getLastModifiedUserId() {
		return lastModifiedUserId;
	}

	public void setLastModifiedUserId(Member lastModifiedUserId) {
		this.lastModifiedUserId = lastModifiedUserId;
	}

	public CenterType getCenterTypeId() {
		return centerTypeId;
	}

	public void setCenterTypeId(CenterType centerTypeId) {
		this.centerTypeId = centerTypeId;
	}

	public Location getLocationId() {
		return locationId;
	}

	public void setLocationId(Location locationId) {
		this.locationId = locationId;
	}

	public Date getCenterStartDate() {
		return centerStartDate;
	}

	public void setCenterStartDate(Date centerStartDate) {
		this.centerStartDate = centerStartDate;
	}

	public Date getCenterEndDate() {
		return centerEndDate;
	}

	public void setCenterEndDate(Date centerEndDate) {
		this.centerEndDate = centerEndDate;
	}

	public Boolean getOnlineActive() {
		return onlineActive;
	}

	public void setOnlineActive(Boolean onlineActive) {
		this.onlineActive = onlineActive;
	}

	/*public Set<Facility> getFacilityRole() {
		return FacilityRole;
	}

	public void setFacilityRole(Set<Facility> facilityRole) {
		FacilityRole = facilityRole;
	}

	public Set<SubFacility> getSubFacilityRole() {
		return SubFacilityRole;
	}

	public void setSubFacilityRole(Set<SubFacility> subFacilityRole) {
		SubFacilityRole = subFacilityRole;
	}

	public Set<Booking> getBookRole() {
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
