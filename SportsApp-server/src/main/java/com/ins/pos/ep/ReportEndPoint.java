package com.ins.pos.ep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ins.pos.dto.AccountReportOutputDTO;
import com.ins.pos.dto.BookingReportOutputDTO;
import com.ins.pos.dto.ConsolidatedReportByDateOutputDTO;
import com.ins.pos.dto.ConsolidatedReportByFacilityOutputDTO;
import com.ins.pos.dto.SpotBookingReportOutputDTO;
import com.ins.pos.dto.SummaryReportOutputDTO;
import com.ins.pos.service.ReportService;

@RestController
@RequestMapping("/api/report")
public class ReportEndPoint { 
	
	@Autowired
	ReportService reportService;
	
	@PostMapping("/getBookingSummary")
	public SummaryReportOutputDTO getBookingSummary(@RequestBody String data) {
		return reportService.getAllBookingForDays(data);
	}
	
	@PostMapping("/getSpotBookingForDays")
	public SpotBookingReportOutputDTO getSpotBookingForDays(@RequestBody String data) {
		return reportService.getSpotBookingForDays(data);
	}
	
	@PostMapping("/getAllBookingForDays")
	public BookingReportOutputDTO getAllBookingForDays(@RequestBody String data) {
		return reportService.getBookingReport(data);
	}
	
	@PostMapping("/getAllAccountsForDays")
	public AccountReportOutputDTO getAllAccountsForDays(@RequestBody String data) {
		return reportService.getAccountReport(data);
	}
	
	@PostMapping("/getConsolidatedReportByDate")
	public ConsolidatedReportByDateOutputDTO getConsolidatedReportByDate(@RequestBody String data) {
		return reportService.getConsolidatedReportByDate(data);
	}
	
	@PostMapping("/getConsolidatedReportByFacility")
	public ConsolidatedReportByFacilityOutputDTO getConsolidatedReportByFacility(@RequestBody String data) {
		return reportService.getConsolidatedReportByFacility(data);
	}
	
	
}
