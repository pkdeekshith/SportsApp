package com.ins.pos.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MemberDetailsJsonDTO {

	private Long memberId;
	private String memberName;
	private String memberPhoto;
	private Boolean isStudent;
	private Boolean isGovt;
	private Boolean isCoaching;
	private String memberIdProof;
	private String gender;
	@JsonFormat(pattern = "dd MMM yyyy")
	private Date dob;
	@JsonFormat(pattern = "dd MMM yyyy")
	private Date memberTypeValidity;
	@JsonFormat(pattern = "dd MMM yyyy")
	private Date memberTypeStartDate;
	private String memberContactNo;
	private Boolean active;
	private String email;
	private String street;
	private String city;
	private String district;
	private String state;
	private String country;
	private Integer pincode;
	private String fatherName;
	private String age;
	private Double paidAmount;
	private String memberIdProofType;
	private MemberShipTypeJsonDTO memberShipType;
	private List<FacilityTypeJsonDTO> facilityType;
	private List<RoleJsonDTO> roles;
	private Boolean firstLogin;
	private String centerName;
	private Long centreId;
	private Boolean isRenewalPending;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPhoto() {
		return memberPhoto;
	}

	public void setMemberPhoto(String memberPhoto) {
		this.memberPhoto = memberPhoto;
	}

	public Boolean getIsStudent() {
		return isStudent;
	}

	public void setIsStudent(Boolean isStudent) {
		this.isStudent = isStudent;
	}

	public Boolean getIsGovt() {
		return isGovt;
	}

	public void setIsGovt(Boolean isGovt) {
		this.isGovt = isGovt;
	}

	public Boolean getIsCoaching() {
		return isCoaching;
	}

	public void setIsCoaching(Boolean isCoaching) {
		this.isCoaching = isCoaching;
	}

	public String getMemberIdProof() {
		return memberIdProof;
	}

	public void setMemberIdProof(String memberIdProof) {
		this.memberIdProof = memberIdProof;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMemberContactNo() {
		return memberContactNo;
	}

	public void setMemberContactNo(String memberContactNo) {
		this.memberContactNo = memberContactNo;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getMemberIdProofType() {
		return memberIdProofType;
	}

	public void setMemberIdProofType(String memberIdProofType) {
		this.memberIdProofType = memberIdProofType;
	}

	public List<FacilityTypeJsonDTO> getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(List<FacilityTypeJsonDTO> facilityType) {
		this.facilityType = facilityType;
	}

	public MemberShipTypeJsonDTO getMemberShipType() {
		return memberShipType;
	}

	public void setMemberShipType(MemberShipTypeJsonDTO memberShipType) {
		this.memberShipType = memberShipType;
	}

	public Date getMemberTypeValidity() {
		return memberTypeValidity;
	}

	public void setMemberTypeValidity(Date memberTypeValidity) {
		this.memberTypeValidity = memberTypeValidity;
	}

	public Date getMemberTypeStartDate() {
		return memberTypeStartDate;
	}

	public void setMemberTypeStartDate(Date memberTypeStartDate) {
		this.memberTypeStartDate = memberTypeStartDate;
	}

	public List<RoleJsonDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleJsonDTO> roles) {
		this.roles = roles;
	}

	public Boolean getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(Boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public Long getCentreId() {
		return centreId;
	}

	public void setCentreId(Long centreId) {
		this.centreId = centreId;
	}

	public Boolean getIsRenewalPending() {
		return isRenewalPending;
	}

	public void setIsRenewalPending(Boolean isRenewalPending) {
		this.isRenewalPending = isRenewalPending;
	}
}
