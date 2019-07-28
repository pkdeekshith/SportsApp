package com.ins.pos.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Facility;
import com.ins.pos.entity.TimeSlots;
import com.ins.pos.entity.TimeTable;

public interface TimeTableDao extends CrudRepository<TimeTable, Long> {
	public List<TimeTable> findByActive(Boolean active);

	public List<TimeTable> findByActiveAndDayNumAndFacilityId(Boolean active, int dayNum, Facility facilityId);

//	@Query (value = "SELECT * FROM time_table WHERE active=? and session_start_time>=?  AND ? session_end_time<=? and facility_id=?", nativeQuery = true)
	public List<TimeTable> findByActiveAndSessionStartTimeAndSessionEndTimeAndFacilityId(Boolean active, int sessionStartTime, int sessionEndTime, Facility facilityId);

	
	
	@Query (value = "SELECT * FROM time_table WHERE  facility_id=? AND active=1 and session_start_time>=0  AND  session_end_time<=720 ", nativeQuery = true)
	public List<TimeTable> checking(Long facilityId);
	
	@Query (value = "SELECT * FROM time_table WHERE  facility_id=? AND active=1 and session_start_time>=720  AND  session_end_time<=1440 ", nativeQuery = true)
	public List<TimeTable> forFullday(Long facilityId);
	

	public List<TimeTable> findByActiveAndTimeSlotsId(Boolean actice,TimeSlots timeSlotsId);
	
	
	
	
	
	
	
	

	
}