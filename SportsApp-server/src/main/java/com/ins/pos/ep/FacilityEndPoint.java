package com.ins.pos.ep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ins.pos.dto.FacilityJsonDTO;
import com.ins.pos.dto.FacilitySubFacilityJsonDTO;
import com.ins.pos.dto.FacilityTypeJsonDTO;
import com.ins.pos.dto.PreferredSportsJsonDTO;
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
	
	@PostMapping("/getAllFacilities")
	public List<FacilityJsonDTO> getAllFacilities(@RequestBody String data) {
		return facilityService.getAllFacilities(data);
	}
	
	@GetMapping("/getAllFacilities")
	public List<FacilityJsonDTO> getAllFacilities() {
		return facilityService.getAllFacilities();
	}
	
	@PostMapping("/getFacilityForPreferredSport")
	public List<FacilityJsonDTO> getFacilityForPreferredSport(@RequestBody String data) {
		return facilityService.getFacilityForPreferredSport(data);
	}
	
	@PostMapping("/getAllFacilitiesAndPreferredSports")
	public List<PreferredSportsJsonDTO> getAllFacilitiesAndPreferredSports(@RequestBody String data) {
		return facilityService.getAllFacilitiesAndPreferredSports(data);
	}
	
	@PostMapping("/checkFacilityAvailabilty")
	public String checkFacilityAvailabilty(@RequestBody String data) {
		return facilityService.checkFacilityAvailabilty(data);
	}
	
	@PostMapping("/getSubFacilitiesForFacilities")
	public List<FacilitySubFacilityJsonDTO> getSubFacilitiesForFacilities(@RequestBody String data) {
		return facilityService.getSubFacilitiesForFacility(data);
	}
}
