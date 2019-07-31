package com.ins.pos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ins.pos.dto.MemberShipTypeJsonDTO;
import com.ins.pos.entity.MemberShipType;
import com.ins.pos.repository.MemberShipTypeRepository;
import com.ins.pos.service.MemberService;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberShipTypeRepository memberShipTypeRepository;

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
	
}
