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
public class Booking {

	@Id
	@GeneratedValue
	@Column
	private Long bookingId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId", nullable = false)

	private Member memberId;
	@Column
	private String startTime;
	@Column
	private String endTime;

	@Column
	private int bookStatus;

	@Column
	private Date bookedDate;

	@Column
	private Date bookingStartDate;
	
	@Column
	private Date bookingEndDate;

	@Column
	private Boolean active;

	@Column
	private int dayNum;

	@Column
	private int count;

	@Column
	private int sessionStartTime;

	@Column
	private int sessionEndTime;

	@Column
	private Boolean entryStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchId", nullable = false)
	@JsonIgnore
	private Branch branchId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "centreId", nullable = false)
	@JsonIgnore
	private Center centerId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "facilityId", nullable = false)

	private Facility facilityId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subFacilityId", nullable = false)

	private SubFacility subFacilityId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lastModifiedUserId", nullable = false)

	private Member lastModifiedUserId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "timeTableId", nullable = false)
	private TimeTable timeTableId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "leaveTabId", nullable = true)
	@JsonIgnore
	private LeaveTab leaveTabId;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "bookingId")
	@JsonIgnore
	private Set<AccountBook> AccountTypeLinkRole = new HashSet<AccountBook>(0);

	public Boolean getEntryStatus() {
		return entryStatus;
	}

	public void setEntryStatus(Boolean entryStatus) {
		this.entryStatus = entryStatus;
	}

	public int getDayNum() {
		return dayNum;
	}

	public void setDayNum(int dayNum) {
		this.dayNum = dayNum;
	}

	public int getSessionStartTime() {
		return sessionStartTime;
	}

	public void setSessionStartTime(int sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}

	public int getSessionEndTime() {
		return sessionEndTime;
	}

	public void setSessionEndTime(int sessionEndTime) {
		this.sessionEndTime = sessionEndTime;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Member getMemberId() {
		return memberId;
	}

	public void setMemberId(Member memberId) {
		this.memberId = memberId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(int bookStatus) {
		this.bookStatus = bookStatus;
	}

	public Date getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(Date bookedDate) {
		this.bookedDate = bookedDate;
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

	public Member getLastModifiedUserId() {
		return lastModifiedUserId;
	}

	public void setLastModifiedUserId(Member lastModifiedUserId) {
		this.lastModifiedUserId = lastModifiedUserId;
	}

	public TimeTable getTimeTableId() {
		return timeTableId;
	}

	public void setTimeTableId(TimeTable timeTableId) {
		this.timeTableId = timeTableId;
	}

	public LeaveTab getLeaveTabId() {
		return leaveTabId;
	}

	public void setLeaveTabId(LeaveTab leaveTabId) {
		this.leaveTabId = leaveTabId;
	}

	public Set<AccountBook> getAccountTypeLinkRole() {
		return AccountTypeLinkRole;
	}

	public void setAccountTypeLinkRole(Set<AccountBook> accountTypeLinkRole) {
		AccountTypeLinkRole = accountTypeLinkRole;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getBookingStartDate() {
		return bookingStartDate;
	}

	public void setBookingStartDate(Date bookingStartDate) {
		this.bookingStartDate = bookingStartDate;
	}

	public Date getBookingEndDate() {
		return bookingEndDate;
	}

	public void setBookingEndDate(Date bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}

}
