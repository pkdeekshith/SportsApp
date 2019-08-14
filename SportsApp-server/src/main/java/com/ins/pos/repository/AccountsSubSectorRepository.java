package com.ins.pos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ins.pos.entity.AccountsSubSector;
import com.ins.pos.entity.SubFacility;

public interface AccountsSubSectorRepository extends CrudRepository<AccountsSubSector, Long> {
	@Query("select a from AccountsSubSector a inner join a.accountsId ac inner join a.subFacilityId sf where ac.bookingDate>=:bookingStartDate and ac.bookingDateLast<=:bookingEndDate and ac.sessionStartTime=:sessionStartTime and ac.sessionEndTime=:sessionEndTime and ac.typeOfBooking=:typeOfBooking and ac.active=:active and sf=:subFacility")
	public List<AccountsSubSector> findActiveBookingForSubFacility(@Param("bookingStartDate") Date bookingStartDate,
			@Param("bookingEndDate") Date bookingEndDate, @Param("sessionStartTime") int sessionStartTime,
			@Param("sessionEndTime") int sessionEndTime, @Param("typeOfBooking") String typeOfBooking,
			@Param("active") Boolean active, @Param("subFacility") SubFacility subFacility);
	
}
