package com.ins.pos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class FacilityType {

	
	@Id
	@GeneratedValue
	@Column
	private Long facilityTypeId;
	
	@Column
	private String facilityTypeName;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private Boolean active;

	@Column
	private Boolean bookStatus;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private Boolean onlineActive;
	
	public Boolean getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(Boolean bookStatus) {
		this.bookStatus = bookStatus;
	}

	public Long getFacilityTypeId() {
		return facilityTypeId;
	}

	public void setFacilityTypeId(Long facilityTypeId) {
		this.facilityTypeId = facilityTypeId;
	}

	public String getFacilityTypeName() {
		return facilityTypeName;
	}

	public void setFacilityTypeName(String facilityTypeName) {
		this.facilityTypeName = facilityTypeName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getOnlineActive() {
		return onlineActive;
	}

	public void setOnlineActive(Boolean onlineActive) {
		this.onlineActive = onlineActive;
	}

}
