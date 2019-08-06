package com.ins.pos.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role {

	@Id
	@GeneratedValue@Column
	private Long roleId;
	
	@Column
	private String roleName;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private Boolean active;
	
	@Column
	private Date createDate;

	/*@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "roleId")
	private Set<RoleAction> RoleActionRole = new HashSet<RoleAction>(0);

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "roleId")
	private Set<Member> MemberRole = new HashSet<Member>(0);*/

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/*public Set<RoleAction> getRoleActionRole() {
		return RoleActionRole;
	}

	public void setRoleActionRole(Set<RoleAction> roleActionRole) {
		RoleActionRole = roleActionRole;
	}

	public Set<Member> getMemberRole() {
		return MemberRole;
	}

	public void setMemberRole(Set<Member> memberRole) {
		MemberRole = memberRole;
	}*/

}
