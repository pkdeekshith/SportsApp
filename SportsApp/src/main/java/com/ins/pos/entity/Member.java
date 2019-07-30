package com.ins.pos.entity;

import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Member {

	@Id
	@GeneratedValue
	@Column
	private Long memberId;
	@Column
	private String memberName;
	@Column
	private String memberPhoto;
	@Column
	private String word;

	@Column
	private Long billId;

	@Column
	private Boolean spotCheck;

	@Column
	private Boolean isStudent;

	@Column
	private Boolean isGovt;

	@Column
	private Boolean isCoaching;

	@Column
	private String memberIdProof;

	@Column
	private String gender;

	@Column
	private Date dob;
	@Column
	private String memberContactNo;

	@Column
	private String memberBarCode;
	@Column(columnDefinition = "tinyint(1) default 1")
	private Boolean active;
	@Column
	private String userName;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String street;
	@Column(name = "city")
	private String city;
	@Column(name = "district")
	private String district;
	@Column(name = "state")
	private String state;
	@Column(name = "country")
	private String country;
	@Column(name = "pincode")
	private Integer pincode;
	@Column
	private String fatherName;
	@Column
	private String age;
	@Column
	private Boolean checkStaff;
	@Column
	private Double paidAmount;
	@Column
	private String memberIdProofType;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMemberIdProof() {
		return memberIdProof;
	}

	public void setMemberIdProof(String memberIdProof) {
		this.memberIdProof = memberIdProof;
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

	public Boolean getSpotCheck() {
		return spotCheck;
	}

	public void setSpotCheck(Boolean spotCheck) {
		this.spotCheck = spotCheck;
	}

	public Long getBillId() {
		return billId;
	}

	public void setBillId(Long billId) {
		this.billId = billId;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getMemberPhoto() {
		return memberPhoto;
	}

	public void setMemberPhoto(String memberPhoto) {
		this.memberPhoto = memberPhoto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberShipTypeId", nullable = false)
	private MemberShipType memberShipTypeId;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "member")
	private Set<MemberFacility> memberFacility = new HashSet<MemberFacility>(0);

	@Column
	private Date memberTypeValidity;

	@Column
	private Date memberTypeStartDate;

	@Column
	private Date createdDate;

	@Column
	Date updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id", nullable = false)
	@JsonIgnore
	private Branch branchId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "role_id", nullable = false)
	private Role roleId;

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Boolean getCheckStaff() {
		return checkStaff;
	}

	public void setCheckStaff(Boolean checkStaff) {
		this.checkStaff = checkStaff;
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

	public MemberShipType getMemberShipTypeId() {
		return memberShipTypeId;
	}

	public void setMemberShipTypeId(MemberShipType memberShipTypeId) {
		this.memberShipTypeId = memberShipTypeId;
	}

	public Date getMemberTypeValidity() {
		return memberTypeValidity;
	}

	public void setMemberTypeValidity(Date memberTypeValidity) {
		this.memberTypeValidity = memberTypeValidity;
	}

	public String getMemberContactNo() {
		return memberContactNo;
	}

	public void setMemberContactNo(String memberContactNo) {
		this.memberContactNo = memberContactNo;
	}

	public String getMemberBarCode() {
		return memberBarCode;
	}

	public void setMemberBarCode(String memberBarCode) {
		this.memberBarCode = memberBarCode;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
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

	public Branch getBranchId() {
		return branchId;
	}

	public void setBranchId(Branch branchId) {
		this.branchId = branchId;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}

	public Set<MemberFacility> getMemberFacility() {
		return memberFacility;
	}

	public void setMemberFacility(Set<MemberFacility> memberFacility) {
		this.memberFacility = memberFacility;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getMemberTypeStartDate() {
		return memberTypeStartDate;
	}

	public void setMemberTypeStartDate(Date memberTypeStartDate) {
		this.memberTypeStartDate = memberTypeStartDate;
	}

}
