package com.ins.pos.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table
public class CommunicationLog {

	@Id
	@Column
	private String orderId;

	@Lob
	@Column
	private String mailBody;
	
	@Column
	private String mailId;
	
	@Column
	private String phoneNumber;

	@Column
	private boolean mailStatus;

	@Lob
	@Column
	private String smsContent;

	@Column
	private boolean smsStatus;

	@Column
	private Date createdDate;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMailBody() {
		return mailBody;
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public boolean getMailStatus() {
		return mailStatus;
	}

	public void setMailStatus(boolean mailStatus) {
		this.mailStatus = mailStatus;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public boolean getSmsStatus() {
		return smsStatus;
	}

	public void setSmsStatus(boolean smsStatus) {
		this.smsStatus = smsStatus;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
