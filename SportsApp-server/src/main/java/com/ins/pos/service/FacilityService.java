package com.ins.pos.service;

import java.util.List;

import com.ins.pos.dto.FacilityJsonDTO;
import com.ins.pos.dto.FacilitySubFacilityJsonDTO;
import com.ins.pos.dto.FacilityTypeJsonDTO;
import com.ins.pos.dto.PreferredSportsJsonDTO;

public interface FacilityService {
	public List<FacilityTypeJsonDTO> getAllFacilityTypes();
	
	public List<FacilityJsonDTO> getFacilityForPreferredSport(String data);
	public String checkFacilityAvailabilty(String data);

	public List<FacilityJsonDTO> getAllFacilities(String data);

	public List<FacilitySubFacilityJsonDTO> getSubFacilitiesForFacility(String data);

	public List<PreferredSportsJsonDTO> getAllFacilitiesAndPreferredSports(String data);

	public List<FacilityJsonDTO> getAllFacilities();
}
