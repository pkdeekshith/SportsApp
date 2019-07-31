package com.ins.pos.service;

import java.util.List;

import com.ins.pos.dto.MemberShipTypeJsonDTO;

public interface MemberService {

	List<MemberShipTypeJsonDTO> getMemberShipTypes(String data);
	
}
