package com.ins.pos.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class MemberShipType {

	@Id
	@GeneratedValue
	@Column
	private Long memberShipId;
	
    @Column
	private String memberShipTypeName;
    
	@Column
	private Double memberShipCost;
	
	@Column
	private Double memberShipDiscount;
	
	
	@Column
	private Double memberIdCardAmount;
	
	public Double getMemberIdCardAmount() {
		return memberIdCardAmount;
	}

	public void setMemberIdCardAmount(Double memberIdCardAmount) {
		this.memberIdCardAmount = memberIdCardAmount;
	}

	@Column(columnDefinition="tinyint(1) default true")
	private Boolean active;
	
	@Column
	private Date lastModifiedDate;

	/*@OneToMany(fetch = FetchType.EAGER, mappedBy = "memberShipTypeId")
	@JsonIgnore
	private Set<Member> MemberRole = new HashSet<Member>(0);*/

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Long getMemberShipId() {
		return memberShipId;
	}

	public void setMemberShipId(Long memberShipId) {
		this.memberShipId = memberShipId;
	}

	public String getMemberShipTypeName() {
		return memberShipTypeName;
	}

	public void setMemberShipTypeName(String memberShipTypeName) {
		this.memberShipTypeName = memberShipTypeName;
	}

	public Double getMemberShipCost() {
		return memberShipCost;
	}

	public void setMemberShipCost(Double memberShipCost) {
		this.memberShipCost = memberShipCost;
	}

	public Double getMemberShipDiscount() {
		return memberShipDiscount;
	}

	public void setMemberShipDiscount(Double memberShipDiscount) {
		this.memberShipDiscount = memberShipDiscount;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	/*public Set<Member> getMemberRole() {
		return MemberRole;
	}

	public void setMemberRole(Set<Member> memberRole) {
		MemberRole = memberRole;
	}*/

	

	
	
}
