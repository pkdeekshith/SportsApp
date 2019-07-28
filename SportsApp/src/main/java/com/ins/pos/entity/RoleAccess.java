package com.ins.pos.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RoleAccess {

	@Id
	@GeneratedValue
	private Long roleAccessId;

	private String pageName;

	private int access;

	@ManyToOne
	private Role roleId;

	public Long getRoleAccessId() {
		return roleAccessId;
	}

	public void setRoleAccessId(Long roleAccessId) {
		this.roleAccessId = roleAccessId;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public int getAccess() {
		return access;
	}

	public void setAccess(int access) {
		this.access = access;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}
}
