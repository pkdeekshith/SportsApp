package com.ins.pos.ep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ins.pos.dto.FacilityDetailsJsonDTO;
import com.ins.pos.dto.FacilitySubFacilityDetailsJsonDTO;
import com.ins.pos.dto.OnlineBookingWindowDTO;
import com.ins.pos.dto.SubFacilityDetailJsonDTO;
import com.ins.pos.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminEndPoint {

	@Autowired
	AdminService adminService;

	@PostMapping("/getAllFacilities")
	public List<FacilityDetailsJsonDTO> getAllFacilities(@RequestBody String data) {
		return adminService.getAllFacilities(data);
	}

	@PostMapping("/updateFacilities")
	public String updateFacilities(@RequestBody List<FacilityDetailsJsonDTO> data) {
		return adminService.updateFacilities(data);
	}

	@PostMapping("/getAllSubFacilities")
	public List<FacilitySubFacilityDetailsJsonDTO> getAllSubFacilities(@RequestBody String data) {
		return adminService.getSubFacilitiesForFacilities(data);
	}

	@PostMapping("/updateSubFacilities")
	public String updateSubFacilities(@RequestBody List<SubFacilityDetailJsonDTO> data) {
		return adminService.updateSubFacilities(data);
	}

	@GetMapping("/getOnlineBookingWindow")
	public List<OnlineBookingWindowDTO> getOnlineBookingWindow() {
		return adminService.getOnlineBookingWindow();
	}

	@PostMapping("/saveBookingWindow")
	public String saveBookingWindow(@RequestBody List<OnlineBookingWindowDTO> data) {
		return adminService.saveBookingWindow(data);
	}

	@GetMapping("/sendCommunications/{accountId}")
	public void sendMail(@PathVariable(name = "accountId") long accountId) {
		adminService.sendCommunications(accountId);
	}
}
