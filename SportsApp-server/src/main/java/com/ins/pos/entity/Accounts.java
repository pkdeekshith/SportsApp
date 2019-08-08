package com.ins.pos.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table

public class Accounts {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Long accountsId;
	
	@Column
	private Double actualAmount;
	
	@Column
	private Double discount;
	
	
	@Column
	private Double payableAmount;
	
	@Column
	private Double paidAmount;
	
	@Column
	private Double extraAmount;
	
	@Column
	private Double cautionDeposit;
	
	
	@Column
	private Boolean cautionStatus;
	public Boolean getCautionStatus() {
		return cautionStatus;
	}

	public void setCautionStatus(Boolean cautionStatus) {
		this.cautionStatus = cautionStatus;
	}

	public Double getCautionDeposit() {
		return cautionDeposit;
	}

	public void setCautionDeposit(Double cautionDeposit) {
		this.cautionDeposit = cautionDeposit;
	}

	@Column
	private Date paidDate;
	
	public Double getExtraAmount() {
		return extraAmount;
	}

	public void setExtraAmount(Double extraAmount) {
		this.extraAmount = extraAmount;
	}

	@Column
	private Double balanceAmount;
	
	@Column
	private String referenceNo;
	
	@Column
	private String words;
	
	@Column
	private Date bookingDate;
	
	@Column
	private Date bookingDateLast;
	
	@Column
	private Boolean active;
	
	@Column
	private Boolean isCentreBooking;
	
	@Column
	private Double creditAmount;
	
	@Column
	private String bookingType;
	
	@Column
	private int sessionStartTime;

	@Column
	private int sessionEndTime;
	
	@Column
	private String bookingApp;
	
	public Double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(Double creditAmount) {
		this.creditAmount = creditAmount;
	}

	@Column
	private String typeOfBooking;


	public String getTypeOfBooking() {
		return typeOfBooking;
	}

	public void setTypeOfBooking(String typeOfBooking) {
		this.typeOfBooking = typeOfBooking;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getBookingDateLast() {
		return bookingDateLast;
	}

	public void setBookingDateLast(Date bookingDateLast) {
		this.bookingDateLast = bookingDateLast;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	@Column
	private String paymentType;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "memberId", nullable = false)
	private Member memberId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchId", nullable = false)
	private Branch branchId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "centerId", nullable = false)
	private Center centerId;
	
	public Center getCenterId() {
		return centerId;
	}

	public void setCenterId(Center centerId) {
		this.centerId = centerId;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "accountsId")
	private Set<AccountBook> AccountTypeLinkRole = new HashSet<AccountBook>(0);

	public Long getAccountsId() {
		return accountsId;
	}

	public void setAccountsId(Long accountsId) {
		this.accountsId = accountsId;
	}

	public Double getActualAmount() {
		return actualAmount;
	}

	public void setActualAmount(Double actualAmount) {
		this.actualAmount = actualAmount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getPayableAmount() {
		return payableAmount;
	}

	public void setPayableAmount(Double payableAmount) {
		this.payableAmount = payableAmount;
	}

	public Double getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public Double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Member getMemberId() {
		return memberId;
	}

	public void setMemberId(Member memberId) {
		this.memberId = memberId;
	}

	public Branch getBranchId() {
		return branchId;
	}

	public void setBranchId(Branch branchId) {
		this.branchId = branchId;
	}

	public Set<AccountBook> getAccountTypeLinkRole() {
		return AccountTypeLinkRole;
	}

	public void setAccountTypeLinkRole(Set<AccountBook> accountTypeLinkRole) {
		AccountTypeLinkRole = accountTypeLinkRole;
	}

	public Boolean getIsCentreBooking() {
		return isCentreBooking;
	}

	public void setIsCentreBooking(Boolean isCentreBooking) {
		this.isCentreBooking = isCentreBooking;
	}

	public String getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}

	public int getSessionStartTime() {
		return sessionStartTime;
	}

	public void setSessionStartTime(int sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}

	public int getSessionEndTime() {
		return sessionEndTime;
	}

	public void setSessionEndTime(int sessionEndTime) {
		this.sessionEndTime = sessionEndTime;
	}

	public String getBookingApp() {
		return bookingApp;
	}

	public void setBookingApp(String bookingApp) {
		this.bookingApp = bookingApp;
	}
	


}
