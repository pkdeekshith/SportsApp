package com.ins.pos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class OnlineBookingWindow {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column
	private int bookingStartDate;
	
	@Column
	private int bookingEndDate;

	@Column
	private Boolean active;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "facilityId", nullable = false)
	private Facility facilityId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBookingStartDate() {
		return bookingStartDate;
	}

	public void setBookingStartDate(int bookingStartDate) {
		this.bookingStartDate = bookingStartDate;
	}

	public int getBookingEndDate() {
		return bookingEndDate;
	}

	public void setBookingEndDate(int bookingEndDate) {
		this.bookingEndDate = bookingEndDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Facility getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Facility facilityId) {
		this.facilityId = facilityId;
	}

}
