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
public class UserSports {

	@Id
	@GeneratedValue
	@Column(name="user_sports_id")
	private Integer userSportsId;
	@Column(name="user_type")
	private String userType;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "institution_id", nullable = false)
	private Institution institutionId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchId", nullable = false)	
	private Branches branchId;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="dob")
	private String DOB;
	@Column(name="email")
	private String email;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userRoleId", nullable = false)	
	private UserRole userRoleId;
	@Column(name="user_sports_log_id")
	private String userSportsLogId;
	@Column(name="password")
	private String password;
	@Column(name="confirm_password")
	private String confirmPassword;
	public Integer getUserSportsId() {
		return userSportsId;
	}
	public void setUserSportsId(Integer userSportsId) {
		this.userSportsId = userSportsId;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
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
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserRole getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(UserRole userRoleId) {
		this.userRoleId = userRoleId;
	}
	public String getUserSportsLogId() {
		return userSportsLogId;
	}
	public void setUserSportsLogId(String userSportsLogId) {
		this.userSportsLogId = userSportsLogId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
