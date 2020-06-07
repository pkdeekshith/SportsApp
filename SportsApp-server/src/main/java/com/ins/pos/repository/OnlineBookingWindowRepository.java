package com.ins.pos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Facility;
import com.ins.pos.entity.OnlineBookingWindow;

public interface OnlineBookingWindowRepository extends CrudRepository<OnlineBookingWindow, Long> {
	public OnlineBookingWindow findByActiveAndFacilityId(Boolean active,Facility facilityId);
	public OnlineBookingWindow findByFacilityId(Facility facilityId);

}
