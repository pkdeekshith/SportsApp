package com.ins.pos.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.awl.merchanttoolkit.dto.ReqMsgDTO;
import com.awl.merchanttoolkit.dto.ResMsgDTO;
import com.awl.merchanttoolkit.transaction.AWLMEAPI;
import com.ins.pos.entity.AccountBook;
import com.ins.pos.entity.Accounts;
import com.ins.pos.entity.Booking;
import com.ins.pos.entity.Member;
import com.ins.pos.entity.PaymentOrderStatus;
import com.ins.pos.entity.PaymentLog;
import com.ins.pos.entity.PaymentRequest;
import com.ins.pos.entity.PaymentResponse;
import com.ins.pos.entity.User;
import com.ins.pos.repository.AccountBookRepository;
import com.ins.pos.repository.AccountsRepository;
import com.ins.pos.repository.BookingRepository;
import com.ins.pos.repository.MemberRepository;
import com.ins.pos.repository.PaymentLogRepository;
import com.ins.pos.repository.PaymentOrderStatusRepository;
import com.ins.pos.repository.PaymentRequestRepository;
import com.ins.pos.repository.PaymentResponseRepository;
import com.ins.pos.service.PaymentService;
import com.ins.pos.service.util.CommunicationUtil;

@Service
public class PaymentServiceImpl implements PaymentService {

	private final Logger LOGGER = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	CommunicationUtil communicationUtil;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	private PaymentLogRepository paymentLogRepository;

	@Value("${sportsapp.payment.encryptionKey}")
	private String encryptionKey;

	@Value("${sportsapp.payment.merchantID}")
	private String merchantID;

	@Value("${sportsapp.payment.paymentResponseURL}")
	private String paymentResponseURL;

	@Value("${sportsapp.payment.url}")
	private String url;

	@Value("${sportsapp.payment.redirectURL}")
	private String redirectURL;

	@Autowired
	private PaymentRequestRepository paymentRequestRepository;

	@Autowired
	private PaymentResponseRepository paymentResponseRepository;

	@Autowired
	private PaymentOrderStatusRepository paymentOrderStatusRepository;

	@Autowired
	CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

	@Autowired
	private AccountBookRepository accountBookRepository;

	@Autowired
	BookingRepository bookingRepository;

	@Override
	@Transactional
	public String initiatePayment(String data) {
		LOGGER.debug("initiatePayment : Input" + data);
		boolean isSuccess = false;
		JSONObject response = new JSONObject();
		PaymentLog paymentLog = new PaymentLog();
		paymentLog.setRequest(data);
		Double totalAmount = new Double(0);
		JSONObject req = new JSONObject(data);
		String memberId = req.getString("memberId");
		paymentLog.setMemberId(memberId);
		String isNewMember = req.getString("isNewMember");
		paymentLog = paymentLogRepository.saveAndFlush(paymentLog);
		Double amountDob = req.getDouble("amount");
		List<Long> accountIds = new ArrayList<Long>();
		JSONArray accountId = req.getJSONArray("accountId");
		Accounts accountId1 = null;
		Accounts accountId2 = null;
		accountId.forEach((a) -> {
			accountIds.add(new Long((Integer) a));
		});
		List<Accounts> accounts = accountsRepository.findByAccountsIdIn(accountIds);
		if (!accounts.isEmpty()) {
			int i = 0;
			for (Accounts a : accounts) {
				totalAmount = totalAmount + a.getPayableAmount();
				if (i == 0) {
					accountId1 = a;
				} else {
					accountId2 = a;
				}
				i++;
			}
			if (amountDob.equals(totalAmount)) {
				Member m = accounts.get(0).getMemberId();
				if (m.getMemberId() == Long.parseLong(memberId)) {
					ReqMsgDTO reqMsgDTO = new ReqMsgDTO();
					try {
						String orderId = generateOrderId(accountIds, String.valueOf(m.getMemberId()));
						PaymentOrderStatus paymenOrderStatus = paymentOrderStatusRepository.findByOrderId(orderId);
						if (paymenOrderStatus == null) {
							Optional<Member> member = memberRepository.findById(Long.parseLong(memberId));
							paymenOrderStatus = new PaymentOrderStatus();
							paymenOrderStatus.setAccountId1(accountId1);
							paymenOrderStatus.setAccountId2(accountId2);
							paymenOrderStatus.setMemberId(member.get());
							paymenOrderStatus.setOrderId(orderId);
							paymenOrderStatus.setTotalAmount(totalAmount);
							paymenOrderStatus.setStatus("I");
							paymenOrderStatus.setIsNewMember(isNewMember);
							paymenOrderStatus.setCreatedDate(new Date());
							paymenOrderStatus.setLeftPad(RandomStringUtils.randomAlphanumeric(6).toUpperCase());
							paymenOrderStatus.setRightPad(RandomStringUtils.randomAlphanumeric(6).toUpperCase());
							reqMsgDTO = generatePaymentRequest(orderId, totalAmount, "Payment :" + memberId);
							if ("Success".equals(reqMsgDTO.getStatusDesc())) {
								paymenOrderStatus.setMerchantReq(reqMsgDTO.getReqMsg());
								isSuccess = true;
							}
							paymenOrderStatus = paymentOrderStatusRepository.saveAndFlush(paymenOrderStatus);
							response.put("key",
									paymenOrderStatus.getLeftPad() + orderId + paymenOrderStatus.getRightPad());
						}

					} catch (Exception e) {
						LOGGER.error("Exception - ", e);
						isSuccess = false;
					}
				}
			}
		}
		if (isSuccess)
			response.put("status", "S");
		else
			response.put("status", "F");
		return response.toString();
	}

