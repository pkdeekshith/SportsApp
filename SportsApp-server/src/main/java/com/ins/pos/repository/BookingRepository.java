package com.ins.pos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Booking;

public interface BookingRepository extends CrudRepository<Booking, Long> {

}