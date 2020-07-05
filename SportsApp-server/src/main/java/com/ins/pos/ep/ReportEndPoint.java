package com.ins.pos.ep;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ins.pos.dto.AccountReportOutputDTO;
import com.ins.pos.dto.BookingReportOutputDTO;
import com.ins.pos.dto.ConsolidatedReportByDateOutputDTO;
import com.ins.pos.dto.ConsolidatedReportByFacilityOutputDTO;
import com.ins.pos.dto.MemberContactDetailsJsonDTO;
import com.ins.pos.dto.PaymentOrderReportOutputDTO;
import com.ins.pos.dto.SpotBookingReportOutputDTO;
import com.ins.pos.dto.SummaryReportOutputDTO;
import com.ins.pos.pdf.PDFGeneratorService;
import com.ins.pos.pdf.PDFRequest;
import com.ins.pos.pdf.Transactions;
import com.ins.pos.service.ReportService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@RestController
@RequestMapping("/api/report")
public class ReportEndPoint {

	@Autowired
	ReportService reportService;

	@Autowired
	private PDFGeneratorService pdfGeneratorService;

	@PostMapping("/getBookingSummary")
	public SummaryReportOutputDTO getBookingSummary(@RequestBody String data, HttpServletResponse response) {
		return reportService.getAllBookingForDays(data, response);
	}

