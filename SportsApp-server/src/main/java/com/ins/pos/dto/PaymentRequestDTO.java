package com.ins.pos.dto;

public class PaymentRequestDTO {

	private String merchantRequest;
	private String merchantID;

	public String getMerchantRequest() {
		return merchantRequest;
	}

	public void setMerchantRequest(String merchantRequest) {
		this.merchantRequest = merchantRequest;
	}

	public String getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

}
