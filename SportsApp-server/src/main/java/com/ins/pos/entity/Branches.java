package com.ins.pos.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Branches {

	
	@Id
	@GeneratedValue
	@Column
	private Integer branchId;
	@Column(name="branch_name")
	private String branchName;
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	@Column(name="branch_code")
	private String branchCode;
	@Column(name="branch_startDate")
	private String branchStartDate;
	@Column(name="fax_no")
	private String faxNo;
	@Column(name="street_address")
	private String streetAddress;
	@Column(name="landmark")
	private String landMark;
	@Column(name="pincode")
	private Integer pincode;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="country")
	private String country;
	@Column(name="contact_name")
	private String contactName;
	@Column(name="mobile_no")
	private Integer mobileNo;
	@Column(name="landline_no")
	private String landLineNo;
	@Column(name="email")
	private String email;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "institution_id", nullable = false)
	private Institution institutionId;
	
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "branchId")
	private Set<UserSports> UserSportsRole = new HashSet<UserSports>(0);
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "branchId")
	private Set<FinancialYear> financialSports = new HashSet<FinancialYear>(0);
	public Set<FinancialYear> getFinancialSports() {
		return financialSports;
	}
	public void setFinancialSports(Set<FinancialYear> financialSports) {
		this.financialSports = financialSports;
	}
	public Set<UserSports> getUserSportsRole() {
		return UserSportsRole;
	}
	public void setUserSportsRole(Set<UserSports> userSportsRole) {
		UserSportsRole = userSportsRole;
	}
	public Institution getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(Institution institutionId) {
		this.institutionId = institutionId;
	}
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getBranchStartDate() {
		return branchStartDate;
	}
	public void setBranchStartDate(String branchStartDate) {
		this.branchStartDate = branchStartDate;
	}
	public String getFaxNo() {
		return faxNo;
	}
	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getLandMark() {
		return landMark;
	}
	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public Integer getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(Integer mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getLandLineNo() {
		return landLineNo;
	}
	public void setLandLineNo(String landLineNo) {
		this.landLineNo = landLineNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
