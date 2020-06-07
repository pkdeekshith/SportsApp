package com.ins.pos.dto;

public class SummaryReportDTO {

	private String date;
	private double memberRegistration;
	private double renewal;
	private double booking;
	private double quickBooking;
	private double total;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
