package com.ins.pos.dto;

import java.util.List;

public class SummaryReportOutputDTO {

	private List<SummaryReportDTO> data;
	private double memberRegistration;
	private double renewal;
	private double booking;
	private double quickBooking;
	private double total;
	private String status;

	public List<SummaryReportDTO> getData() {
		return data;
	}

	public void setData(List<SummaryReportDTO> data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getMemberRegistration() {
		return memberRegistration;
	}

	public void setMemberRegistration(double memberRegistration) {
		this.memberRegistration = memberRegistration;
	}

	public double getRenewal() {
		return renewal;
	}

	public void setRenewal(double renewal) {
		this.renewal = renewal;
	}

	public double getBooking() {
		return booking;
	}

	public void setBooking(double booking) {
		this.booking = booking;
	}

	public double getQuickBooking() {
		return quickBooking;
	}

	public void setQuickBooking(double quickBooking) {
		this.quickBooking = quickBooking;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
}
