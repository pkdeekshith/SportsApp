package com.ins.pos.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Branch {

	@Id
	@GeneratedValue
	@Column
	private Long branchId;
	
	@Column
	private String branchName;
	
	@Column
	private String branchCode;
	
	@Column
	private Date startDate;
	
	@Column
	private String StreetAddress;
	
	@Column
	private String landMark;
	
	@Column
	private String city;
	
	@Column
	private String district;
	
	@Column
	private String state;
	
	@Column
	private String country;
	
	
	@Column
	private String pinCode;
	
	
	@Column
	private String contactName;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "branchId")
	private Set<Member> MemberRole = new HashSet<Member>(0);

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "branchId")
	private Set<Facility> facilityRole = new HashSet<Facility>(0);

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "branchId")
	private Set<SubFacility> subfacilityRole = new HashSet<SubFacility>(0);

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "branchId")
	private Set<Booking> bookRole = new HashSet<Booking>(0);

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "branchId")
	private Set<LeaveTab> leaveTypRole = new HashSet<LeaveTab>(0);

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "branchId")
	private Set<Accounts> accountRole = new HashSet<Accounts>(0);
	
	
	@Column
	private String contactPhoneNumber;
	
	@Column
	private String contactEmail;
	
	@Column(columnDefinition="tinyint(1) default 1")
	@Type(type = "org.hibernate.type.NumericBooleanType")
	private Boolean active;

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStreetAddress() {
		return StreetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		StreetAddress = streetAddress;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
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

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public Set<Member> getMemberRole() {
		return MemberRole;
	}

	public void setMemberRole(Set<Member> memberRole) {
		MemberRole = memberRole;
	}

	public Set<Facility> getFacilityRole() {
		return facilityRole;
	}

	public void setFacilityRole(Set<Facility> facilityRole) {
		this.facilityRole = facilityRole;
	}

	public Set<SubFacility> getSubfacilityRole() {
		return subfacilityRole;
	}

	public void setSubfacilityRole(Set<SubFacility> subfacilityRole) {
		this.subfacilityRole = subfacilityRole;
	}

	public Set<Booking> getBookRole() {
		return bookRole;
	}

	public void setBookRole(Set<Booking> bookRole) {
		this.bookRole = bookRole;
	}

	public Set<LeaveTab> getLeaveTypRole() {
		return leaveTypRole;
	}

	public void setLeaveTypRole(Set<LeaveTab> leaveTypRole) {
		this.leaveTypRole = leaveTypRole;
	}

	public Set<Accounts> getAccountRole() {
		return accountRole;
	}

	public void setAccountRole(Set<Accounts> accountRole) {
		this.accountRole = accountRole;
	}

	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	
}
