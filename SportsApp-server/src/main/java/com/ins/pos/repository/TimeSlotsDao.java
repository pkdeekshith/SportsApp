package com.ins.pos.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.TimeSlots;

public interface TimeSlotsDao extends CrudRepository<TimeSlots, Long> {
	public Iterable<TimeSlots> findByActive(Boolean active);
	
	
	
//	@Query(value="SELECT * FROM time_slots WHERE ? >= session_start_time AND session_close_time >= ? and day_num=? and facility_id=? and active=1 ",nativeQuery=true)
//	public TimeSlots checkTimeSlotsExists(String startTime,String endTime,int dayNum,Long facilityId);

//	select * FROM time_slots where day_num=7 and facility_id=67 and active=1 and( '21:00' >= session_start_time AND session_close_time >= '22:00' OR session_start_time between '21:00' and '22:00' OR session_close_time between '21:00' and '22:00');
//	@Query(value="call usp_timeslots(?,?,?,?);",nativeQuery=true)
	@Query(value="select * FROM time_slots where day_num=? and facility_id=? and active=1 and( ? >= session_start_time AND session_close_time >= ? OR session_start_time between ? and ? OR session_close_time between ? and ?)",nativeQuery=true)
	public ArrayList<TimeSlots> checkTimeSlotsExists(int dayNum,Long facilityId,String startTime,String endTime,String startTime1,String endTime1,String startTime2,String endTime2);

}
