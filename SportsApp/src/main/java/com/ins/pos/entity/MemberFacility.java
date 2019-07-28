package com.ins.pos.entity;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;


@Entity
@Table
public class MemberFacility {
	
	@Id
	@GeneratedValue
	@JsonIgnore
	private Long memFacilityId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "facilityType", nullable = false)
	private FacilityType facilityType;
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "member", nullable = false)
	private Member member;
	
	@Column
	private Boolean active;

	public Long getMemFacilityId() {
		return memFacilityId;
	}

	public void setMemFacilityId(Long memFacilityId) {
		this.memFacilityId = memFacilityId;
	}

	public FacilityType getFacilityType() {
		return facilityType;
	}

	public void setFacilityType(FacilityType facilityType) {
		this.facilityType = facilityType;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	@JsonValue
	public Map<String,Object> toJSON() {
		Map<String,Object> mapValues =  new HashMap<String,Object>();
		if(this.getFacilityType()!=null) {
		mapValues.put("facilityTypeId",this.getFacilityType().getFacilityTypeId());
		mapValues.put("facilityTypeName", this.getFacilityType().getFacilityTypeName());
		}
		return mapValues;
	}

}
