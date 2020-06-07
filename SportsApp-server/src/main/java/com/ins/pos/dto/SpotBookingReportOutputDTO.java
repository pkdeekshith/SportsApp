package com.ins.pos.dto;

import java.util.List;

public class SpotBookingReportOutputDTO {

	private List<SpotBookingReportDTO> data;
	private String status;
	private double totalAmount;

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
}