	@Override
	@Transactional
	public String proceedPayment(String encodedString, Model model) {
		String leftPad = encodedString.substring(0, 6);
		String restString = encodedString.substring(6);
		String orderId = restString.substring(0, restString.length() - 6);
		String rightPad = restString.substring(restString.length() - 6, restString.length());
		PaymentOrderStatus paymenOrderStatus = paymentOrderStatusRepository.findByLeftPadAndOrderIdAndRightPad(leftPad,
				orderId, rightPad);
		if (paymenOrderStatus != null) {
			if (TimeUnit.MILLISECONDS
					.toMinutes((new Date().getTime()) - paymenOrderStatus.getCreatedDate().getTime()) < 5) {
				model.addAttribute("paymentURL", url);
				model.addAttribute("mid", merchantID);
				model.addAttribute("merchantRequest", paymenOrderStatus.getMerchantReq());
				return "initiatePayment";
			} else {
				return "paymentFailure";
			}
		} else {
			return "paymentFailure";

		}
	}

	private String generateOrderId(List<Long> accountIds, String memberId) {
		StringBuffer orderId = new StringBuffer();
		orderId.append("M").append(memberId);
		for (Long accountId : accountIds) {
			orderId.append("AC").append(String.valueOf(accountId));
		}
		return orderId.toString();
	}

	@Override
	@Transactional
	public String initiatePayment(String memberId, String accountId, Model model) {
		String returnURL = "paymentFailure";
		Optional<Accounts> account = accountsRepository.findById(Long.parseLong(accountId));
		Member m = null;
		Accounts a = null;
		if (account.isPresent()) {
			a = account.get();
			m = a.getMemberId();
		}

		if (m != null && m.getMemberId().equals(Long.parseLong(memberId))) {
			ReqMsgDTO reqMsgDTO = new ReqMsgDTO();
			try {
				reqMsgDTO = generatePaymentRequest(accountId, a.getPaidAmount(), "Payment :" + accountId);
			} catch (Exception e) {
				LOGGER.error("Exception - ", e);

			}
			if ("Success".equals(reqMsgDTO.getStatusDesc())) {
				returnURL = "initiatePayment";
				model.addAttribute("url", url);
				model.addAttribute("mid", merchantID);
				model.addAttribute("merchantRequest", reqMsgDTO.getReqMsg());
			}
		}
		return returnURL;
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
		AWLMEAPI objAWLMEAPI = new AWLMEAPI();
		objReqMsgDTO = objAWLMEAPI.generateTrnReqMsg(objReqMsgDTO);
		PaymentRequest paymentRequest = new PaymentRequest();
		BeanUtils.copyProperties(objReqMsgDTO, paymentRequest);
		paymentRequestRepository.save(paymentRequest);
		return objReqMsgDTO;
	}

