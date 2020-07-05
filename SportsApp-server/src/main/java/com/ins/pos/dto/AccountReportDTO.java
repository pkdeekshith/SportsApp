package com.ins.pos.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AccountReportDTO {
	private long receiptNo;
	private String memberName;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date receiptDate;
	private String receiptType;
	private double debitAmount;
	private double creditAmount;

	public long getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(long receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Date getReceiptDate() {
		return receiptDate;
	}

	public void setReceiptDate(Date receiptDate) {
		this.receiptDate = receiptDate;
	}

	public String getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(String receiptType) {
		this.receiptType = receiptType;
	}

	public double getDebitAmount() {
		return debitAmount;
	}

	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}
}
