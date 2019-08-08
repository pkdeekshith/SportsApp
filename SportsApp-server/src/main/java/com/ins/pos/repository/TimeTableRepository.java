package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Facility;
import com.ins.pos.entity.TimeTable;

public interface TimeTableRepository extends CrudRepository<TimeTable, Long> {
	public List<TimeTable> findByActive(Boolean active);

	public List<TimeTable> findByActiveAndDayNumAndFacilityId(Boolean active, int dayNum, Facility facilityId);
	
	public List<TimeTable> findByActiveAndSessionStartTimeAndSessionEndTimeAndFacilityId(Boolean active, int sessionStartTime, int sessionEndTime, Facility facilityId);


}