package com.ins.pos.ep;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.ins.pos.dto.CenterDetailsJsonDTO;
import com.ins.pos.dto.FacilityDetailsJsonDTO;
import com.ins.pos.dto.FacilitySubFacilityDetailsJsonDTO;
import com.ins.pos.dto.OnlineBookingWindowDTO;
import com.ins.pos.dto.SendCommunicationFormDTO;
import com.ins.pos.dto.SubFacilityDetailJsonDTO;
import com.ins.pos.service.AdminService;

@Controller
@RequestMapping("/api/admin")
public class AdminEndPoint {

	@Autowired
	AdminService adminService;

	@GetMapping("/getAllCenters")
	public @ResponseBody List<CenterDetailsJsonDTO> getAllCenters() {
		return adminService.getAllCenters();
	}
	
	@PostMapping("/updateCenters")
	public @ResponseBody String updateCenters(@RequestBody List<CenterDetailsJsonDTO> data) {
		return adminService.updateCenters(data);
	}
	
	@PostMapping("/getAllFacilities")
	public @ResponseBody List<FacilityDetailsJsonDTO> getAllFacilities(@RequestBody String data) {
		return adminService.getAllFacilities(data);
	}

	@PostMapping("/updateFacilities")
	public @ResponseBody String updateFacilities(@RequestBody List<FacilityDetailsJsonDTO> data) {
		return adminService.updateFacilities(data);
	}

	@PostMapping("/getAllSubFacilities")
	public @ResponseBody List<FacilitySubFacilityDetailsJsonDTO> getAllSubFacilities(@RequestBody String data) {
		return adminService.getSubFacilitiesForFacilities(data);
	}

	@PostMapping("/updateSubFacilities")
	public @ResponseBody String updateSubFacilities(@RequestBody List<SubFacilityDetailJsonDTO> data) {
		return adminService.updateSubFacilities(data);
	}

	@GetMapping("/getOnlineBookingWindow")
	public @ResponseBody List<OnlineBookingWindowDTO> getOnlineBookingWindow() {
		return adminService.getOnlineBookingWindow();
	}

	@PostMapping("/saveBookingWindow")
	public @ResponseBody String saveBookingWindow(@RequestBody List<OnlineBookingWindowDTO> data) {
		return adminService.saveBookingWindow(data);
	}

	@PostMapping("/sendCommunications")
	public RedirectView  sendMail(SendCommunicationFormDTO request,HttpServletResponse response) {
		return adminService.sendCommunications(request,response);
	}
}
