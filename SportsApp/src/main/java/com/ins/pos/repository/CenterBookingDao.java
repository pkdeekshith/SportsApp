package com.ins.pos.repository;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Center;
import com.ins.pos.entity.CenterBooking;

public interface CenterBookingDao extends CrudRepository<CenterBooking, Long> {

	CenterBooking findByBookedDateAndCenterAndBookingType(Date selectedDate, Center center, String bookingType);

	CenterBooking findByBookedDateAndCenter(Date selectedDate, Center center);

}