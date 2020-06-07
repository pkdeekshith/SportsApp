package com.ins.pos.dto;

import java.util.List;

public class AccountReportOutputDTO {

	private List<AccountReportDTO> data;
	private double totalCreditAmount;
	private double totalDebitAmount;
	private String status;

	public List<AccountReportDTO> getData() {
		return data;
	}

	public void setData(List<AccountReportDTO> data) {
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotalCreditAmount() {
		return totalCreditAmount;
	}

	public void setTotalCreditAmount(double totalCreditAmount) {
		this.totalCreditAmount = totalCreditAmount;
	}

	public double getTotalDebitAmount() {
		return totalDebitAmount;
	}

	public void setTotalDebitAmount(double totalDebitAmount) {
		this.totalDebitAmount = totalDebitAmount;
	}
}
