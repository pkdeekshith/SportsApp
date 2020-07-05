package com.ins.pos.ep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ins.pos.dto.CenterJsonDTO;
import com.ins.pos.service.CenterService;

@RestController
@RequestMapping("/api/center")
public class CenterEndPoint {

	@Autowired
	CenterService centerService;

	@GetMapping("/getAllCenters")
	public List<CenterJsonDTO> getAllCenters() {
		return centerService.getAllCenters();
	}

}
