package com.ins.pos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class BookAdditionalMembers {
	
	@Id
	@GeneratedValue
	@Column
	private Long bookAddMemId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberPrimaryId", nullable = false)
	
	private Member memberPrimaryId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberfkId", nullable = false)
	
	private Member memberfkId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountsId", nullable = false)
	
	private Accounts accountsId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bookingId", nullable = false)
	private Booking bookingId;
	
	
	@Column
	private Boolean statusUpdate;
	
	public Boolean getStatusUpdate() {
		return statusUpdate;
	}

	public void setStatusUpdate(Boolean statusUpdate) {
		this.statusUpdate = statusUpdate;
	}

	public Booking getBookingId() {
		return bookingId;
	}

	public void setBookingId(Booking bookingId) {
		this.bookingId = bookingId;
	}

	public Accounts getAccountsId() {
		return accountsId;
	}

	public void setAccountsId(Accounts accountsId) {
		this.accountsId = accountsId;
	}

	private Boolean active;

	public Long getBookAddMemId() {
		return bookAddMemId;
	}

	public void setBookAddMemId(Long bookAddMemId) {
		this.bookAddMemId = bookAddMemId;
	}

	public Member getMemberPrimaryId() {
		return memberPrimaryId;
	}

	public void setMemberPrimaryId(Member memberPrimaryId) {
		this.memberPrimaryId = memberPrimaryId;
	}

	public Member getMemberfkId() {
		return memberfkId;
	}

	public void setMemberfkId(Member memberfkId) {
		this.memberfkId = memberfkId;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	

}
