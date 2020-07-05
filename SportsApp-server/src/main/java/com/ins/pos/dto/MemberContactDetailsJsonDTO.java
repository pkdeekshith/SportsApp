package com.ins.pos.dto;

public class MemberContactDetailsJsonDTO {

	private Long memberId;
	private String memberName;
	private String memberContactNo;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberContactNo() {
		return memberContactNo;
	}

	public void setMemberContactNo(String memberContactNo) {
		this.memberContactNo = memberContactNo;
	}
}
