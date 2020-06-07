package com.ins.pos.service;

import com.ins.pos.dto.AccountReportOutputDTO;
import com.ins.pos.dto.BookingReportOutputDTO;
import com.ins.pos.dto.ConsolidatedReportByDateOutputDTO;
import com.ins.pos.dto.ConsolidatedReportByFacilityOutputDTO;
import com.ins.pos.dto.SpotBookingReportOutputDTO;
import com.ins.pos.dto.SummaryReportOutputDTO;

public interface ReportService {

	public SummaryReportOutputDTO getAllBookingForDays(String data);

	public SpotBookingReportOutputDTO getSpotBookingForDays(String data);
	
	public BookingReportOutputDTO getBookingReport(String data);

	public AccountReportOutputDTO getAccountReport(String data);

	public ConsolidatedReportByDateOutputDTO getConsolidatedReportByDate(String data);

	public ConsolidatedReportByFacilityOutputDTO getConsolidatedReportByFacility(String data);
	
}
