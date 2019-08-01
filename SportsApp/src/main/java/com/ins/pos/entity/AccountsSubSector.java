package com.ins.pos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class AccountsSubSector {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Long accountsSubId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "facilityId", nullable = false)
	
	private Facility facilityId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subFacilityId", nullable = false)
	private SubFacility subFacilityId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountsId", nullable = false)
	private Accounts accountsId;
	
	public Accounts getAccountsId() {
		return accountsId;
	}

	public void setAccountsId(Accounts accountsId) {
		this.accountsId = accountsId;
	}

	@Column
	private Boolean status;

	public Long getAccountsSubId() {
		return accountsSubId;
	}

	public void setAccountsSubId(Long accountsSubId) {
		this.accountsSubId = accountsSubId;
	}

	public Facility getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Facility facilityId) {
		this.facilityId = facilityId;
	}

	public SubFacility getSubFacilityId() {
		return subFacilityId;
	}

	public void setSubFacilityId(SubFacility subFacilityId) {
		this.subFacilityId = subFacilityId;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	

}
