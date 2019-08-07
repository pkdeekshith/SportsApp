package com.ins.pos.service;

import java.util.List;

import com.ins.pos.dto.FacilitySubFacilityTimeTableJsonDTO;
import com.ins.pos.dto.SubFacilityTimeTableJsonDTO;

public interface TimetableService {
	public List<FacilitySubFacilityTimeTableJsonDTO> getAvailableTimeTables(String data);

	public List<SubFacilityTimeTableJsonDTO> getTimeSlotsForSubFacility(String data);

}
