package com.ins.pos.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import com.awl.merchanttoolkit.dto.ResMsgDTO;

public interface PaymentService {

	String processPaymentResponse(String data, Model model,HttpServletResponse response);

	String initiatePayment(String data);

	String initiatePayment(String memberId, String accountId, Model model);

	String proceedPayment(String encodedString, Model model);

	ResMsgDTO checkPaymentStatus(String data);

	ResMsgDTO cancelPayment(String data);

	ResMsgDTO refundPayment(String data);

}
