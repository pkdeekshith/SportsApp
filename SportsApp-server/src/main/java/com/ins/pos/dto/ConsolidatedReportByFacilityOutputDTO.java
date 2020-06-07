package com.ins.pos.dto;

import java.util.List;

public class ConsolidatedReportByFacilityOutputDTO {

	private List<ConsolidatedReportByFacilityDTO> data;
	private double totalAmount;
	private String status;

	public List<ConsolidatedReportByFacilityDTO> getData() {
		return data;
	}

	public void setData(List<ConsolidatedReportByFacilityDTO> data) {
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
}
