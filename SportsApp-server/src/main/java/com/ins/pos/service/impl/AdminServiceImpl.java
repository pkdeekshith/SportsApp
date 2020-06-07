package com.ins.pos.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ins.pos.dto.FacilityDetailsJsonDTO;
import com.ins.pos.dto.FacilitySubFacilityDetailsJsonDTO;
import com.ins.pos.dto.OnlineBookingWindowDTO;
import com.ins.pos.dto.SubFacilityDetailJsonDTO;
import com.ins.pos.entity.Accounts;
import com.ins.pos.entity.Center;
import com.ins.pos.entity.Facility;
import com.ins.pos.entity.Member;
import com.ins.pos.entity.OnlineBookingWindow;
import com.ins.pos.entity.SubFacility;
import com.ins.pos.repository.AccountsRepository;
import com.ins.pos.repository.CenterRepository;
import com.ins.pos.repository.FacilityRepository;
import com.ins.pos.repository.MemberRepository;
import com.ins.pos.repository.OnlineBookingWindowRepository;
import com.ins.pos.repository.PriceRepository;
import com.ins.pos.repository.SubFacilityRepository;
import com.ins.pos.service.AdminService;
import com.ins.pos.service.util.CommunicationUtil;

@Service
public class AdminServiceImpl implements AdminService {

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
	private AccountsRepository accountsRepository;

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
			}
		} catch (JSONException e) {
			e.printStackTrace();
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
				}
				facilitySubFacilityJsonDTOArray.add(facilitySubFacilityJsonDTO);
			}
		} catch (JSONException e) {
			e.printStackTrace();
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
				}
			}
			status = "Success";
		} catch (Exception e) {

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
	public void sendCommunications(long accountId) {

		Optional<Accounts> account = accountsRepository.findById(accountId);
		account.ifPresent((a) -> {
			Member m = a.getMemberId();
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
		});

	}
}