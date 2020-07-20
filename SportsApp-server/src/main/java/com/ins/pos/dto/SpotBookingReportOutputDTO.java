package com.ins.pos.dto;

import java.util.List;

public class SpotBookingReportOutputDTO {

	private List<SpotBookingReportDTO> data;
	private String status;
	private double totalAmount;
	private String bookingApp;
	private String center;
	private String date;

	public List<SpotBookingReportDTO> getData() {
		return data;
	}

	public void setData(List<SpotBookingReportDTO> data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getBookingApp() {
		return bookingApp;
	}

	public void setBookingApp(String bookingApp) {
		this.bookingApp = bookingApp;
	}

	public String getCenter() {
		return center;
	}

	public void setCenter(String center) {
		this.center = center;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
