package com.ins.pos.dto;

import java.util.List;

public class ConsolidatedReportByDateOutputDTO {

	private List<ConsolidatedReportByDateDTO> data;
	private double totalAmount;
	private String status;

	public List<ConsolidatedReportByDateDTO> getData() {
		return data;
	}

	public void setData(List<ConsolidatedReportByDateDTO> data) {
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
