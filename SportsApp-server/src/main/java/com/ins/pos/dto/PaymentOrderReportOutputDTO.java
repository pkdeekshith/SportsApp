package com.ins.pos.dto;

import java.util.List;

public class PaymentOrderReportOutputDTO {

	private List<PaymentOrderReportDTO> data;
	private double totalAmount;
	private String status;
	private String fromDate;
	private String toDate;
	private String bookingApp;
	private String paymentStatus;

	public List<PaymentOrderReportDTO> getData() {
		return data;
	}

	public void setData(List<PaymentOrderReportDTO> data) {
		this.data = data;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getBookingApp() {
		return bookingApp;
	}

	public void setBookingApp(String bookingApp) {
		this.bookingApp = bookingApp;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
}
