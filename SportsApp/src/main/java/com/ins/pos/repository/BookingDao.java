package com.ins.pos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Booking;
import com.ins.pos.entity.Facility;
import com.ins.pos.entity.Member;
import com.ins.pos.entity.SubFacility;
import com.ins.pos.entity.TimeTable;

public interface BookingDao extends CrudRepository<Booking, Long> {
	public Booking findBybookingId(Long bookingId);
	public List<Booking> findByActiveAndBookedDateAndSubFacilityId(Boolean active, Date bookedDate, SubFacility subFacilityId);

	public Iterable<Booking> findByActive(boolean active);

	@Query(value = "SELECT * FROM booking WHERE booked_date BETWEEN ? AND ? and sub_facility_id=? and time_table_id=?", nativeQuery = true)
	public List<Booking> checkWhetherDateExistOrNot(String startDate, String endDate, Long subFacilityId, Long timeTableId);

	
	@Query(value = "SELECT * FROM booking WHERE booked_date=? and centre_id=?", nativeQuery = true)
	public List<Booking> checkCenterBookedOrNot(String startDate, Long centerId);
	
	

	@Query(value = "SELECT * FROM booking WHERE active=? order by booking_id desc", nativeQuery = true)
	public List<Booking> findByActiveForBook(boolean active);
	
	

	@Query(value = "SELECT * FROM booking WHERE booked_date>=? and booked_date<=? and active=1", nativeQuery = true)
	public List<Booking> getAllSelectedDays(String startDate,String endDate);
	
	
//	@Query(value = " select * from booking as b left join spocems.book_additional_members as c on b.booking_id=c.booking_id  WHERE b.booked_date>=? and b.booked_date<=? and b.active=1", nativeQuery = true)
//	public List<Booking> getAllSelectedDays(String startDate,String endDate);
	
	
	@Query(value = "SELECT * FROM booking WHERE booked_date>=? and booked_date<=? and centre_id=? and active=1", nativeQuery = true)
	public List<Booking> getAllSelectedDaysAndCenter(String startDate,String endDate,Long center);
	
	@Query(value = "SELECT * FROM booking WHERE booked_date>=? and booked_date<=? and centre_id=? and facility_id=? and active=1", nativeQuery = true)
	public List<Booking> getAllSelectedDaysAndCenterAndFacility(String startDate,String endDate,Long center,Long facility);
	
	
	
	@Query(value = "SELECT * FROM booking WHERE booked_date>=? and booked_date<=? and centre_id=? and facility_id=? and sub_facility_id=? and active=1", nativeQuery = true)
	public List<Booking> getAllSelectedDaysAndCenterAndFacilityAndSubFacility(String startDate,String endDate,Long center,Long facility,Long subFacility);
	
	
	public List<Booking> findByActiveAndBookedDateAndFacilityIdAndDayNum(Boolean active,Date selectedDate,Facility facilityId,int daynum);
	
	
	public List<Booking> findByActiveAndBookedDateAndFacilityIdAndSubFacilityIdAndDayNum(Boolean active,Date selectedDate,Facility facilityId,SubFacility subFacilityId,int daynum);
	
	public List<Booking>  findByMemberIdAndActive(Member member,Boolean active);
	
	public List<Booking>  findByMemberIdAndActiveAndBookedDate(Member member,Boolean active,Date bookedDate);

	@Query(value = " SELECT * FROM booking WHERE booked_date=curdate()  and member_id=? and  active=? ;", nativeQuery = true)
	public List<Booking>  memberIdAndActiveAndBookedDates(Long member,Boolean active);
	
	public List<Booking> findByActiveAndBookedDateAndFacilityIdAndDayNumAndMemberIdAndTimeTableIdAndSubFacilityId(Boolean active,Date selectedDate,Facility facilityId,int daynum,Member memberId,TimeTable timeTableId,SubFacility subFacilityId);
	public List<Booking> findByActiveAndBookedDateAndFacilityIdAndDayNumAndTimeTableIdAndSubFacilityId(Boolean active,Date selectedDate,Facility facilityId,int daynum,TimeTable timeTableId,SubFacility subFacilityId);

}