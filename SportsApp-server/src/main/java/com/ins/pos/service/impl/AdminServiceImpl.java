package com.ins.pos.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import com.ins.pos.dto.CenterDetailsJsonDTO;
import com.ins.pos.dto.FacilityDetailsJsonDTO;
import com.ins.pos.dto.FacilitySubFacilityDetailsJsonDTO;
import com.ins.pos.dto.OnlineBookingWindowDTO;
import com.ins.pos.dto.SendCommunicationFormDTO;
import com.ins.pos.dto.SubFacilityDetailJsonDTO;
import com.ins.pos.entity.Center;
import com.ins.pos.entity.CommunicationLog;
import com.ins.pos.entity.Facility;
import com.ins.pos.entity.Member;
import com.ins.pos.entity.OnlineBookingWindow;
import com.ins.pos.entity.PaymentOrderStatus;
import com.ins.pos.entity.PaymentResponse;
import com.ins.pos.entity.SubFacility;
import com.ins.pos.repository.CenterRepository;
import com.ins.pos.repository.CommunicationLogRepository;
import com.ins.pos.repository.FacilityRepository;
import com.ins.pos.repository.MemberRepository;
import com.ins.pos.repository.OnlineBookingWindowRepository;
import com.ins.pos.repository.PaymentOrderStatusRepository;
import com.ins.pos.repository.PaymentResponseRepository;
import com.ins.pos.repository.PriceRepository;
import com.ins.pos.repository.SubFacilityRepository;
import com.ins.pos.service.AdminService;
import com.ins.pos.service.util.CommunicationUtil;

@Service
public class AdminServiceImpl implements AdminService {

	private final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Autowired
	private FacilityRepository facilityRepository;

	@Autowired
	private CenterRepository centerRepository;

	@Autowired
	SubFacilityRepository subFacilityRepository;

	@Autowired
	PriceRepository priceRepository;

	@Autowired
	OnlineBookingWindowRepository onlineBookingWindowRepository;

	@Autowired
	CommunicationUtil communicationUtil;

	@Autowired
	MemberRepository memberRepository;

	@Autowired
	private PaymentOrderStatusRepository paymentOrderStatusRepository;

	@Autowired
	private PaymentResponseRepository paymentResponseRepository;

	@Autowired
	private CommunicationLogRepository communicationLogRepository;

	@Value("${sportsapp.payment.appURL}")
	private String appURL;

	@Override
	public List<FacilityDetailsJsonDTO> getAllFacilities(String data) {
		List<FacilityDetailsJsonDTO> facilityJsonDTOList = new ArrayList<FacilityDetailsJsonDTO>();
		try {
			JSONObject jsonData = new JSONObject(data);
			String centerId = jsonData.getString("centerId");
			Optional<Center> center = centerRepository.findById(Long.parseLong(centerId));
			if (center.isPresent()) {
				List<Facility> facilityList = facilityRepository.findByCenterId(center.get());
				for (Facility facility : facilityList) {
					FacilityDetailsJsonDTO facilityJsonDTO = new FacilityDetailsJsonDTO();
					BeanUtils.copyProperties(facility, facilityJsonDTO);
					facilityJsonDTOList.add(facilityJsonDTO);
				}
			} else {
				LOGGER.error("Center -" + centerId + " does nt exist!!");
			}
		} catch (JSONException e) {
			LOGGER.error("Exception", e);
		}

		return facilityJsonDTOList;
	}

	@Override
	public List<FacilitySubFacilityDetailsJsonDTO> getSubFacilitiesForFacilities(String data) {
		List<FacilitySubFacilityDetailsJsonDTO> facilitySubFacilityJsonDTOArray = new ArrayList<FacilitySubFacilityDetailsJsonDTO>();

		try {
			JSONObject jsonData = new JSONObject(data);
			JSONArray facilityList = jsonData.getJSONArray("facility");
			for (Object value : facilityList) {
				FacilitySubFacilityDetailsJsonDTO facilitySubFacilityJsonDTO = new FacilitySubFacilityDetailsJsonDTO();
				Optional<Facility> facility = facilityRepository.findById(Long.valueOf((String) value));
				if (facility.isPresent()) {
					BeanUtils.copyProperties(facility.get(), facilitySubFacilityJsonDTO);
					List<SubFacility> subFacilityList = subFacilityRepository.findByFacilityId(facility.get());
					List<SubFacilityDetailJsonDTO> subFacilityJsonDTOList = new ArrayList<SubFacilityDetailJsonDTO>();
					for (SubFacility subFacility : subFacilityList) {
						SubFacilityDetailJsonDTO subFacilityJsonDTO = new SubFacilityDetailJsonDTO();
						BeanUtils.copyProperties(subFacility, subFacilityJsonDTO);
						subFacilityJsonDTOList.add(subFacilityJsonDTO);
					}
					facilitySubFacilityJsonDTO.setSubFacility(subFacilityJsonDTOList);
				} else {
					LOGGER.error("Facility -" + (String) value + " does nt exist!!");
				}
				facilitySubFacilityJsonDTOArray.add(facilitySubFacilityJsonDTO);
			}
		} catch (JSONException e) {
			LOGGER.error("Exception", e);
		}
		return facilitySubFacilityJsonDTOArray;

	}

