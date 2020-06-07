package com.ins.pos.service.impl;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Optional;

import javax.mail.MessagingException;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.awl.merchanttoolkit.dto.ReqMsgDTO;
import com.awl.merchanttoolkit.dto.ResMsgDTO;
import com.awl.merchanttoolkit.transaction.AWLMEAPI;
import com.ins.pos.dto.PaymentRequestDTO;
import com.ins.pos.entity.Accounts;
import com.ins.pos.entity.Member;
import com.ins.pos.entity.PaymentRequest;
import com.ins.pos.entity.PaymentResponse;
import com.ins.pos.repository.AccountsRepository;
import com.ins.pos.repository.MemberRepository;
import com.ins.pos.repository.PaymentRequestRepository;
import com.ins.pos.repository.PaymentResponseRepository;
import com.ins.pos.service.PaymentService;
import com.ins.pos.service.util.CommunicationUtil;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	CommunicationUtil communicationUtil;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	private AccountsRepository accountsRepository;

	@Value("${sportsapp.payment.encryptionKey}")
	private String encryptionKey;

	@Value("${sportsapp.payment.merchantID}")
	private String merchantID;

	@Value("${sportsapp.payment.paymentResponseURL}")
	private String paymentResponseURL;

	@Value("${sportsapp.payment.url}")
	private String url;

	@Autowired
	private PaymentRequestRepository paymentRequestRepository;

	@Autowired
	private PaymentResponseRepository paymentResponseRepository;

	public void sendCommunications(Accounts a, Member m) {

		String subject = "SportsApp - Payment successfull - Account created for " + m.getMemberName();
		String smsBody = "SportsApp - Payment is successfull and account has been created. Please check your mail for login details.";
		String mailbody = "<h2><strong style=\"color: #000;\">Dear ${name} ,</strong></h2><h3 style=\"color: #4485b8;\">Payment is successfull and account has been created for you within SportsApp!</h3><h5></h5><h4>Please follow these instructions for logging in to the system.</h4><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Browse to ${url}</h5><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Enter your credentials</h5><h5><strong >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;User ID: ${userId}</strong></h5><h5><strong >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Password: ${password}</strong></h5><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Payment Reference ID: ${refID}</h5><h5></h5><h4 style=\"color: #4485b8;\">Sincerely,<br>Administrator</p></h4>";
		mailbody = mailbody.replace("${name}", m.getMemberName()).replace("${userId}", m.getUserName())
				.replace("${password}", m.getPassword()).replace("${refID}", "Dummy code")
				.replace("${url}", "http://15.206.200.143:8080/SportsApp");
		try {
			communicationUtil.sendEmail(m.getEmail(), subject, mailbody);
			communicationUtil.sendSms("ygWI8sO+qIc-M4uDF2kFDvD7Nc8BpLbloZyF7zZOic", smsBody, "TXTLCL",
					"91" + m.getMemberContactNo());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String initiatePayment(String data, Model model) {
		JSONObject req = new JSONObject(data);
		JSONObject res = new JSONObject();
		String memberId = req.getString("memberId");
		String accountId = req.getString("accountId");
		Optional<Accounts> account = accountsRepository.findById(Long.parseLong(accountId));
		account.ifPresent((a) -> {
			Member m = a.getMemberId();
			if (m.getMemberId() == Long.parseLong(memberId)) {
				ReqMsgDTO reqMsgDTO = new ReqMsgDTO();
				try {
					reqMsgDTO = generatePaymentRequest(accountId, a.getPaidAmount(), "Payment :" + accountId);
				} catch (Exception e) {
					e.printStackTrace();
					res.put("status", "Failure");
				}
				if ("Success".equals(reqMsgDTO.getStatusDesc())) {
					//res.put("status", "Success");
					//res.put("merchantRequest", reqMsgDTO.getReqMsg());
					//res.put("merchantID", merchantID);
					//res.put("paymentURL", url);
					PaymentRequestDTO paymentRequest = new PaymentRequestDTO();
					paymentRequest.setMerchantID(merchantID);
					paymentRequest.setMerchantRequest(reqMsgDTO.getReqMsg());
					model.addAttribute("paymentRequest", paymentRequest);
				} else {
					res.put("status", "Failure");

				}
			}

		});
		return "paymentSubmit";
	}

	private ReqMsgDTO generatePaymentRequest(String orderId, Double amount, String remarks) throws Exception {
		ReqMsgDTO objReqMsgDTO = new ReqMsgDTO();
		Double amountPaisa = amount * 100;
		DecimalFormat decimalFormat = new DecimalFormat("#");
		objReqMsgDTO.setOrderId(orderId);
		objReqMsgDTO.setMid(merchantID);
		objReqMsgDTO.setTrnAmt(decimalFormat.format(amountPaisa));
		objReqMsgDTO.setTrnCurrency("INR");
		objReqMsgDTO.setMeTransReqType("S");
		objReqMsgDTO.setEnckey(encryptionKey);
		objReqMsgDTO.setResponseUrl(paymentResponseURL);
		objReqMsgDTO.setTrnRemarks(remarks);
		objReqMsgDTO.setRecurrDay("");
		objReqMsgDTO.setRecurrPeriod("");
		objReqMsgDTO.setNoOfRecurring("");
		/*objReqMsgDTO.setAddField1("");
		objReqMsgDTO.setAddField2("");
		objReqMsgDTO.setAddField3("");
		objReqMsgDTO.setAddField4("");
		objReqMsgDTO.setAddField5("");
		objReqMsgDTO.setAddField6("");
		objReqMsgDTO.setAddField7("");
		objReqMsgDTO.setAddField8("");*/
		AWLMEAPI objAWLMEAPI = new AWLMEAPI();
		objReqMsgDTO = objAWLMEAPI.generateTrnReqMsg(objReqMsgDTO);
		PaymentRequest paymentRequest = new PaymentRequest();
		BeanUtils.copyProperties(objReqMsgDTO, paymentRequest);
		paymentRequestRepository.save(paymentRequest);
		return objReqMsgDTO;
	}

	@Override
	public ModelAndView processPaymentResponse(String merchantResponse) {
		AWLMEAPI objAWLMEAPI = new AWLMEAPI();
		ResMsgDTO objResMsgDTO;
		try {
			objResMsgDTO = objAWLMEAPI.parseTrnResMsg(merchantResponse, encryptionKey);

			PaymentResponse paymentResponse = new PaymentResponse();
			BeanUtils.copyProperties(objResMsgDTO, paymentResponse);
			paymentResponseRepository.save(paymentResponse);
			if (objResMsgDTO.getStatusCode().equals("S")) {
				Optional<Accounts> account = accountsRepository.findById(Long.parseLong(objResMsgDTO.getOrderId()));
				if (account.isPresent()) {
					Accounts a = account.get();
					a.setPaidDate(new Date());
					a.setReferenceNo(objResMsgDTO.getPgMeTrnRefNo());
					a.setPaidAmount(new Double(objResMsgDTO.getTrnAmt()));
					accountsRepository.save(a);
					return new ModelAndView("redirect:http://localhost:8080/SportsApp");
				}
			} else {
				return new ModelAndView("redirect:http://localhost:8080/SportsApp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("redirect:http://localhost:8080/SportsApp");
		}
		return null;
	}

}