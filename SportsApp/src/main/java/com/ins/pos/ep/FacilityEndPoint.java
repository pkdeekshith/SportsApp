package com.ins.pos.ep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ins.pos.dto.FacilityJsonDTO;
import com.ins.pos.dto.FacilityTypeJsonDTO;
import com.ins.pos.service.FacilityService;

@RestController
@RequestMapping("/api/facility")
public class FacilityEndPoint { 
	
	@Autowired
	FacilityService facilityService;

	@RequestMapping("/getPreferredSports")
	public List<FacilityTypeJsonDTO> getAllFacilityTypes() {
		return facilityService.getAllFacilityTypes();
	}
	
	@PostMapping("/getFacilityForPreferredSport")
	public List<FacilityJsonDTO> getFacilityForPreferredSport(@RequestBody String data) {
		return facilityService.getFacilityForPreferredSport(data);
	}
}
