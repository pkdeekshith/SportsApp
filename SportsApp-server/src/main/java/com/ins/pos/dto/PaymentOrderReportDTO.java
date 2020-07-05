package com.ins.pos.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PaymentOrderReportDTO {

	private String orderId;

	private Long memberId;

	private String memberName;

	private Long accountId1;

	private String bookingType1;

	private Long accountId2;

	private String bookingType2;

	private Double totalAmount;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date date;

	private String status;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getAccountId1() {
		return accountId1;
	}

	public void setAccountId1(Long accountId1) {
		this.accountId1 = accountId1;
	}

	public String getBookingType1() {
		return bookingType1;
	}

	public void setBookingType1(String bookingType1) {
		this.bookingType1 = bookingType1;
	}

	public Long getAccountId2() {
		return accountId2;
	}

	public void setAccountId2(Long accountId2) {
		this.accountId2 = accountId2;
	}

	public String getBookingType2() {
		return bookingType2;
	}

	public void setBookingType2(String bookingType2) {
		this.bookingType2 = bookingType2;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
