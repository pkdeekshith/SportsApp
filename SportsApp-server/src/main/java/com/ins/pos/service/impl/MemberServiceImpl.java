package com.ins.pos.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ins.pos.dto.FacilityTypeJsonDTO;
import com.ins.pos.dto.MemberJsonDTO;
import com.ins.pos.dto.MemberShipTypeJsonDTO;
import com.ins.pos.entity.Accounts;
import com.ins.pos.entity.Branch;
import com.ins.pos.entity.Center;
import com.ins.pos.entity.FacilityType;
import com.ins.pos.entity.Member;
import com.ins.pos.entity.MemberFacility;
import com.ins.pos.entity.MemberShipType;
import com.ins.pos.entity.Role;
import com.ins.pos.repository.AccountsRepository;
import com.ins.pos.repository.CenterRepository;
import com.ins.pos.repository.FacilityTypeRepository;
import com.ins.pos.repository.MemberFacilityRepository;
import com.ins.pos.repository.MemberRepository;
import com.ins.pos.repository.MemberShipTypeRepository;
import com.ins.pos.service.MemberService;
import com.ins.pos.service.util.EnglishNumberToWords;
import com.ins.pos.service.util.PasswordGenerator;

@Service
public class MemberServiceImpl implements MemberService{

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
	
	@Override
	public List<MemberShipTypeJsonDTO> getMemberShipTypes() {
		List<MemberShipTypeJsonDTO> memberShipTypeJsonDTOList = new ArrayList<MemberShipTypeJsonDTO>();
		List<MemberShipType> memberShipTypeList = memberShipTypeRepository.findByActiveAndOnlineActive(true, true);
		for(MemberShipType memberShipType:memberShipTypeList) {
			MemberShipTypeJsonDTO memberShipTypeJsonDTO = new MemberShipTypeJsonDTO();
			BeanUtils.copyProperties(memberShipType, memberShipTypeJsonDTO);
			memberShipTypeJsonDTOList.add(memberShipTypeJsonDTO);
		}
		return memberShipTypeJsonDTOList;
	}
	
	public String saveMember(MemberJsonDTO memberJsonDTO) {
		JSONObject response = new JSONObject();
		Optional<Center> centerOpt = null;
		boolean isNewMember = false;
		Accounts account = new Accounts();
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
				member.setPassword(PasswordGenerator.generateRandomPassword(8));
				isNewMember = true;
				// BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
				// String hashedPassword = passwordEncoder.encode((String)
			}
			BeanUtils.copyProperties(memberJsonDTO, member);
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

			member.setRoleId(roleId);
			Branch branchId = new Branch();
			branchId.setBranchId(1l);
			member.setBranchId(branchId);
			member.setSpotCheck(false);
			// dmember.setMemberPhoto((String) jsonObject.get("memberPhoto"));
			member.setActive(true);
			member.setIsOnline(true);
			member.setCreatedDate(new Date());
			member.setUpdatedDate(new Date());
			member = memberRepository.save(member);
			memberJsonDTO.setMemberId(member.getMemberId());
			if (isNewMember) {

				account.setActualAmount(member.getPaidAmount());
				account.setWords(member.getWord());
				account.setActive(true);
				account.setTypeOfBooking("New Member");
				account.setBranchId(member.getBranchId());
				account.setCautionDeposit(0.00);
				account.setCautionStatus(false);
				account.setBalanceAmount(0.00);
				account.setExtraAmount(0.00);
				account.setCreditAmount(0.00);
				account.setDiscount(0.00);
				account.setPaidAmount(member.getPaidAmount());
				account.setPaidDate(new Date());
				account.setPayableAmount(0.00);
				member.getMemberId();
				account.setMemberId(member);
				account.setCenterId(centerOpt.get());
				account = accountsRepository.save(account);

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
		}
		return response.toString();
	}
	
}
