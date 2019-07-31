package com.ins.pos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Booking;
import com.ins.pos.entity.SubFacility;
import com.ins.pos.entity.TimeTable;

public interface BookingRepository extends CrudRepository<Booking, Long> {
	
	public List<Booking> findByBookingStartDateGreaterThanEqualAndBookingEndDateLessThanEqualAndSubFacilityIdAndTimeTableId(Date bookingStartDate, Date bookingEndDate,SubFacility subFacilityId,TimeTable timeTableId);
}