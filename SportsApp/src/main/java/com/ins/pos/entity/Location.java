package com.ins.pos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Location {

	@Id 
	@GeneratedValue
	@Column
	private Long locationId;
	
	@Column
	private String locationName;
	
	/*@OneToMany(fetch = FetchType.EAGER, mappedBy = "locationId")
	private Set<Center> centerRole = new HashSet<Center>(0);*/
	

	@Column(columnDefinition="tinyint(1) default 1")
	private Boolean active;


	public Long getLocationId() {
		return locationId;
	}


	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}


	public String getLocationName() {
		return locationName;
	}


	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}


	/*public Set<Center> getCenterRole() {
		return centerRole;
	}


	public void setCenterRole(Set<Center> centerRole) {
		this.centerRole = centerRole;
	}*/


	public Boolean getActive() {
		return active;
	}


	public void setActive(Boolean active) {
		this.active = active;
	}


	
	
	
}
