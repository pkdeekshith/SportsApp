package com.ins.pos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class LeaveType {
	
	@Id
	@GeneratedValue
	@Column
	private Long leaveTypeId;
	
	@Column
	private String leaveTypeName;
	
	@Column
	private Boolean active;
	
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "leaveTabId")
//	private Set<LeaveTab> leaveTabRole = new HashSet<LeaveTab>(0);	

	
	
	
//	public Set<LeaveTab> getLeaveTabRole() {
//		return leaveTabRole;
//	}
//
//	public void setLeaveTabRole(Set<LeaveTab> leaveTabRole) {
//		this.leaveTabRole = leaveTabRole;
//	}

	public Long getLeaveTypeId() {
		return leaveTypeId;
	}

	public void setLeaveTypeId(Long leaveTypeId) {
		this.leaveTypeId = leaveTypeId;
	}

	public String getLeaveTypeName() {
		return leaveTypeName;
	}

	public void setLeaveTypeName(String leaveTypeName) {
		this.leaveTypeName = leaveTypeName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}


	

}