	@Override
	public String updateFacilities(List<FacilityDetailsJsonDTO> data) {
		String status = "Failure";
		JSONObject response = new JSONObject();
		try {
			for (FacilityDetailsJsonDTO facility : data) {
				Optional<Facility> facilityOpt = facilityRepository.findById(facility.getFacilityId());
				if (facilityOpt.isPresent()) {
					Facility fac = facilityOpt.get();
					fac.setActive(facility.getActive());
					fac.setOnlineActive(facility.getOnlineActive());
					facilityRepository.save(fac);
				} else {
					LOGGER.error("Facility -" + facility.getFacilityId() + " does nt exist!!");
				}
			}
			status = "Success";
		} catch (Exception e) {
			LOGGER.error("Exception", e);
		}
		response.put("status", status);
		return response.toString();
	}

	@Override
	public String updateSubFacilities(List<SubFacilityDetailJsonDTO> data) {
		String status = "Failure";
		JSONObject response = new JSONObject();
		try {
			for (SubFacilityDetailJsonDTO facility : data) {
				Optional<SubFacility> facilityOpt = subFacilityRepository.findById(facility.getSubFacilityId());
				if (facilityOpt.isPresent()) {
					SubFacility fac = facilityOpt.get();
					fac.setActive(facility.getActive());
					fac.setOnlineActive(facility.getOnlineActive());
					subFacilityRepository.save(fac);
				}
			}
			status = "Success";
		} catch (Exception e) {
			LOGGER.error("Exception", e);
		}
		response.put("status", status);
		return response.toString();
	}

	@Override
	public List<OnlineBookingWindowDTO> getOnlineBookingWindow() {
		List<OnlineBookingWindowDTO> list = new ArrayList<OnlineBookingWindowDTO>();
		Iterable<OnlineBookingWindow> iterable = onlineBookingWindowRepository.findAll();
		iterable.forEach((s) -> {
			OnlineBookingWindowDTO dto = new OnlineBookingWindowDTO();
			dto.setActive(s.getActive());
			dto.setBookingEndDate(s.getBookingEndDate());
			dto.setBookingStartDate(s.getBookingStartDate());
			dto.setFacilityId(s.getFacilityId().getFacilityId());
			dto.setFacilityName(s.getFacilityId().getFacilityName());
			list.add(dto);
		});
		return list;
	}

	@Override
	public String saveBookingWindow(List<OnlineBookingWindowDTO> data) {
		JSONObject response = new JSONObject();
		String status = "Failure";
		data.forEach((s) -> {
			Optional<Facility> facilityOpt = facilityRepository.findById(s.getFacilityId());
			if (facilityOpt.isPresent()) {
				OnlineBookingWindow onlineBookingWindow = onlineBookingWindowRepository
						.findByFacilityId(facilityOpt.get());
				onlineBookingWindow.setActive(s.getActive());
				onlineBookingWindow.setBookingStartDate(s.getBookingStartDate());
				onlineBookingWindow.setBookingEndDate(s.getBookingEndDate());
				onlineBookingWindowRepository.save(onlineBookingWindow);
			}
		});
		status = "Success";
		response.put("status", status);
		return response.toString();
	}

