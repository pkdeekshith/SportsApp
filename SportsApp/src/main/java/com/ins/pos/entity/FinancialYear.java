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
public class FinancialYear {
	
	
	@Id
	@GeneratedValue
	@Column
	private Long financialYearId;
	@Column(name="finiancial_name")
	private String financialName;
	@Column(name="start_date")
	private String startDate;
	@Column(name="end_date")
	private String endDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "institution_id", nullable = false)
	private Institution institutionId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchId", nullable = false)
	private Branches branchId;
	public Long getFinancialYearId() {
		return financialYearId;
	}
	public void setFinancialYearId(Long financialYearId) {
		this.financialYearId = financialYearId;
	}
	public String getFinancialName() {
		return financialName;
	}
	public void setFinancialName(String financialName) {
		this.financialName = financialName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Institution getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(Institution institutionId) {
		this.institutionId = institutionId;
	}
	public Branches getBranchId() {
		return branchId;
	}
	public void setBranchId(Branches branchId) {
		this.branchId = branchId;
	}
	
	

}
