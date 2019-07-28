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
public class AccountBook {
	
	@Id
	@GeneratedValue
	private Long accBookId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountsId", nullable = false)
	private Accounts accountsId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "bookingId", nullable = false)
	private Booking bookingId;
	
	@Column
	private Boolean active;

	public Long getAccBookId() {
		return accBookId;
	}

	public void setAccBookId(Long accBookId) {
		this.accBookId = accBookId;
	}

	public Accounts getAccountsId() {
		return accountsId;
	}

	public void setAccountsId(Accounts accountsId) {
		this.accountsId = accountsId;
	}

	public Booking getBookingId() {
		return bookingId;
	}

	public void setBookingId(Booking bookingId) {
		this.bookingId = bookingId;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}


}
