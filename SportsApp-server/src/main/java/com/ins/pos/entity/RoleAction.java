package com.ins.pos.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RoleAction {

	@Id
	@GeneratedValue
	private Long roleActionId;

	private String actionName;

	private String pageName;

	private Boolean viewAccess;

	private Boolean active;

	@ManyToOne
	private Role roleId;

	public Long getRoleActionId() {
		return roleActionId;
	}

	public void setRoleActionId(Long roleActionId) {
		this.roleActionId = roleActionId;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Role getRoleId() {
		return roleId;
	}

	public void setRoleId(Role roleId) {
		this.roleId = roleId;
	}

	public Boolean getViewAccess() {
		return viewAccess;
	}

	public void setViewAccess(Boolean viewAccess) {
		this.viewAccess = viewAccess;
	}
}
