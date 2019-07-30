package com.ins.pos.ep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ins.pos.dto.FacilitySubFacilityTimeTableJsonDTO;
import com.ins.pos.service.TimetableService;

@RestController
@RequestMapping("/api/timetable")
public class TimeTableEndPoint { 
	
	@Autowired
	TimetableService timetableService;

	
	@PostMapping("getAvailableTimeTables")
	public @ResponseBody List<FacilitySubFacilityTimeTableJsonDTO> getAvailableTimeTables(@RequestBody String data) {
				return timetableService.getAvailableTimeTables(data);
	}
	
	
}
