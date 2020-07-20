package com.ins.pos.dto;

import java.util.List;

public class BookingReportOutputDTO {

	private List<BookingReportDTO> data;
	private String status;
	private String bookingApp;
	private String center;
	private String fromDate;
	private String toDate;
	private String facility;
	private String subFacility;

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

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getSubFacility() {
		return subFacility;
	}

	public void setSubFacility(String subFacility) {
		this.subFacility = subFacility;
	}
}
