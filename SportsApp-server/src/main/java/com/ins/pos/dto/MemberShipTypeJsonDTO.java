package com.ins.pos.dto;

public class MemberShipTypeJsonDTO {

	private Long memberShipId;
	
	private String memberShipTypeName;
    
	private Double memberShipCost;
	
	private Double memberShipDiscount;
	
	private Double memberIdCardAmount;

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

	public Double getMemberIdCardAmount() {
		return memberIdCardAmount;
	}

	public void setMemberIdCardAmount(Double memberIdCardAmount) {
		this.memberIdCardAmount = memberIdCardAmount;
	}
		
}
