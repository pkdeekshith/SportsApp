package com.ins.pos.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ins.pos.dto.FacilityTypeJsonDTO;
import com.ins.pos.dto.MemberContactDetailsJsonDTO;
import com.ins.pos.dto.MemberDetailsJsonDTO;
import com.ins.pos.dto.MemberJsonDTO;
import com.ins.pos.dto.MemberShipTypeJsonDTO;
import com.ins.pos.entity.Accounts;
import com.ins.pos.entity.Branch;
import com.ins.pos.entity.Center;
import com.ins.pos.entity.CommunicationLog;
import com.ins.pos.entity.FacilityType;
import com.ins.pos.entity.Member;
import com.ins.pos.entity.MemberCenter;
import com.ins.pos.entity.MemberFacility;
import com.ins.pos.entity.MemberShipType;
import com.ins.pos.entity.Role;
import com.ins.pos.entity.User;
import com.ins.pos.repository.AccountsRepository;
import com.ins.pos.repository.CenterRepository;
import com.ins.pos.repository.CommunicationLogRepository;
import com.ins.pos.repository.FacilityTypeRepository;
import com.ins.pos.repository.MemberCenterRepository;
import com.ins.pos.repository.MemberFacilityRepository;
import com.ins.pos.repository.MemberRepository;
import com.ins.pos.repository.MemberShipTypeRepository;
import com.ins.pos.repository.UserRepository;
import com.ins.pos.service.MemberService;
import com.ins.pos.service.util.CommunicationUtil;
import com.ins.pos.service.util.EnglishNumberToWords;
import com.ins.pos.service.util.PasswordGenerator;

