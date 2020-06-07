package com.ins.pos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class PaymentResponse {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	private String orderId;
	@Column
	private String pgMeTrnRefNo;
	@Column
	private String trnAmt;
	@Column
	private String authNStatus;
	@Column
	private String authZStatus;
	@Column
	private String captureStatus;
	@Column
	private String rrn;
	@Column
	private String authZCode;
	@Column
	private String responseCode;
	@Column
	private String trnReqDate;
	@Column
	private String statusCode;
	@Column
	private String statusDesc;
	@Column
	private String addField1;
	@Column
	private String addField2;
	@Column
	private String addField3;
	@Column
	private String addField4;
	@Column
	private String addField5;
	@Column
	private String addField6;
	@Column
	private String addField7;
	@Column
	private String addField8;
	@Column
	private String addField9;
	@Column
	private String addField10;
	@Column
	private String pgRefCancelReqId;
	@Column
	private String refundAmt;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getPgMeTrnRefNo() {
		return pgMeTrnRefNo;
	}
	public void setPgMeTrnRefNo(String pgMeTrnRefNo) {
		this.pgMeTrnRefNo = pgMeTrnRefNo;
	}
	public String getTrnAmt() {
		return trnAmt;
	}
	public void setTrnAmt(String trnAmt) {
		this.trnAmt = trnAmt;
	}
	public String getAuthNStatus() {
		return authNStatus;
	}
	public void setAuthNStatus(String authNStatus) {
		this.authNStatus = authNStatus;
	}
	public String getAuthZStatus() {
		return authZStatus;
	}
	public void setAuthZStatus(String authZStatus) {
		this.authZStatus = authZStatus;
	}
	public String getCaptureStatus() {
		return captureStatus;
	}
	public void setCaptureStatus(String captureStatus) {
		this.captureStatus = captureStatus;
	}
	public String getRrn() {
		return rrn;
	}
	public void setRrn(String rrn) {
		this.rrn = rrn;
	}
	public String getAuthZCode() {
		return authZCode;
	}
	public void setAuthZCode(String authZCode) {
		this.authZCode = authZCode;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getTrnReqDate() {
		return trnReqDate;
	}
	public void setTrnReqDate(String trnReqDate) {
		this.trnReqDate = trnReqDate;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getAddField1() {
		return addField1;
	}
	public void setAddField1(String addField1) {
		this.addField1 = addField1;
	}
	public String getAddField2() {
		return addField2;
	}
	public void setAddField2(String addField2) {
		this.addField2 = addField2;
	}
	public String getAddField3() {
		return addField3;
	}
	public void setAddField3(String addField3) {
		this.addField3 = addField3;
	}
	public String getAddField4() {
		return addField4;
	}
	public void setAddField4(String addField4) {
		this.addField4 = addField4;
	}
	public String getAddField5() {
		return addField5;
	}
	public void setAddField5(String addField5) {
		this.addField5 = addField5;
	}
	public String getAddField6() {
		return addField6;
	}
	public void setAddField6(String addField6) {
		this.addField6 = addField6;
	}
	public String getAddField7() {
		return addField7;
	}
	public void setAddField7(String addField7) {
		this.addField7 = addField7;
	}
	public String getAddField8() {
		return addField8;
	}
	public void setAddField8(String addField8) {
		this.addField8 = addField8;
	}
	public String getAddField9() {
		return addField9;
	}
	public void setAddField9(String addField9) {
		this.addField9 = addField9;
	}
	public String getAddField10() {
		return addField10;
	}
	public void setAddField10(String addField10) {
		this.addField10 = addField10;
	}
	public String getPgRefCancelReqId() {
		return pgRefCancelReqId;
	}
	public void setPgRefCancelReqId(String pgRefCancelReqId) {
		this.pgRefCancelReqId = pgRefCancelReqId;
	}
	public String getRefundAmt() {
		return refundAmt;
	}
	public void setRefundAmt(String refundAmt) {
		this.refundAmt = refundAmt;
	}

}
