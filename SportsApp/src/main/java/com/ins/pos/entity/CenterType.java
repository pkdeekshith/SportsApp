package com.ins.pos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class CenterType {

	@Id
	@GeneratedValue
	@Column
	private Long centerTypeId;
	
	@Column
	private String centerTypeName;
	
	@Column(columnDefinition="tinyint(1) default 1")
	private Boolean active;
	


	/*@OneToMany(fetch = FetchType.EAGER, mappedBy = "centerTypeId")
	private Set<Center> CenterRole = new HashSet<Center>(0);*/



	public Long getCenterTypeId() {
		return centerTypeId;
	}



	public void setCenterTypeId(Long centerTypeId) {
		this.centerTypeId = centerTypeId;
	}



	public String getCenterTypeName() {
		return centerTypeName;
	}



	public void setCenterTypeName(String centerTypeName) {
		this.centerTypeName = centerTypeName;
	}



	public Boolean getActive() {
		return active;
	}



	public void setActive(Boolean active) {
		this.active = active;
	}



/*	public Set<Center> getCenterRole() {
		return CenterRole;
	}

	public void setCenterRole(Set<Center> centerRole) {
		CenterRole = centerRole;
	}*/


	

	
}
