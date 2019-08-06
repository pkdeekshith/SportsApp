package com.ins.pos.service;

import java.util.List;

import com.ins.pos.dto.FacilitySubFacilityTimeTableJsonDTO;

public interface TimetableService {
	public List<FacilitySubFacilityTimeTableJsonDTO> getAvailableTimeTables(String data);

}
