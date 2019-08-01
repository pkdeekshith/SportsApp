package com.ins.pos.service;

import java.util.List;

import com.ins.pos.dto.MemberJsonDTO;
import com.ins.pos.dto.MemberShipTypeJsonDTO;

public interface MemberService {

	public String saveMember(MemberJsonDTO memberJsonDTO);
	List<MemberShipTypeJsonDTO> getMemberShipTypes();
	
}
