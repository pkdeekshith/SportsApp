package com.ins.pos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class PaymentRequest {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column
	private String orderId;

	@Column
	private String mid;

	@Column
	private String trnAmt;

	@Column
	private String trnCurrency;

	@Column
	private String trnRemarks;

	@Column
	private String meTransReqType;

	@Column
	private String recurrPeriod;

	@Column
	private String recurrDay;

	@Column
	private String noOfRecurring;

	@Column
	private String responseUrl;

	@Column
	private String addField1 = "";

	@Column
	private String addField2 = "";

	@Column
	private String addField3 = "";

	@Column
	private String addField4 = "";

	@Column
	private String addField5 = "";

	@Column
	private String addField6 = "";

	@Column
	private String addField7 = "";

	@Column
	private String addField8 = "";

	@Column
	private String addField9 = "NA";

	@Column
	private String addField10 = "NA";

	@Column(columnDefinition = "TEXT")
	private String reqMsg;

	@Column
	private String enckey;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getTrnAmt() {
		return trnAmt;
	}

	public void setTrnAmt(String trnAmt) {
		this.trnAmt = trnAmt;
	}

	public String getTrnCurrency() {
		return trnCurrency;
	}

	public void setTrnCurrency(String trnCurrency) {
		this.trnCurrency = trnCurrency;
	}

	public String getTrnRemarks() {
		return trnRemarks;
	}

	public void setTrnRemarks(String trnRemarks) {
		this.trnRemarks = trnRemarks;
	}

	public String getMeTransReqType() {
		return meTransReqType;
	}

	public void setMeTransReqType(String meTransReqType) {
		this.meTransReqType = meTransReqType;
	}

	public String getRecurrPeriod() {
		return recurrPeriod;
	}

	public void setRecurrPeriod(String recurrPeriod) {
		this.recurrPeriod = recurrPeriod;
	}

	public String getRecurrDay() {
		return recurrDay;
	}

	public void setRecurrDay(String recurrDay) {
		this.recurrDay = recurrDay;
	}

	public String getNoOfRecurring() {
		return noOfRecurring;
	}

	public void setNoOfRecurring(String noOfRecurring) {
		this.noOfRecurring = noOfRecurring;
	}

	public String getResponseUrl() {
		return responseUrl;
	}

	public void setResponseUrl(String responseUrl) {
		this.responseUrl = responseUrl;
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

	public String getReqMsg() {
		return reqMsg;
	}

	public void setReqMsg(String reqMsg) {
		this.reqMsg = reqMsg;
	}

	public String getEnckey() {
		return enckey;
	}

	public void setEnckey(String enckey) {
		this.enckey = enckey;
	}

}