	@Override
	@Transactional
	public String processPaymentResponse(String merchantResponse, Model model, HttpServletResponse response) {
		AWLMEAPI objAWLMEAPI = new AWLMEAPI();
		ResMsgDTO objResMsgDTO;
		Map<String, String> output = new HashMap();
		try {
			objResMsgDTO = objAWLMEAPI.parseTrnResMsg(merchantResponse, encryptionKey);

			PaymentResponse paymentResponse = new PaymentResponse();
			BeanUtils.copyProperties(objResMsgDTO, paymentResponse);
			paymentResponseRepository.save(paymentResponse);
			PaymentOrderStatus paymenOrderStatus = paymentOrderStatusRepository
					.findByOrderId(objResMsgDTO.getOrderId());
			if (objResMsgDTO.getStatusCode().equals("S")) {

				paymenOrderStatus.setStatus("S");
				paymentOrderStatusRepository.saveAndFlush(paymenOrderStatus);
				Accounts a = paymenOrderStatus.getAccountId1();
				if (a != null) {
					a.setPaidDate(new Date());
					a.setReferenceNo(objResMsgDTO.getPgMeTrnRefNo());
					a.setPaidAmount(a.getPayableAmount());
					a.setActive(true);
					if (a.getOnHold()!=null&&a.getOnHold()) {
						a.setOnHold(false);
					}
					a = accountsRepository.save(a);
					List<AccountBook> accBooks = accountBookRepository.findByAccountsId(a);
					for (AccountBook accntBook : accBooks) {
						Booking b = accntBook.getBookingId();
						b.setActive(true);
						bookingRepository.save(b);
					}
					if("New Member".equals(a.getTypeOfBooking())||"Member Renewal".equals(a.getTypeOfBooking())) {
						paymenOrderStatus.getMemberId().setActive(true);
						memberRepository.save(paymenOrderStatus.getMemberId());
					}
				}
				if (paymenOrderStatus.getAccountId2() != null) {
					Accounts a1 = paymenOrderStatus.getAccountId2();
					if (a1 != null) {
						a1.setPaidDate(new Date());
						a1.setActive(true);
						a1.setReferenceNo(objResMsgDTO.getPgMeTrnRefNo());
						a1.setPaidAmount(a1.getPayableAmount());
						if (a1.getOnHold()!=null&&a1.getOnHold()) {
							a1.setOnHold(false);
						}
						a1 = accountsRepository.save(a1);
						List<AccountBook> accBooks = accountBookRepository.findByAccountsId(a1);
						for (AccountBook accntBook : accBooks) {
							Booking b = accntBook.getBookingId();
							b.setActive(true);
							bookingRepository.save(b);
						}
					}
				}
				if ("Y".equals(paymenOrderStatus.getIsNewMember())) {
					Member member = paymenOrderStatus.getMemberId();
					User user = new User();
					user.setUsername(member.getEmail());
					user.setEmail(member.getEmail());
					user.setPassword(member.getPassword());
					customUserDetailsServiceImpl.saveUser(user);
				}

				output.put("TransactionRefNo", objResMsgDTO.getPgMeTrnRefNo());
				output.put("OrderID", objResMsgDTO.getOrderId());
				output.put("TransactionDateTime", objResMsgDTO.getTrnReqDate());
				output.put("status", objResMsgDTO.getStatusCode());
				output.put("statusDesc", objResMsgDTO.getStatusDesc());
				output.put("redirectURL", redirectURL);
				model.addAllAttributes(output);

			} else {
				Accounts a = paymenOrderStatus.getAccountId1();
				if (a != null) {
					if (a.getOnHold()!=null&&a.getOnHold()) {
						a.setOnHold(false);
					}
					a = accountsRepository.save(a);
				}

				if (paymenOrderStatus.getAccountId2() != null) {
					Accounts a1 = paymenOrderStatus.getAccountId2();
					if (a1 != null) {
						if (a1.getOnHold()!=null&&a1.getOnHold()) {
							a1.setOnHold(false);
						}
						a1 = accountsRepository.save(a1);
					}
				}

				paymenOrderStatus.setStatus("F");
				paymentOrderStatusRepository.saveAndFlush(paymenOrderStatus);
				output.put("TransactionRefNo", objResMsgDTO.getPgMeTrnRefNo());
				output.put("OrderID", objResMsgDTO.getOrderId());
				output.put("TransactionDateTime", objResMsgDTO.getTrnReqDate());
				output.put("status", objResMsgDTO.getStatusCode());
				output.put("statusDesc", objResMsgDTO.getStatusDesc());
				output.put("redirectURL", redirectURL);
				model.addAllAttributes(output);
			}
		} catch (Exception e) {
			LOGGER.error("Exception - ", e);
		}
		return "paymentSuccess";
	}

