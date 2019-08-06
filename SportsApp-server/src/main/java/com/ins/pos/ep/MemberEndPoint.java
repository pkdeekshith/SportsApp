package com.ins.pos.ep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ins.pos.dto.MemberJsonDTO;
import com.ins.pos.dto.MemberShipTypeJsonDTO;
import com.ins.pos.service.MemberService;

@RestController
@RequestMapping("/api/member")
public class MemberEndPoint { 
	
	@Autowired
	MemberService memberService;

	@RequestMapping("/getMemberShipTypes")
	public @ResponseBody List<MemberShipTypeJsonDTO> getMemberShipTypes() {
		return memberService.getMemberShipTypes();
	}
	
	@PostMapping("/saveMember")
	public String saveMember(@RequestBody MemberJsonDTO memberJsonDTO) {
		return memberService.saveMember(memberJsonDTO);
	}
	
}
