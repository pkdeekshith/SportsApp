package com.ins.pos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ins.pos.entity.Accounts;
import com.ins.pos.entity.Center;
import com.ins.pos.entity.Facility;
import com.ins.pos.entity.Member;
import com.ins.pos.entity.SubFacility;

public interface AccountsRepository extends CrudRepository<Accounts, Long> {
	
	List<Accounts> findByAccountsIdIn(List<Long> accountsId);

	@Query("select a from Accounts a join a.memberId where a.memberId=:memberId and a.typeOfBooking=:typeOfBooking and a.active=:active")
	public List<Accounts> getAllBookingForMember(@Param("memberId") Member memberId,
			@Param("typeOfBooking") String typeOfBooking, @Param("active") Boolean active);

	@Query("select a from Accounts a where a.bookingDate>=:bookingStartDate and a.bookingDate<=:bookingEndDate and a.active=:active and a.bookingApp IN :bookingApp and a.centerId IN :centerId")
	public List<Accounts> getAllBookingForDays(@Param("bookingStartDate") Date bookingStartDate,
			@Param("bookingEndDate") Date bookingEndDate, @Param("active") Boolean active,
			@Param("bookingApp") List<String> bookingApp,@Param("centerId") List<Center> centerId);

	@Query("select a from Accounts a join a.memberId where a.memberId=:memberId and a.bookingDateLast>=:bookingEndDate and a.typeOfBooking=:typeOfBooking and a.active=:active")
	public List<Accounts> getUpcomingBookingForMember(@Param("memberId") Member memberId,
			@Param("typeOfBooking") String typeOfBooking, @Param("active") Boolean active,
			@Param("bookingEndDate") Date bookingEndDate);

	@Query("select a from Accounts a where a.bookingDate>=:bookingStartDate and a.bookingDate<=:bookingEndDate and a.typeOfBooking=:typeOfBooking and a.active=:active and a.bookingApp IN :bookingApp and a.centerId IN :centerId")
	public List<Accounts> getSpotBookingForDays(@Param("bookingStartDate") Date bookingStartDate,
			@Param("bookingEndDate") Date bookingEndDate, @Param("typeOfBooking") String typeOfBooking,
			@Param("active") Boolean active, @Param("bookingApp") List<String> bookingApp,@Param("centerId") List<Center> centerId);

	@Query(value = "SELECT a FROM Accounts a WHERE a.paidDate>=:startDate and a.paidDate<:endDate and a.active=1 and a.bookingApp IN :bookingApp")
	public List<Accounts> getAllSelectedDays(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
			@Param("bookingApp") List<String> bookingApp);

	@Query(value = "SELECT a FROM Accounts a WHERE a.paidDate>=:startDate and a.paidDate<:endDate and a.active=1 and a.bookingApp IN :bookingApp")
	public List<Accounts> getAllMemberAccountSelectedDaysAllPayment(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("bookingApp") List<String> bookingApp);

	@Query(value = "SELECT distinct a FROM Accounts a WHERE a.paidDate>=:startDate and a.paidDate<:endDate and a.centerId = :centerId and a.bookingApp IN :bookingApp")
	public List<Accounts> getAllSelectedDaysAndCenterAllPayment(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("centerId") Center centerId, @Param("bookingApp") List<String> bookingApp);

	@Query(value = "SELECT distinct a FROM Accounts a inner join a.AccountTypeLinkRole at inner join  at.bookingId bk WHERE a.paidDate>=:startDate and a.paidDate<:endDate and bk.centerId = :centerId and bk.facilityId = :facility and a.bookingApp IN :bookingApp")
	public List<Accounts> getAllSelectedDaysCenterAndFacilityAllPayment(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("centerId") Center centerId, @Param("facility") Facility facility,
			@Param("bookingApp") List<String> bookingApp);

	@Query(value = "SELECT distinct a FROM Accounts a inner join a.AccountTypeLinkRole at inner join  at.bookingId bk WHERE a.paidDate>=:startDate and a.paidDate<:endDate and bk.centerId = :centerId and bk.facilityId = :facility and bk.subFacilityId=:subFacility and a.bookingApp IN :bookingApp")
	public List<Accounts> getAllSelectedDaysAndCenterAndFacilityAndSubFacilityAllPayment(
			@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("centerId") Center centerId,
			@Param("facility") Facility facility, @Param("subFacility") SubFacility subFacility,
			@Param("bookingApp") List<String> bookingApp);

	@Query(value = "SELECT a FROM Accounts a WHERE a.paidDate>=:startDate and a.paidDate<:endDate and a.active=1 and a.typeOfBooking=:paymentType and a.bookingApp IN :bookingApp")
	public List<Accounts> getAllMemberAccountSelectedDays(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("paymentType") String paymentType,
			@Param("bookingApp") List<String> bookingApp);

	@Query(value = "SELECT a FROM Accounts a WHERE a.paidDate>=:startDate and a.paidDate<:endDate and a.active=1 and a.typeOfBooking=:paymentType and a.centerId = :centerId and a.bookingApp IN :bookingApp")
	public List<Accounts> getAllMemberAccountSelectedDaysCenter(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("paymentType") String paymentType,
			@Param("centerId") Center centerId, @Param("bookingApp") List<String> bookingApp);

	@Query(value = "SELECT a FROM Accounts a WHERE a.paidDate>=:startDate and a.paidDate<:endDate and a.active=1 and a.typeOfBooking=:paymentType and a.bookingApp IN :bookingApp")
	public List<Accounts> getBookingAccountSelectedDays(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("paymentType") String paymentType,
			@Param("bookingApp") List<String> bookingApp);

	@Query(value = "SELECT distinct a FROM Accounts a inner join a.AccountTypeLinkRole at inner join  at.bookingId bk WHERE a.typeOfBooking=:paymentType and a.paidDate>=:startDate and a.paidDate<:endDate and bk.centerId = :centerId and a.bookingApp IN :bookingApp")
	public List<Accounts> getAllSelectedDaysAndCenter(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("centerId") Center centerId,
			@Param("paymentType") String paymentType, @Param("bookingApp") List<String> bookingApp);

	@Query(value = "SELECT distinct a FROM Accounts a inner join a.AccountTypeLinkRole at inner join  at.bookingId bk WHERE a.typeOfBooking=:paymentType and a.paidDate>=:startDate and a.paidDate<:endDate and bk.centerId = :centerId and bk.facilityId = :facility and a.bookingApp IN :bookingApp")
	public List<Accounts> getAllSelectedDaysCenterAndFacility(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("centerId") Center centerId, @Param("facility") Facility facility,
			@Param("paymentType") String paymentType, @Param("bookingApp") List<String> bookingApp);

	@Query(value = "SELECT distinct a FROM Accounts a inner join a.AccountTypeLinkRole at inner join  at.bookingId bk WHERE a.typeOfBooking=:paymentType and a.paidDate>=:startDate and a.paidDate<:endDate and bk.centerId = :centerId and bk.facilityId = :facility and bk.subFacilityId=:subFacility and a.bookingApp IN :bookingApp")
	public List<Accounts> getAllSelectedDaysAndCenterAndFacilityAndSubFacility(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("centerId") Center centerId, @Param("facility") Facility facility,
			@Param("subFacility") SubFacility subFacility, @Param("paymentType") String paymentType,
			@Param("bookingApp") List<String> bookingApp);

}