@Service
public class MemberServiceImpl implements MemberService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(MemberServiceImpl.class);

	@Autowired
	private MemberShipTypeRepository memberShipTypeRepository;

	@Autowired
	private CenterRepository centerRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	private MemberFacilityRepository memberFacilityRepository;

	@Autowired
	private FacilityTypeRepository facilityTypeRepository;

	@Value("${sportsapp.photos.path}")
	private String photoPath;

	@Autowired
	CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	CommunicationUtil communicationUtil;
	
	@Autowired
	private CommunicationLogRepository communicationLogRepository;
	
	@Autowired
	private MemberCenterRepository memberCenterRepository;
	
	@Value("${sportsapp.renewal.reminder.days}")
	private long reminderDays;

	@Override
	public List<MemberShipTypeJsonDTO> getMemberShipTypes() {
		List<MemberShipTypeJsonDTO> memberShipTypeJsonDTOList = new ArrayList<MemberShipTypeJsonDTO>();
		List<MemberShipType> memberShipTypeList = memberShipTypeRepository.findByActiveAndOnlineActive(true, true);
		for (MemberShipType memberShipType : memberShipTypeList) {
			if (memberShipType.getMemberShipTypeName().equals("ONLINE")) {
				MemberShipTypeJsonDTO memberShipTypeJsonDTO = new MemberShipTypeJsonDTO();
				BeanUtils.copyProperties(memberShipType, memberShipTypeJsonDTO);
				memberShipTypeJsonDTOList.add(memberShipTypeJsonDTO);
			}
		}
		return memberShipTypeJsonDTOList;
	}

	public String saveMember(MemberJsonDTO memberJsonDTO) {
		JSONObject response = new JSONObject();
		Optional<Center> centerOpt = null;
		boolean isNewMember = false;
		Accounts account = new Accounts();
		String password = null;
		try {
			Member member = new Member();
			if (memberJsonDTO.getMemberId() != null) {
				member = memberRepository.findById(memberJsonDTO.getMemberId()).isPresent()
						? memberRepository.findById(memberJsonDTO.getMemberId()).get()
						: new Member();
			} else {
				Calendar calender = Calendar.getInstance();
				calender.add(Calendar.YEAR, 1);
				member.setMemberTypeStartDate(new Date());
				member.setMemberTypeValidity(calender.getTime());
				password = PasswordGenerator.generateRandomPassword(8);
				//password = "password";
				member.setPassword(password);
				isNewMember = true;
				// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				// String hashedPassword = passwordEncoder.encode((String)
			}
			if (isNewMember)
				BeanUtils.copyProperties(memberJsonDTO, member);
			else {
				BeanUtils.copyProperties(memberJsonDTO, member, "memberPhoto");
			}
			if (memberJsonDTO.getCenterId() != null) {
				centerOpt = centerRepository.findById(memberJsonDTO.getCenterId());
			}
			Optional<MemberShipType> memberShipType = memberShipTypeRepository
					.findById(memberJsonDTO.getMemberShipTypeId());
			member.setPaidAmount((memberShipType.get().getMemberShipCost() * memberJsonDTO.getFacilityTypeId().size())
					+ memberShipType.get().getMemberIdCardAmount());
			String words = EnglishNumberToWords.convert(member.getPaidAmount());
			member.setWord(words);
			if (memberJsonDTO.getMemberId() == null || memberJsonDTO.getMemberId() > 0) {
				member.setBillId(accountsRepository.count() + 1);
			}
			member.setMemberShipTypeId(memberShipType.get());
			member.setUserName(member.getEmail());
			member.setCheckStaff(false);
			Role roleId = new Role();
			roleId.setRoleId(Long.parseLong("99"));
			List<MemberFacility> memberFacilityList = new ArrayList<MemberFacility>();

			User userExists = customUserDetailsServiceImpl.findUserByEmail(member.getEmail());
			if (isNewMember && userExists != null) {
				throw new BadCredentialsException("User with username: " + member.getEmail() + " already exists");
			}

			
			member.setRoleId(roleId);
			Branch branchId = new Branch();
			branchId.setBranchId(1l);
			member.setBranchId(branchId);
			member.setSpotCheck(false);
			if (memberJsonDTO.getMemberPhoto() != null) {
				try {
					byte imageByte[] = memberJsonDTO.getMemberPhoto().getBytes();
					String path = photoPath + System.currentTimeMillis();
					FileOutputStream fos = new FileOutputStream(path);
					fos.write(imageByte);
					fos.close();
					member.setMemberPhoto(path);
				} catch (Exception e) {
					LOGGER.error("Exception : ", e);
				}
			}
			member.setActive(false);
			member.setIsOnline(true);
			member.setFirstLogin(true);
			member.setCreatedDate(new Date());
			member.setUpdatedDate(new Date());
			member = memberRepository.save(member);
			memberJsonDTO.setMemberId(member.getMemberId());
			if (isNewMember) {

				account.setActualAmount(member.getPaidAmount());
				account.setWords(member.getWord());
				account.setActive(false);
				account.setTypeOfBooking("New Member");
				account.setBranchId(member.getBranchId());
				account.setCautionDeposit(0.00);
				account.setCautionStatus(false);
				account.setBalanceAmount(0.00);
				account.setExtraAmount(0.00);
				account.setCreditAmount(0.00);
				account.setDiscount(0.00);
				// account.setPaidAmount();
				// account.setPaidDate();
				account.setPayableAmount(member.getPaidAmount());
				account.setBookingApp("Online");
				account.setBookingDate(new Date());
				member.getMemberId();
				account.setMemberId(member);
				account.setCenterId(centerOpt.get());
				account = accountsRepository.save(account);
				
				MemberCenter memberCenter = new MemberCenter();
				memberCenter.setActive(true);
				memberCenter.setCenter(account.getCenterId());
				memberCenter.setMember(member);
				memberCenterRepository.save(memberCenter);
			}

			if (memberJsonDTO.getFacilityTypeId() != null) {

				for (Long facilityTypeId : memberJsonDTO.getFacilityTypeId()) {
					MemberFacility memberFacility = new MemberFacility();
					Optional<FacilityType> facilityTypeOpt = facilityTypeRepository.findById(facilityTypeId);
					memberFacility.setFacilityType(facilityTypeOpt.get());
					memberFacility.setMember(member);
					memberFacility.setActive(true);
					memberFacilityList.add(memberFacility);

				}
				memberFacilityRepository.saveAll(memberFacilityList);
			}

			response.put("status", "Success");
			response.put("memberId", member.getMemberId());
			response.put("accountId", account.getAccountsId());
		} catch (Exception e) {
			response.put("status", "Failure");
			LOGGER.error("Exception : ", e);
		}
		return response.toString();
	}

	@Override
	public String renewMember(String memberJson) {
		JSONObject response = new JSONObject();
		Optional<Center> centerOpt = null;
		Accounts account = new Accounts();
		try {
			JSONObject request = new JSONObject(memberJson);
			Member member = new Member();
			if (request.has("memberId")) {
				member = memberRepository.findById(Long.valueOf(((Integer) request.get("memberId")).longValue())).get();

			} else {

			}
			if (request.has("centerId")) {
				centerOpt = centerRepository.findById(Long.valueOf(((Integer) request.get("centerId")).longValue()));
			}
			if (request.has("memberShipTypeId")) {
				Long memberShipTypeId = request.has("memberShipTypeId")
						? Long.valueOf(((Integer) request.get("memberShipTypeId")).longValue())
						: null;
				if (memberShipTypeId != null) {
					Optional<MemberShipType> memberShipType = memberShipTypeRepository.findById(memberShipTypeId);
					if (memberShipType.isPresent() && memberShipType.get() != null) {
						member.setMemberShipTypeId(memberShipType.get());
					}
				}
			}
			Calendar endCalender = Calendar.getInstance();
			endCalender.setTime(member.getMemberTypeValidity());
			endCalender.add(Calendar.DAY_OF_YEAR, 1);
			endCalender.add(Calendar.YEAR, 1);
			member.setMemberTypeValidity(endCalender.getTime());

			member.setPaidAmount(
					(member.getMemberShipTypeId().getMemberShipCost() * member.getMemberFacility().size()));
			String words = EnglishNumberToWords.convert(member.getPaidAmount());
			member.setWord(words);

			member = memberRepository.save(member);

			account.setActualAmount(member.getPaidAmount());
			account.setWords(member.getWord());
			account.setActive(false);
			account.setTypeOfBooking("Member Renewal");
			account.setBranchId(member.getBranchId());
			account.setCautionDeposit(0.00);
			account.setCautionStatus(false);
			account.setBalanceAmount(0.00);
			account.setExtraAmount(0.00);
			account.setCreditAmount(0.00);
			account.setDiscount(0.00);
			//account.setPaidAmount(member.getPaidAmount());
			//account.setPaidDate(new Date());
			account.setPayableAmount(member.getPaidAmount());
			account.setBookingDate(new Date());
			account.setBookingApp("Online");
			member.getMemberId();
			account.setMemberId(member);
			account.setCenterId(centerOpt.isPresent() ? centerOpt.get() : null);
			account = accountsRepository.save(account);
			response.put("status", "Success");
			response.put("memberId", member.getMemberId());
			response.put("accountId", account.getAccountsId());
		} catch (Exception e) {
			response.put("status", "Failure");
			LOGGER.error("Exception : ", e);
		}
		return response.toString();
	}

	@Override
	public MemberDetailsJsonDTO getMember(long id) {
		MemberDetailsJsonDTO memberDetailsJsonDTO = new MemberDetailsJsonDTO();
		Optional<Member> member = memberRepository.findById(id);
		List<FacilityTypeJsonDTO> facilityTypeList = new ArrayList<FacilityTypeJsonDTO>();
		memberDetailsJsonDTO.setFacilityType(facilityTypeList);
		if (member.isPresent()) {
			MemberShipTypeJsonDTO memberShipTypeJsonDTO = new MemberShipTypeJsonDTO();
			BeanUtils.copyProperties(member.get(), memberDetailsJsonDTO);
			BeanUtils.copyProperties(member.get().getMemberShipTypeId(), memberShipTypeJsonDTO);
			memberDetailsJsonDTO.setMemberShipType(memberShipTypeJsonDTO);
			List<MemberFacility> memberFacList = memberFacilityRepository.findByMemberAndActive(member.get(), true);
			for (MemberFacility memberFacility : memberFacList) {
				FacilityTypeJsonDTO facilityTypeJsonDTO = new FacilityTypeJsonDTO();
				memberFacility.getFacilityType();
				BeanUtils.copyProperties(memberFacility.getFacilityType(), facilityTypeJsonDTO);
				facilityTypeList.add(facilityTypeJsonDTO);
			}
			if (member.get().getMemberTypeValidity().before(new Date())) {
				memberDetailsJsonDTO.setActive(false);
			}
			MemberCenter memberCenter = memberCenterRepository.findByMember(member.get());
			if(memberCenter!=null) {
				memberDetailsJsonDTO.setCenterName(memberCenter.getCenter().getCentreName());
				memberDetailsJsonDTO.setCentreId(memberCenter.getCenter().getCentreId());
			}
			Date today = new Date();
			long duration  = member.get().getMemberTypeValidity().getTime() - today.getTime();
			long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
			if(diffInDays<reminderDays) {
				memberDetailsJsonDTO.setIsRenewalPending(true);
			}else {
				memberDetailsJsonDTO.setIsRenewalPending(false);
			}
		}
		if (memberDetailsJsonDTO.getMemberPhoto() != null) {
			try {
				byte[] fileContent = FileUtils.readFileToByteArray(new File(memberDetailsJsonDTO.getMemberPhoto()));
				String byteString = new String(fileContent);
				memberDetailsJsonDTO.setMemberPhoto(byteString);
			} catch (Exception e) {
				LOGGER.error("Exception : ", e);
			}
		}
		return memberDetailsJsonDTO;
	}

	@Override
	public String changePassword(String requestJSON) {
		JSONObject reqJson = new JSONObject(requestJSON);
		JSONObject response = new JSONObject();
		long memberId = reqJson.getLong("memberId");
		String password = reqJson.getString("password");
		changePassword(memberId, password);
		response.put("status", "Success");

		return response.toString();
	}

	private void changePassword(long memberId, String password) {
		Optional<Member> member = memberRepository.findById(memberId);
		member.ifPresent((m) -> {
			m.setPassword(password);
			m.setFirstLogin(false);
			User user = userRepository.findByEmail(m.getEmail());
			user.setPassword(bCryptPasswordEncoder.encode(password));
			userRepository.save(user);
			memberRepository.save(m);
		});
	}

	@Override
	public String forgetPassword(String requestJSON) {
		JSONObject reqJson = new JSONObject(requestJSON);
		JSONObject response = new JSONObject();
		String password = PasswordGenerator.generateRandomPassword(8);
		String email = reqJson.getString("email");

		List<Member> memberList = memberRepository.findByUserNameAndActive(email, true);
		if (memberList.isEmpty()) {
			response.put("status", "Failure");
			response.put("message", "Username doesn't exist!!!");
		} else {
			Member m = memberList.get(0);

			m.setPassword(password);
			m.setFirstLogin(true);
			User user = userRepository.findByEmail(m.getEmail());
			user.setPassword(bCryptPasswordEncoder.encode(password));
			userRepository.save(user);
			memberRepository.save(m);
			String subject = "SportsApp - Password reset for " + m.getMemberName();
			String smsBody = "SportsApp - Password reset is successfull. One time password is "+m.getPassword();
			String mailbody = "<h2><strong style=\"color: #000;\">Dear ${name} ,</strong></h2><h3 style=\"color: #4485b8;\">Password reset is successfull!</h3><h5></h5><h4>Please find the details below.</h4><h5><strong >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;User ID: ${userId}</strong></h5><h5><strong >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Password: ${password}</strong></h5><h5></h5><h4 style=\"color: #4485b8;\">Sincerely,<br>Administrator</p></h4>";
			mailbody = mailbody.replace("${name}", m.getMemberName()).replace("${userId}", m.getUserName())
					.replace("${password}", m.getPassword());
			boolean emailStatus = communicationUtil.sendEmail(m.getEmail(), subject, mailbody);
			boolean smsStatus = communicationUtil.sendSms(smsBody, m.getMemberContactNo());
			CommunicationLog communicationLog = new CommunicationLog();
			communicationLog.setCreatedDate(new Date());
			communicationLog.setMailBody(mailbody);
			communicationLog.setMailId(m.getEmail());
			communicationLog.setMailStatus(emailStatus);
			communicationLog.setOrderId("M"+m.getMemberId()+"FP"+(new Date()).getTime());
			communicationLog.setPhoneNumber(m.getMemberContactNo());
			communicationLog.setSmsContent(smsBody);
			communicationLog.setSmsStatus(smsStatus);
			communicationLogRepository.save(communicationLog);
			
		}

		response.put("status", "Success");
		return response.toString();
	}

}
