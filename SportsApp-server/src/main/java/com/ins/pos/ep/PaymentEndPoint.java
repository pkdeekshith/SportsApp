package com.ins.pos.ep;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.awl.merchanttoolkit.dto.ResMsgDTO;
import com.ins.pos.service.PaymentService;

@Controller
@RequestMapping("/api/payment")
public class PaymentEndPoint {

	@Autowired
	PaymentService paymentService;

	@PostMapping("/initiatePayment")
	public @ResponseBody String initiatePayment(@RequestBody String data) {
		return paymentService.initiatePayment(data);
	}
	
	@GetMapping("/proceedToPayment/{encodedString}")
	public String proceedPayment(@PathVariable("encodedString") String encodedString, Model model, HttpServletResponse response) {
		return paymentService.proceedPayment(encodedString,model);
	}
	
	@GetMapping("/initiateMemberPayment")
	public String initiateMemberPayment(@RequestParam("memberId") String memberId,@RequestParam("accountId") String accountId, Model model) {
		return paymentService.initiatePayment(memberId,accountId,model);
	}

	@PostMapping("/processPaymentResponse")
	public String initiateBookingPayment(HttpServletRequest request, Model model,HttpServletResponse response) {
		String merchantResponse = request.getParameter("merchantResponse");
		return paymentService.processPaymentResponse(merchantResponse,model,response);
	}
	
	@PostMapping("/checkPaymentStatus")
	public @ResponseBody ResMsgDTO checkPaymentStatus(@RequestBody String data) {
		return paymentService.checkPaymentStatus(data);
	}
	
	@PostMapping("/cancelPayment")
	public @ResponseBody ResMsgDTO cancelPayment(@RequestBody String data) {
		return paymentService.cancelPayment(data);
	}
	
	@PostMapping("/refundPayment")
	public @ResponseBody ResMsgDTO refundPayment(@RequestBody String data) {
		return paymentService.refundPayment(data);
	}
	
	
}
