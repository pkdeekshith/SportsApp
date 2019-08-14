package com.ins.pos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ins.pos.entity.Accounts;
import com.ins.pos.entity.Member;

public interface AccountsRepository extends CrudRepository<Accounts, Long> {

	@Query("select a from Accounts a join a.memberId where a.memberId=:memberId and a.typeOfBooking=:typeOfBooking and a.active=:active")
	public List<Accounts> getAllBookingForMember(@Param("memberId") Member memberId,
			@Param("typeOfBooking") String typeOfBooking, @Param("active") Boolean active);

	@Query("select a from Accounts a join a.memberId where a.memberId=:memberId and a.bookingDateLast>=:bookingEndDate and a.typeOfBooking=:typeOfBooking and a.active=:active")
	public List<Accounts> getUpcomingBookingForMember(@Param("memberId") Member memberId,
			@Param("typeOfBooking") String typeOfBooking, @Param("active") Boolean active,
			@Param("bookingEndDate") Date bookingEndDate);

}
