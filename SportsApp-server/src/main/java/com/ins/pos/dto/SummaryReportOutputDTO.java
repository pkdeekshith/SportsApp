package com.ins.pos.dto;

import java.util.List;

public class SummaryReportOutputDTO {

	private List<SummaryReportDTO> data;
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
}
