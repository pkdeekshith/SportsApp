package com.ins.pos.service;

import java.util.List;

import com.ins.pos.dto.FacilityJsonDTO;
import com.ins.pos.dto.FacilityTypeJsonDTO;

public interface FacilityService {
	public List<FacilityTypeJsonDTO> getAllFacilityTypes();

	public List<FacilityJsonDTO> getFacilityForPreferredSport(String data);
}
