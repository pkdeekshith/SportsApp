package com.ins.pos.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Institution {
	
	@Id
	@GeneratedValue
	@Column
	private Integer institutionId;
	@Column(name="institution_name")
	private String institutionName;
	@Column(name="company_start_date")
	private String companyStartDate;
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
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
	@Column(name="website_url")
	private String websiteUrl;
	@Column(name="description")
	private String description;
	@Column(name="tax_register_no")
	private String taxRegisterNo;
	@Column(name="tax_register_date")
	private String taxRegisterDate;
	@Column(name="tax_empire_date")
	private String taxExpireDate;
	@Column(name="tax_description")
	private String taxDescription;
	@Column(name="tax_type")
	private String taxType;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "institutionId")
	private Set<Branches> branchRole = new HashSet<Branches>(0);
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "institutionId")
	private Set<FinancialYear> financialRole = new HashSet<FinancialYear>(0);
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "institutionId")
	private Set<UserSports> userSportsRole = new HashSet<UserSports>(0);
	public Set<UserSports> getUserSportsRole() {
		return userSportsRole;
	}
	public void setUserSportsRole(Set<UserSports> userSportsRole) {
		this.userSportsRole = userSportsRole;
	}
	public Set<FinancialYear> getFinancialRole() {
		return financialRole;
	}
	public void setFinancialRole(Set<FinancialYear> financialRole) {
		this.financialRole = financialRole;
	}
	public Set<Branches> getBranchRole() {
		return branchRole;
	}
	public void setBranchRole(Set<Branches> branchRole) {
		this.branchRole = branchRole;
	}
	public Integer getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(Integer institutionId) {
		this.institutionId = institutionId;
	}
	public String getCompanyStartDate() {
		return companyStartDate;
	}
	public void setCompanyStartDate(String companyStartDate) {
		this.companyStartDate = companyStartDate;
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
	public String getWebsiteUrl() {
		return websiteUrl;
	}
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTaxRegisterNo() {
		return taxRegisterNo;
	}
	public void setTaxRegisterNo(String taxRegisterNo) {
		this.taxRegisterNo = taxRegisterNo;
	}
	public String getTaxRegisterDate() {
		return taxRegisterDate;
	}
	public void setTaxRegisterDate(String taxRegisterDate) {
		this.taxRegisterDate = taxRegisterDate;
	}
	public String getTaxExpireDate() {
		return taxExpireDate;
	}
	public void setTaxExpireDate(String taxExpireDate) {
		this.taxExpireDate = taxExpireDate;
	}
	public String getTaxDescription() {
		return taxDescription;
	}
	public void setTaxDescription(String taxDescription) {
		this.taxDescription = taxDescription;
	}
	public String getTaxType() {
		return taxType;
	}
	public void setTaxType(String taxType) {
		this.taxType = taxType;
	}
	
	
	

}
