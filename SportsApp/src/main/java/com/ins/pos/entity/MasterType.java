package com.ins.pos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class MasterType {
	
	@Id
	@GeneratedValue
	@Column
	private Long masterTypeId;
	
	@Column
	private String masterTypeName;
	
	@Column
	private Boolean centerCheck;
	
	@Column
	private Boolean facilityCheck;
	
	@Column 
	private Boolean subFacilityCheck;
	
	@Column
	private Boolean active;

	public Long getMasterTypeId() {
		return masterTypeId;
	}

	public void setMasterTypeId(Long masterTypeId) {
		this.masterTypeId = masterTypeId;
	}

	public String getMasterTypeName() {
		return masterTypeName;
	}

	public void setMasterTypeName(String masterTypeName) {
		this.masterTypeName = masterTypeName;
	}

	public Boolean getCenterCheck() {
		return centerCheck;
	}

	public void setCenterCheck(Boolean centerCheck) {
		this.centerCheck = centerCheck;
	}

	public Boolean getFacilityCheck() {
		return facilityCheck;
	}

	public void setFacilityCheck(Boolean facilityCheck) {
		this.facilityCheck = facilityCheck;
	}

	public Boolean getSubFacilityCheck() {
		return subFacilityCheck;
	}

	public void setSubFacilityCheck(Boolean subFacilityCheck) {
		this.subFacilityCheck = subFacilityCheck;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
	
	

}
