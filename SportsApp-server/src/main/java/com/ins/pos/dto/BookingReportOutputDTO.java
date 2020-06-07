package com.ins.pos.dto;

import java.util.List;

public class BookingReportOutputDTO {

	private List<BookingReportDTO> data;
	private String status;

	public List<BookingReportDTO> getData() {
		return data;
	}

	public void setData(List<BookingReportDTO> data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
