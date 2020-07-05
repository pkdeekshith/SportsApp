package com.ins.pos.pdf;

import java.util.List;
import java.util.Map;

import com.ins.pos.dto.AccountReportOutputDTO;
import com.ins.pos.dto.BookingReportOutputDTO;
import com.ins.pos.dto.ConsolidatedReportByDateOutputDTO;
import com.ins.pos.dto.ConsolidatedReportByFacilityOutputDTO;
import com.ins.pos.dto.MemberContactDetailsJsonDTO;
import com.ins.pos.dto.PaymentOrderReportOutputDTO;
import com.ins.pos.dto.SpotBookingReportOutputDTO;
import com.ins.pos.dto.SummaryReportOutputDTO;

public class Transactions {
	ConsolidatedReportByDateOutputDTO consolidatedReportByDate;
	ConsolidatedReportByFacilityOutputDTO consolidatedReportByFacility;
	SpotBookingReportOutputDTO spotBookingReport;
	SummaryReportOutputDTO summaryReport;
	BookingReportOutputDTO bookingReport;
	AccountReportOutputDTO accountReport;
	Map<String, String> templateMap;
	PaymentOrderReportOutputDTO paymentOrderReport;
	List<MemberContactDetailsJsonDTO> getActiveMemberContacts;

	public ConsolidatedReportByDateOutputDTO getConsolidatedReportByDate() {
		return consolidatedReportByDate;
	}

	public void setConsolidatedReportByDate(ConsolidatedReportByDateOutputDTO consolidatedReportByDate) {
		this.consolidatedReportByDate = consolidatedReportByDate;
	}

	public ConsolidatedReportByFacilityOutputDTO getConsolidatedReportByFacility() {
		return consolidatedReportByFacility;
	}

	public void setConsolidatedReportByFacility(ConsolidatedReportByFacilityOutputDTO consolidatedReportByFacility) {
		this.consolidatedReportByFacility = consolidatedReportByFacility;
	}

	public SpotBookingReportOutputDTO getSpotBookingReport() {
		return spotBookingReport;
	}

	public void setSpotBookingReport(SpotBookingReportOutputDTO spotBookingReport) {
		this.spotBookingReport = spotBookingReport;
	}

	public SummaryReportOutputDTO getSummaryReport() {
		return summaryReport;
	}

	public void setSummaryReport(SummaryReportOutputDTO summaryReport) {
		this.summaryReport = summaryReport;
	}

	public BookingReportOutputDTO getBookingReport() {
		return bookingReport;
	}

	public void setBookingReport(BookingReportOutputDTO bookingReport) {
		this.bookingReport = bookingReport;
	}

	public AccountReportOutputDTO getAccountReport() {
		return accountReport;
	}

	public void setAccountReport(AccountReportOutputDTO accountReport) {
		this.accountReport = accountReport;
	}

	public Map<String, String> getTemplateMap() {
		return templateMap;
	}

	public void setTemplateMap(Map<String, String> templateMap) {
		this.templateMap = templateMap;
	}

	public PaymentOrderReportOutputDTO getPaymentOrderReport() {
		return paymentOrderReport;
	}

	public void setPaymentOrderReport(PaymentOrderReportOutputDTO paymentOrderReport) {
		this.paymentOrderReport = paymentOrderReport;
	}

	public List<MemberContactDetailsJsonDTO> getGetActiveMemberContacts() {
		return getActiveMemberContacts;
	}

	public void setGetActiveMemberContacts(List<MemberContactDetailsJsonDTO> getActiveMemberContacts) {
		this.getActiveMemberContacts = getActiveMemberContacts;
	}

}
