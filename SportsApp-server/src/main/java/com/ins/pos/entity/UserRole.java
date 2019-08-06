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
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "auth_user_roles", uniqueConstraints = @UniqueConstraint(columnNames = {
		"role", "username" }))
public class UserRole {

	@Id
	@Column(name = "user_role_id", unique = true, nullable = false)
	@GeneratedValue
	private Integer userRoleId;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "userRoleId")
	private Set<UserSports> UserSportsRole = new HashSet<UserSports>(0);
	

	public Set<UserSports> getUserSportsRole() {
		return UserSportsRole;
	}

	public void setUserSportsRole(Set<UserSports> userSportsRole) {
		UserSportsRole = userSportsRole;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username", nullable = false)
	private User user;
	@Column(name = "role", nullable = false, length = 45)
	private String role;

	public UserRole() {
	}

	public UserRole(User user, String role) {
		this.user = user;
		this.role = role;
	}

	public Integer getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}