	@Override
	public ResMsgDTO checkPaymentStatus(String data) {
		JSONObject request = new JSONObject(data);
		String orderId = request.getString("orderId");
		ResMsgDTO objResMsgDTO = null;
		PaymentResponse paymentResponse = paymentResponseRepository.findByOrderId(orderId);
		AWLMEAPI objAWLMEAPI = new AWLMEAPI();
		try {
			if (paymentResponse != null) {
				objResMsgDTO = objAWLMEAPI.getTransactionStatus(merchantID, orderId, paymentResponse.getPgMeTrnRefNo(),
						encryptionKey, "/");

			}
		} catch (Exception e) {
			LOGGER.error("Exception - ", e);
		}

		return objResMsgDTO;
	}

	@Override
	public ResMsgDTO cancelPayment(String data) {
		JSONObject request = new JSONObject(data);
		String orderId = request.getString("orderId");
		ResMsgDTO objResMsgDTO = null;
		PaymentResponse paymentResponse = paymentResponseRepository.findByOrderId(orderId);
		AWLMEAPI objAWLMEAPI = new AWLMEAPI();
		try {
			if (paymentResponse != null) {
				ReqMsgDTO objReqMsgDTO = new ReqMsgDTO();
				objReqMsgDTO.setOrderId(orderId);
				objReqMsgDTO.setMid(merchantID);
				objReqMsgDTO.setEnckey(encryptionKey);
				objReqMsgDTO.setPgMeTrnRefNo(paymentResponse.getPgMeTrnRefNo());
				objResMsgDTO = objAWLMEAPI.cancelTransaction(objReqMsgDTO, "/");
			}
		} catch (Exception e) {
			LOGGER.error("Exception - ", e);
		}

		return objResMsgDTO;
	}

	@Override
	public ResMsgDTO refundPayment(String data) {
		JSONObject request = new JSONObject(data);
		String orderId = request.getString("orderId");
		String partialRefund = request.getString("isPartialRefund");
		Double amountDob = request.getDouble("amount");
		ResMsgDTO objResMsgDTO = null;
		PaymentResponse paymentResponse = paymentResponseRepository.findByOrderId(orderId);
		AWLMEAPI objAWLMEAPI = new AWLMEAPI();
		try {
			if (paymentResponse != null) {
				ReqMsgDTO objReqMsgDTO = new ReqMsgDTO();

				objReqMsgDTO.setOrderId(orderId);
				objReqMsgDTO.setMid(merchantID);
				if ("Y".equals(partialRefund)) {
					Double amountPaisa = amountDob * 100;
					DecimalFormat decimalFormat = new DecimalFormat("#");
					objReqMsgDTO.setRefundAmt(decimalFormat.format(amountPaisa));
				} else {
					objReqMsgDTO.setRefundAmt(paymentResponse.getTrnAmt());
				}

				objReqMsgDTO.setPgMeTrnRefNo(paymentResponse.getPgMeTrnRefNo());
				objReqMsgDTO.setEnckey(encryptionKey);
				objResMsgDTO = objAWLMEAPI.refundTransaction(objReqMsgDTO, "/");
			}
		} catch (Exception e) {
			LOGGER.error("Exception - ", e);
		}

		return objResMsgDTO;
	}
}