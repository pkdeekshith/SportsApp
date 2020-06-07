package com.ins.pos.ep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ins.pos.service.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentEndPoint {

	@Autowired
	PaymentService paymentService;

	@PostMapping("/initiateMemberPayment")
	public String initiateMemberPayment(@RequestBody String data, Model model) {
		return paymentService.initiatePayment(data,model);
	}

	@GetMapping("/processPaymentResponse")
	public ModelAndView initiateBookingPayment(@RequestParam("merchantResponse") String merchantResponse) {
		return paymentService.processPaymentResponse(merchantResponse);
	}
}