	@PostMapping("/getBookingSummaryPDF")
	public void getBookingSummaryPDF(@RequestBody String data, HttpServletResponse response) {
		try {
			Transactions transaction = new Transactions();
			transaction.setSummaryReport(reportService.getAllBookingForDays(data, response));
			PDFRequest pdfReq = pdfGeneratorService.getPDFRequest(transaction, "Test.jasper");
			JasperPrint jasperPrint = pdfGeneratorService.createPdf(pdfReq);
			response.setContentType("application/x-pdf");
			response.setHeader("Content-disposition", "inline; filename=report.pdf");
			final OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/getSpotBookingForDays")
	public SpotBookingReportOutputDTO getSpotBookingForDays(@RequestBody String data, HttpServletResponse response) {
		return reportService.getSpotBookingForDays(data, response);
	}

	@PostMapping("/getSpotBookingForDaysPDF")
	public void getSpotBookingForDaysPDF(@RequestBody String data, HttpServletResponse response) {
		try {
			Transactions transaction = new Transactions();
			transaction.setSpotBookingReport(reportService.getSpotBookingForDays(data, response));
			PDFRequest pdfReq = pdfGeneratorService.getPDFRequest(transaction, "Test.jasper");
			JasperPrint jasperPrint = pdfGeneratorService.createPdf(pdfReq);
			response.setContentType("application/x-pdf");
			response.setHeader("Content-disposition", "inline; filename=report.pdf");
			final OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/getAllBookingForDays")
	public BookingReportOutputDTO getAllBookingForDays(@RequestBody String data, HttpServletResponse response) {
		return reportService.getBookingReport(data, response);
	}

	@PostMapping("/getAllBookingForDaysPDF")
	public void getAllBookingForDaysPDF(@RequestBody String data, HttpServletResponse response) {
		try {
			Transactions transaction = new Transactions();
			transaction.setBookingReport(reportService.getBookingReport(data, response));
			PDFRequest pdfReq = pdfGeneratorService.getPDFRequest(transaction, "Test.jasper");
			JasperPrint jasperPrint = pdfGeneratorService.createPdf(pdfReq);
			response.setContentType("application/x-pdf");
			response.setHeader("Content-disposition", "inline; filename=report.pdf");
			final OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/getAllAccountsForDays")
	public AccountReportOutputDTO getAllAccountsForDays(@RequestBody String data, HttpServletResponse response) {
		return reportService.getAccountReport(data, response);
	}

	@PostMapping("/getAllAccountsForDaysPDF")
	public void getAllAccountsForDaysPDF(@RequestBody String data, HttpServletResponse response) {
		try {
			Transactions transaction = new Transactions();
			transaction.setAccountReport(reportService.getAccountReport(data, response));
			PDFRequest pdfReq = pdfGeneratorService.getPDFRequest(transaction, "Test.jasper");
			JasperPrint jasperPrint = pdfGeneratorService.createPdf(pdfReq);
			response.setContentType("application/x-pdf");
			response.setHeader("Content-disposition", "inline; filename=report.pdf");
			final OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/getConsolidatedReportByDate")
	public ConsolidatedReportByDateOutputDTO getConsolidatedReportByDate(@RequestBody String data,
			HttpServletResponse response) {
		return reportService.getConsolidatedReportByDate(data, response);
	}

	@PostMapping("/getConsolidatedReportByDatePDF")
	public void getConsolidatedReportByDatePDF(@RequestBody String data, HttpServletResponse response) {
		try {
			Transactions transaction = new Transactions();
			transaction.setConsolidatedReportByDate(reportService.getConsolidatedReportByDate(data, response));
			PDFRequest pdfReq = pdfGeneratorService.getPDFRequest(transaction, "Test.jasper");
			JasperPrint jasperPrint = pdfGeneratorService.createPdf(pdfReq);
			response.setContentType("application/x-pdf");
			response.setHeader("Content-disposition", "inline; filename=report.pdf");
			final OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/getConsolidatedReportByFacility")
	public ConsolidatedReportByFacilityOutputDTO getConsolidatedReportByFacility(@RequestBody String data,
			HttpServletResponse response) {
		return reportService.getConsolidatedReportByFacility(data, response);
	}

	@PostMapping("/getConsolidatedReportByFacilityPDF")
	public void getConsolidatedReportByFacilityPDF(@RequestBody String data, HttpServletResponse response) {
		try {
			Transactions transaction = new Transactions();
			transaction.setConsolidatedReportByFacility(reportService.getConsolidatedReportByFacility(data, response));
			PDFRequest pdfReq = pdfGeneratorService.getPDFRequest(transaction, "Test.jasper");
			JasperPrint jasperPrint = pdfGeneratorService.createPdf(pdfReq);
			response.setContentType("application/x-pdf");
			response.setHeader("Content-disposition", "inline; filename=report.pdf");
			final OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostMapping("/getPaymentDetailsForDays")
	public PaymentOrderReportOutputDTO getPaymentDetailsForDays(@RequestBody String data,
			HttpServletResponse response) {
		return reportService.getPaymentDetailsForDays(data, response);
	}

	@PostMapping("/getPaymentDetailsForDaysPDF")
	public void getPaymentDetailsForDaysPDF(@RequestBody String data, HttpServletResponse response) {
		try {
			Transactions transaction = new Transactions();
			transaction.setPaymentOrderReport(reportService.getPaymentDetailsForDays(data, response));
			PDFRequest pdfReq = pdfGeneratorService.getPDFRequest(transaction, "Test.jasper");
			JasperPrint jasperPrint = pdfGeneratorService.createPdf(pdfReq);
			response.setContentType("application/x-pdf");
			response.setHeader("Content-disposition", "inline; filename=report.pdf");
			final OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/getActiveMemberContacts")
	public List<MemberContactDetailsJsonDTO> getActiveMemberContacts(@RequestBody String requestJSON,
			HttpServletResponse response) {
		return reportService.getAllMemberContactDetails(requestJSON, response);
	}

	@RequestMapping("/getActiveMemberContactsPDF")
	public void getActiveMemberContactsPDF(@RequestBody String requestJSON, HttpServletResponse response) {
		try {
			Transactions transaction = new Transactions();
			transaction.setGetActiveMemberContacts(reportService.getAllMemberContactDetails(requestJSON, response));
			PDFRequest pdfReq = pdfGeneratorService.getPDFRequest(transaction, "Test.jasper");
			JasperPrint jasperPrint = pdfGeneratorService.createPdf(pdfReq);
			response.setContentType("application/x-pdf");
			response.setHeader("Content-disposition", "inline; filename=report.pdf");
			final OutputStream outStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/triggerSMS")
	public String triggerSMS(@RequestBody String requestJSON) {
		return reportService.triggerSMS(requestJSON);
	}

}
