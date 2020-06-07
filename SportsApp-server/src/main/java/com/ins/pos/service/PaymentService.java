package com.ins.pos.service;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public interface PaymentService {

	ModelAndView processPaymentResponse(String data);

	String initiatePayment(String data, Model model);

}
