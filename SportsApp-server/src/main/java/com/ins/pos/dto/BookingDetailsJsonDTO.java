package com.ins.pos.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class BookingDetailsJsonDTO {

	private Long accountsId;
	
	private Double paidAmount;
	
	@JsonFormat(pattern = "dd MMM yyyy")
	private Date bookingStartDate;

	@JsonFormat(pattern = "dd MMM yyyy")
	private Date bookingEndDate;
	
	@JsonFormat(pattern = "dd MMM yyyy")
	private Date bookedDate;
	
	private int sessionStartTime;

	private int sessionEndTime;

	private Long memberId;

	private String memberName;
	
	private Long facilityId;
	
	private String facilityName;
	
	private Long subFacilityId;
	
	private String subFacilityName;

	public Long getAccountsId() {
		return accountsId;
	}

	public void setAccountsId(Long accountsId) {
		this.accountsId = accountsId;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
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

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

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

	public Long getSubFacilityId() {
		return subFacilityId;
	}

	public void setSubFacilityId(Long subFacilityId) {
		this.subFacilityId = subFacilityId;
	}

	public String getSubFacilityName() {
		return subFacilityName;
	}

	public void setSubFacilityName(String subFacilityName) {
		this.subFacilityName = subFacilityName;
	}

	public Date getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(Date bookedDate) {
		this.bookedDate = bookedDate;
	}

	}
