package com.ins.pos.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ins.pos.dto.AccountReportOutputDTO;
import com.ins.pos.dto.BookingReportOutputDTO;
import com.ins.pos.dto.ConsolidatedReportByDateOutputDTO;
import com.ins.pos.dto.ConsolidatedReportByFacilityOutputDTO;
import com.ins.pos.dto.MemberContactDetailsJsonDTO;
import com.ins.pos.dto.PaymentOrderReportOutputDTO;
import com.ins.pos.dto.SpotBookingReportOutputDTO;
import com.ins.pos.dto.SummaryReportOutputDTO;

public interface ReportService {

	public SummaryReportOutputDTO getAllBookingForDays(String data,HttpServletResponse response);

	public SpotBookingReportOutputDTO getSpotBookingForDays(String data,HttpServletResponse response);
	
	public BookingReportOutputDTO getBookingReport(String data,HttpServletResponse response);

	public AccountReportOutputDTO getAccountReport(String data,HttpServletResponse response);

	public ConsolidatedReportByDateOutputDTO getConsolidatedReportByDate(String data,HttpServletResponse response);

	public ConsolidatedReportByFacilityOutputDTO getConsolidatedReportByFacility(String data,HttpServletResponse response);

	public PaymentOrderReportOutputDTO getPaymentDetailsForDays(String data,HttpServletResponse response);
	
	public List<MemberContactDetailsJsonDTO> getAllMemberContactDetails(String data,HttpServletResponse response);

	public String triggerSMS(String requestJSON);
	
}
