package com.ins.pos.ep;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ins.pos.dto.MemberDetailsJsonDTO;
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
	
	@PostMapping("/renewMember")
	public String renewMember(@RequestBody String requestJSON) {
		return memberService.renewMember(requestJSON);
	}
	
	@RequestMapping("/getMember/{id}")
	public MemberDetailsJsonDTO getMember(HttpServletRequest request, @PathVariable String id) {
		return memberService.getMember(Long.parseLong(id));
	}
	
}