	@Override
	public RedirectView sendCommunications(SendCommunicationFormDTO request, HttpServletResponse response) {
		try {
			if (communicationLogRepository.findByOrderId(request.getOrderID()) == null) {
				PaymentOrderStatus paymenOrderStatus = paymentOrderStatusRepository.findByOrderId(request.getOrderID());
				if ("S".equals(paymenOrderStatus.getStatus()) && "Y".equals(paymenOrderStatus.getIsNewMember())) {
					PaymentResponse paymentResponse = paymentResponseRepository.findByOrderId(request.getOrderID());
					if (paymenOrderStatus.getMemberId() != null) {
						Member m = paymenOrderStatus.getMemberId();
						String subject = "SportsApp - Payment successfull - Account created for " + m.getMemberName();
						String smsBody = "SportsApp - Payment is successfull and account has been created. One time password is "+m.getPassword();
						String mailbody = "<h2><strong style=\"color: #000;\">Dear ${name} ,</strong></h2><h3 style=\"color: #4485b8;\">Payment is successfull and account has been created for you within SportsApp!</h3><h5></h5><h4>Please follow these instructions for logging in to the system.</h4><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Browse to ${url}</h5><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Enter your credentials</h5><h5><strong >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;User ID: ${userId}</strong></h5><h5><strong >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Password: ${password}</strong></h5><h5>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Payment Reference ID: ${refID}</h5><h5></h5><h4 style=\"color: #4485b8;\">Sincerely,<br>Administrator</p></h4>";
						mailbody = mailbody.replace("${name}", m.getMemberName()).replace("${userId}", m.getUserName())
								.replace("${password}", m.getPassword())
								.replace("${refID}", paymentResponse.getPgMeTrnRefNo()).replace("${url}", appURL);
						boolean emailStatus = communicationUtil.sendEmail(m.getEmail(), subject, mailbody);
						boolean smsStatus = communicationUtil.sendSms(
								smsBody, m.getMemberContactNo());
						CommunicationLog communicationLog = new CommunicationLog();
						communicationLog.setCreatedDate(new Date());
						communicationLog.setMailBody(mailbody);
						communicationLog.setMailId(m.getEmail());
						communicationLog.setMailStatus(emailStatus);
						communicationLog.setOrderId(request.getOrderID());
						communicationLog.setPhoneNumber(m.getMemberContactNo());
						communicationLog.setSmsContent(smsBody);
						communicationLog.setSmsStatus(smsStatus);
						communicationLogRepository.save(communicationLog);
					}
				} else if ("Y".equals(paymenOrderStatus.getIsNewMember())) {
					if (paymenOrderStatus.getMemberId() != null) {
						Member m = paymenOrderStatus.getMemberId();
						String subject = "Failed Payment on SportsApp";
						String smsBody = "SportsApp - We are sorry that your payment was not successful. Please check your mail for details.";
						String mailbody = "<h2><strong style=\"color: #000;\">Dear ${name} ,</strong></h2><h3 style=\"color: #4485b8;\">We are sorry that your payment was not successful.</h3><h5></h5><h4>The amount for the said transaction has not been charged by us. In case, the amount has been charged, the refund will be processed by your respective bank.</h4><h4 style=\"color: #4485b8;\">Sincerely,<br>Administrator</p></h4>";
						mailbody = mailbody.replace("${name}", m.getMemberName());
						boolean emailStatus = communicationUtil.sendEmail(m.getEmail(), subject, mailbody);
						boolean smsStatus = communicationUtil.sendSms(
								smsBody,  m.getMemberContactNo());
						CommunicationLog communicationLog = new CommunicationLog();
						communicationLog.setCreatedDate(new Date());
						communicationLog.setMailBody(mailbody);
						communicationLog.setMailId(m.getEmail());
						communicationLog.setMailStatus(emailStatus);
						communicationLog.setOrderId(request.getOrderID());
						communicationLog.setPhoneNumber("91" + m.getMemberContactNo());
						communicationLog.setSmsContent(smsBody);
						communicationLog.setSmsStatus(smsStatus);
						communicationLogRepository.save(communicationLog);

					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception - ", e);
		}
		return new RedirectView("/", true);
	}

	@Override
	public List<CenterDetailsJsonDTO> getAllCenters() {
		List<CenterDetailsJsonDTO> centerJsonDTOList = new ArrayList<CenterDetailsJsonDTO>();
		try {
			Iterable<Center> centers = centerRepository.findAll();
			centers.forEach((c) -> {
				CenterDetailsJsonDTO centerDetailsJsonDTO = new CenterDetailsJsonDTO();
				centerDetailsJsonDTO.setCentreId(c.getCentreId());
				centerDetailsJsonDTO.setCentreName(c.getCentreName());
				centerDetailsJsonDTO.setActive(c.getActive());
				centerDetailsJsonDTO.setOnlineActive(c.getOnlineActive());
				centerJsonDTOList.add(centerDetailsJsonDTO);
			});
		} catch (JSONException e) {
			LOGGER.error("Exception", e);
		}

		return centerJsonDTOList;
	}

	@Override
	public String updateCenters(List<CenterDetailsJsonDTO> data) {

		String status = "Failure";
		JSONObject response = new JSONObject();
		try {
			for (CenterDetailsJsonDTO center : data) {
				Optional<Center> centerOpt = centerRepository.findById(center.getCentreId());
				centerOpt.ifPresent((c) -> {
					c.setActive(center.getActive());
					c.setOnlineActive(center.getOnlineActive());
					centerRepository.save(c);
				});
			}
			status = "Success";
		} catch (Exception e) {
			LOGGER.error("Exception", e);
		}
		response.put("status", status);
		return response.toString();

	}
}