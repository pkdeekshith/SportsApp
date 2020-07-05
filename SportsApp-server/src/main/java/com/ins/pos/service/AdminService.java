package com.ins.pos.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.RedirectView;

import com.ins.pos.dto.CenterDetailsJsonDTO;
import com.ins.pos.dto.FacilityDetailsJsonDTO;
import com.ins.pos.dto.FacilitySubFacilityDetailsJsonDTO;
import com.ins.pos.dto.OnlineBookingWindowDTO;
import com.ins.pos.dto.SendCommunicationFormDTO;
import com.ins.pos.dto.SubFacilityDetailJsonDTO;

public interface AdminService {
	public List<FacilityDetailsJsonDTO> getAllFacilities(String data);
	
	public List<FacilitySubFacilityDetailsJsonDTO> getSubFacilitiesForFacilities(String data);

	public String updateFacilities(List<FacilityDetailsJsonDTO> data);

	public String updateSubFacilities(List<SubFacilityDetailJsonDTO> data);

	public List<OnlineBookingWindowDTO> getOnlineBookingWindow();

	public String saveBookingWindow(List<OnlineBookingWindowDTO> data);

	public RedirectView  sendCommunications(SendCommunicationFormDTO request,HttpServletResponse response);

	public List<CenterDetailsJsonDTO> getAllCenters();

	public String updateCenters(List<CenterDetailsJsonDTO> data);

}
