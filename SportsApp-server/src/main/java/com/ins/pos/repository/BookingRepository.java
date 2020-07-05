package com.ins.pos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ins.pos.entity.Booking;
import com.ins.pos.entity.Center;
import com.ins.pos.entity.Facility;
import com.ins.pos.entity.SubFacility;

public interface BookingRepository extends CrudRepository<Booking, Long> {

	@Query(value = "select b from Booking b where b.bookingDate>=:bookingStartDate and b.bookingDate<=:bookingEndDate and b.active=1 and b.bookingApp IN :bookingApp")
	public List<Booking> getAllBookingForDays(@Param("bookingStartDate") Date bookingStartDate,
			@Param("bookingEndDate") Date bookingEndDate, @Param("bookingApp") List<String> bookingApp);
	
	
	@Query(value = "select b from Booking b where b.bookingDate>=:bookingStartDate and b.bookingDate<=:bookingEndDate and b.centerId=:centreId and b.active=1 and b.bookingApp IN :bookingApp")
	public List<Booking> getAllBookingForDaysAndCenter(@Param("bookingStartDate") Date bookingStartDate,
			@Param("bookingEndDate") Date bookingEndDate, @Param("centreId") Center centreId, @Param("bookingApp") List<String> bookingApp);
	
	@Query(value = "select b from Booking b where b.bookingDate>=:bookingStartDate and b.bookingDate<=:bookingEndDate and b.centerId=:centreId and b.facilityId=:facilityId and b.active=1 and b.bookingApp IN :bookingApp")
	public List<Booking> getAllBookingForDaysAndCenterAndFacility(@Param("bookingStartDate") Date bookingStartDate,
			@Param("bookingEndDate") Date bookingEndDate, @Param("centreId") Center centreId, @Param("facilityId") Facility facilityId, @Param("bookingApp") List<String> bookingApp);
	
	@Query(value = "select b from Booking b where b.bookingDate>=:bookingStartDate and b.bookingDate<=:bookingEndDate and b.centerId=:centreId and b.facilityId=:facilityId and b.subFacilityId=:subFacilityId and b.active=1 and b.bookingApp IN :bookingApp")
	public List<Booking> getAllBookingForDaysAndCenterAndFacilityAndSubFacility(@Param("bookingStartDate") Date bookingStartDate,
			@Param("bookingEndDate") Date bookingEndDate, @Param("centreId") Center centreId, @Param("facilityId") Facility facilityId, @Param("subFacilityId") SubFacility subFacilityId, @Param("bookingApp") List<String> bookingApp);
	
	
}