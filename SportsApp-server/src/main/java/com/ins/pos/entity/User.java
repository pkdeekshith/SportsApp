package com.ins.pos.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "auth_user")
public class User {

	// ------------------------
	// PRIVATE FIELDS
	// ------------------------

	// The user's name
	@Id
	@Column(name = "username", unique = true, nullable = false, length = 100)
	@NotNull
	private String username;

	// The user's email
	@NotNull
	private String email;

	@NotNull
	private String password;

	@Column(name = "enabled", nullable = false)
	private boolean enabled;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<UserRole> userRole = new HashSet<UserRole>(0);

	// ------------------------
	// PUBLIC METHODS
	// ------------------------

	public User() {
	}

	public User(String email, String username) {
		this.email = email;
		this.username = username;
	}

	// Getter and setter methods

	public User(String username) {
		this.username=username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String value) {
		this.email = value;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

} // class User