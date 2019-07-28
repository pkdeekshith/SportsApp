package com.ins.pos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ins.pos.entity.Accounts;
import com.ins.pos.entity.Center;
import com.ins.pos.entity.Facility;
import com.ins.pos.entity.SubFacility;

public interface AccountsDao extends CrudRepository<Accounts, Long> {

	public Iterable<Accounts> findByActive(Boolean active);

	@Query(value = "SELECT * FROM accounts where caution_deposit>0 and caution_status=0;", nativeQuery = true)
	public Iterable<Accounts> allAccountsListWithCaution();

	public Iterable<Accounts> findByAccountsId(Long accountsId);

	public Accounts findByAccountsIdAndCautionStatus(Long accountsId, Boolean cautionStatus);

	@Query(value = "SELECT a FROM Accounts a JOIN FETCH a.memberId JOIN FETCH a.branchId JOIN FETCH a.centerId WHERE a.accountsId = :accountsId")
	public Accounts getAccountWithRelations(@Param("accountsId") Long accountsId);

	@Query(value = "SELECT a FROM Accounts a WHERE a.paidDate>=:startDate and a.paidDate<:endDate and a.active=1")
	public List<Accounts> getAllSelectedDays(@Param("startDate") Date startDate,@Param("endDate") Date endDate);

	@Query(value = "SELECT a FROM Accounts a WHERE a.paidDate>=:startDate and a.paidDate<:endDate and a.active=1 and a.typeOfBooking=:paymentType")
	public List<Accounts> getAllMemberAccountSelectedDays(@Param("startDate") Date startDate,@Param("endDate") Date endDate,  @Param("paymentType") String paymentType);

	@Query(value = "SELECT a FROM Accounts a WHERE a.paidDate>=:startDate and a.paidDate<:endDate and a.active=1 and a.typeOfBooking=:paymentType and a.centerId = :centerId")
	public List<Accounts> getAllMemberAccountSelectedDaysCenter(@Param("startDate") Date startDate,@Param("endDate") Date endDate,  @Param("paymentType") String paymentType,@Param("centerId") Center centerId);

	
	@Query(value = "SELECT a FROM Accounts a WHERE a.paidDate>=:startDate and a.paidDate<:endDate and a.active=1")
	public List<Accounts> getAllMemberAccountSelectedDaysAllPayment(@Param("startDate") Date startDate,@Param("endDate") Date endDate);

	
	@Query(value = "SELECT a FROM Accounts a WHERE a.paidDate>=:startDate and a.paidDate<:endDate and a.active=1 and a.typeOfBooking=:paymentType")
	public List<Accounts> getBookingAccountSelectedDays(@Param("startDate") Date startDate,@Param("endDate") Date endDate,  @Param("paymentType") String paymentType);

	@Query(value = "SELECT distinct a FROM Accounts a inner join a.AccountTypeLinkRole at inner join  at.bookingId bk WHERE a.typeOfBooking=:paymentType and a.paidDate>=:startDate and a.paidDate<:endDate and bk.centerId = :centerId")
	public List<Accounts> getAllSelectedDaysAndCenter(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("centerId") Center centerId, @Param("paymentType") String paymentType);
	
	/*@Query(value = "SELECT distinct a FROM Accounts a inner join a.AccountTypeLinkRole at inner join  at.bookingId bk WHERE a.paidDate>=:startDate and a.paidDate<:endDate and bk.centerId = :centerId")
	public List<Accounts> getAllSelectedDaysAndCenterAllPayment(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("centerId") Center centerId);*/
	
	@Query(value = "SELECT distinct a FROM Accounts a WHERE a.paidDate>=:startDate and a.paidDate<:endDate and a.centerId = :centerId")
	public List<Accounts> getAllSelectedDaysAndCenterAllPayment(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("centerId") Center centerId);
	

	@Query(value="SELECT distinct a FROM Accounts a inner join a.AccountTypeLinkRole at inner join  at.bookingId bk WHERE a.typeOfBooking=:paymentType and a.paidDate>=:startDate and a.paidDate<:endDate and bk.centerId = :centerId and bk.facilityId = :facility")
	public List<Accounts> getAllSelectedDaysCenterAndFacility(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("centerId") Center centerId, @Param("facility") Facility facility, @Param("paymentType") String paymentType);

	@Query(value="SELECT distinct a FROM Accounts a inner join a.AccountTypeLinkRole at inner join  at.bookingId bk WHERE a.paidDate>=:startDate and a.paidDate<:endDate and bk.centerId = :centerId and bk.facilityId = :facility")
	public List<Accounts> getAllSelectedDaysCenterAndFacilityAllPayment(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("centerId") Center centerId, @Param("facility") Facility facility);

	
	@Query(value="SELECT distinct a FROM Accounts a inner join a.AccountTypeLinkRole at inner join  at.bookingId bk WHERE a.typeOfBooking=:paymentType and a.paidDate>=:startDate and a.paidDate<:endDate and bk.centerId = :centerId and bk.facilityId = :facility and bk.subFacilityId=:subFacility")
	public List<Accounts> getAllSelectedDaysAndCenterAndFacilityAndSubFacility(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("centerId") Center centerId, @Param("facility") Facility facility
			, @Param("subFacility") SubFacility subFacility, @Param("paymentType") String paymentType);
	
	@Query(value="SELECT distinct a FROM Accounts a inner join a.AccountTypeLinkRole at inner join  at.bookingId bk WHERE a.paidDate>=:startDate and a.paidDate<:endDate and bk.centerId = :centerId and bk.facilityId = :facility and bk.subFacilityId=:subFacility")
	public List<Accounts> getAllSelectedDaysAndCenterAndFacilityAndSubFacilityAllPayment(@Param("startDate") Date startDate,@Param("endDate") Date endDate,@Param("centerId") Center centerId, @Param("facility") Facility facility
			, @Param("subFacility") SubFacility subFacility);
}
