package com.ins.pos.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class PaymentOrderStatus {

	@Id
	@Column
	private String orderId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "memberId", nullable = false)
	private Member memberId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "accountId1", nullable = false)
	private Accounts accountId1;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "accountId2", nullable = true)
	private Accounts accountId2;

	@Column
	private Double totalAmount;
	
	@Column
	private String isNewMember;

	@Column
	private String leftPad;

	@Column
	private String rightPad;
	
	@Column
	private Date createdDate;

	@Column
	private String status;
	
	@Column
	@Lob
	private String merchantReq;

	public String getLeftPad() {
		return leftPad;
	}

	public void setLeftPad(String leftPad) {
		this.leftPad = leftPad;
	}

	public String getRightPad() {
		return rightPad;
	}

	public void setRightPad(String rightPad) {
		this.rightPad = rightPad;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Member getMemberId() {
		return memberId;
	}

	public void setMemberId(Member memberId) {
		this.memberId = memberId;
	}

	public Accounts getAccountId1() {
		return accountId1;
	}

	public void setAccountId1(Accounts accountId1) {
		this.accountId1 = accountId1;
	}

	public Accounts getAccountId2() {
		return accountId2;
	}

	public void setAccountId2(Accounts accountId2) {
		this.accountId2 = accountId2;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getIsNewMember() {
		return isNewMember;
	}

	public void setIsNewMember(String isNewMember) {
		this.isNewMember = isNewMember;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getMerchantReq() {
		return merchantReq;
	}

	public void setMerchantReq(String merchantReq) {
		this.merchantReq = merchantReq;
	}

}
