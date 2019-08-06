package com.ins.pos.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ins.pos.dto.BookingJsonDTO;
import com.ins.pos.entity.AccountBook;
import com.ins.pos.entity.Accounts;
import com.ins.pos.entity.AccountsSubSector;
import com.ins.pos.entity.BookAdditionalMembers;
import com.ins.pos.entity.Booking;
import com.ins.pos.entity.Branch;
import com.ins.pos.entity.Center;
import com.ins.pos.entity.Facility;
import com.ins.pos.entity.Member;
import com.ins.pos.entity.OnlineBookingWindow;
import com.ins.pos.entity.Price;
import com.ins.pos.entity.SubFacility;
import com.ins.pos.entity.TimeTable;
import com.ins.pos.repository.AccountBookRepository;
import com.ins.pos.repository.AccountsRepository;
import com.ins.pos.repository.AccountsSubSectorRepository;
import com.ins.pos.repository.BookAdditionalMembersRepository;
import com.ins.pos.repository.BookingRepository;
import com.ins.pos.repository.CenterRepository;
import com.ins.pos.repository.FacilityRepository;
import com.ins.pos.repository.MemberRepository;
import com.ins.pos.repository.OnlineBookingWindowRepository;
import com.ins.pos.repository.PriceRepository;
import com.ins.pos.repository.SubFacilityRepository;
import com.ins.pos.repository.TimeTableRepository;
import com.ins.pos.service.BookingService;
import com.ins.pos.service.util.EnglishNumberToWords;

@Service
public class BookingServiceImpl implements BookingService {
	
	@Autowired
	private FacilityRepository facilityRepository;
	
	@Autowired
	private CenterRepository centerRepository;
	
	@Autowired
	private OnlineBookingWindowRepository onlineBookingWindowRepository;
	
	@Autowired
	SubFacilityRepository subFacilityRepository;
	
	@Autowired
	TimeTableRepository timeTableRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private AccountsRepository accountsRepository;
	
	@Autowired
	private BookAdditionalMembersRepository bookAdditionalMembersRepository;
	
	@Autowired
	private AccountBookRepository accountBookRepository;
	
	@Autowired
	private AccountsSubSectorRepository accountsSubSectorRepository;
	
	@Autowired
	PriceRepository priceRepository;

	@Override
	public String saveBooking(BookingJsonDTO bookingJsonDTO) {
		List<Member> memberFiless = new ArrayList<>();
		List<TimeTable> timeTables = new ArrayList<>();
		List<Booking> bookings = new ArrayList<>();
		JSONObject response = new JSONObject();
		try {
			Facility facility = facilityRepository.findById(bookingJsonDTO.getFacilityId()).get();
			Center center = centerRepository.findById(bookingJsonDTO.getCenterId()).get();
			SubFacility subFacility = subFacilityRepository.findById(bookingJsonDTO.getSubFacilityId()).get();
			Member member = memberRepository.findById(bookingJsonDTO.getMemberId()).get();
			timeTableRepository.findAllById(bookingJsonDTO.getTimeTableId()).forEach(timeTables::add);
			for (Long id : bookingJsonDTO.getOtherMemberId()) {
				Member addMember = memberRepository.findById(id).get();
				memberFiless.add(addMember);
			}
			Branch branch = new Branch();
			branch.setBranchId(1l);
			Member modifiedUser = member;
			OnlineBookingWindow onlineBookingWindow = onlineBookingWindowRepository.findByActiveAndFacilityId(true,
					facility);
			Calendar bookedDate = Calendar.getInstance();
			Calendar bookingStartDate = Calendar.getInstance();
			Calendar bookingEndDate = Calendar.getInstance();

			if (onlineBookingWindow != null) {
				if (onlineBookingWindow.getBookingStartDate() > onlineBookingWindow.getBookingEndDate()) {
					if (bookedDate.get(Calendar.DAY_OF_MONTH) >= onlineBookingWindow.getBookingStartDate()) {
						bookingStartDate.add(Calendar.MONTH, 1);
						bookingStartDate.set(Calendar.DAY_OF_MONTH, 1);
						bookingStartDate.set(Calendar.HOUR_OF_DAY, 0);
						bookingStartDate.set(Calendar.MINUTE, 0);
						bookingStartDate.set(Calendar.SECOND, 0);
						bookingStartDate.set(Calendar.MILLISECOND, 0);
						bookingEndDate.add(Calendar.MONTH, 1);
						bookingEndDate.set(Calendar.HOUR_OF_DAY, 23);
						bookingEndDate.set(Calendar.MINUTE, 59);
						bookingEndDate.set(Calendar.SECOND, 59);
						bookingEndDate.set(Calendar.MILLISECOND, 0);
						bookingEndDate.set(Calendar.DAY_OF_MONTH,
								bookingEndDate.getActualMaximum(Calendar.DAY_OF_MONTH));
					} else {
						bookingStartDate.set(Calendar.DAY_OF_MONTH, 1);
						bookingStartDate.set(Calendar.HOUR_OF_DAY, 0);
						bookingStartDate.set(Calendar.MINUTE, 0);
						bookingStartDate.set(Calendar.SECOND, 0);
						bookingStartDate.set(Calendar.MILLISECOND, 0);
						bookingEndDate.set(Calendar.HOUR_OF_DAY, 23);
						bookingEndDate.set(Calendar.MINUTE, 59);
						bookingEndDate.set(Calendar.SECOND, 59);
						bookingEndDate.set(Calendar.MILLISECOND, 0);
						bookingEndDate.set(Calendar.DAY_OF_MONTH,
								bookingEndDate.getActualMaximum(Calendar.DAY_OF_MONTH));
					}
				} else {
					bookingStartDate.set(Calendar.DAY_OF_MONTH, 1);
					bookingStartDate.set(Calendar.HOUR_OF_DAY, 0);
					bookingStartDate.set(Calendar.MINUTE, 0);
					bookingStartDate.set(Calendar.SECOND, 0);
					bookingStartDate.set(Calendar.MILLISECOND, 0);
					bookingEndDate.set(Calendar.HOUR_OF_DAY, 23);
					bookingEndDate.set(Calendar.MINUTE, 59);
					bookingEndDate.set(Calendar.SECOND, 59);
					bookingEndDate.set(Calendar.MILLISECOND, 0);
					bookingEndDate.set(Calendar.DAY_OF_MONTH, bookingEndDate.getActualMaximum(Calendar.DAY_OF_MONTH));
				}

			} else {
				bookingStartDate.set(Calendar.DAY_OF_MONTH, 1);
				bookingStartDate.set(Calendar.HOUR_OF_DAY, 0);
				bookingStartDate.set(Calendar.MINUTE, 0);
				bookingStartDate.set(Calendar.SECOND, 0);
				bookingStartDate.set(Calendar.MILLISECOND, 0);
				bookingEndDate.set(Calendar.HOUR_OF_DAY, 23);
				bookingEndDate.set(Calendar.MINUTE, 59);
				bookingEndDate.set(Calendar.SECOND, 59);
				bookingEndDate.set(Calendar.MILLISECOND, 0);
				bookingEndDate.set(Calendar.DAY_OF_MONTH, bookingEndDate.getActualMaximum(Calendar.DAY_OF_MONTH));

			}
			for (TimeTable timeTbl : timeTables) {
				Booking booking = new Booking();
				booking.setMemberId(member);
				booking.setBookedDate(bookedDate.getTime());
				booking.setBookingStartDate(bookingStartDate.getTime());
				booking.setBookingEndDate(bookingEndDate.getTime());
				booking.setBranchId(branch);
				booking.setFacilityId(facility);
				booking.setSubFacilityId(subFacility);
				booking.setCenterId(center);
				booking.setLastModifiedUserId(modifiedUser);
				booking.setTimeTableId(timeTbl);
				booking.setDayNum(timeTbl.getDayNum());
				booking.setSessionEndTime(timeTbl.getSessionEndTime());
				booking.setSessionStartTime(timeTbl.getSessionStartTime());
				booking.setEntryStatus(false);
				booking.setBookStatus(1);
				booking.setActive(true);
				booking.setBookingApp("Online");
				booking.setCount(1);
				bookingRepository.save(booking);
				bookings.add(booking);
			}
			List<Price> priceList = priceRepository.findByActiveAndSubFacilityId(true, subFacility);

			
			
			Accounts accounts = new Accounts();
			accounts.setMemberId(member);
			accounts.setBranchId(branch);
			if(priceList!=null&&!priceList.isEmpty()) {
				accounts.setPaidAmount(priceList.get(0).getRatePerMonth());
				String words = EnglishNumberToWords.convert(accounts.getPaidAmount());
				accounts.setWords(words);
			}
			//Long l1 = Math.round(accounts.getPaidAmount());
			//double paidAmpunt = l1;
			//accounts.setPaidAmount(1);
			accounts.setPaidDate(new Date());
			accounts.setTypeOfBooking("Bookings");
			//String words = EnglishNumberToWords.convert(accounts.getPaidAmount());
			//accounts.setWords(words);
			accounts.setBookingDate(bookedDate.getTime());
			accounts.setBookingDateLast(bookingEndDate.getTime());
			accounts.setCenterId(center);
			accounts.setCautionStatus(false);
			accounts.setCreditAmount(0.00);
			accounts.setActive(true);
			accounts.setIsCentreBooking(false);
			accountsRepository.save(accounts);

			for (Booking boos : bookings) {
				for (Member memList : memberFiless) {
					BookAdditionalMembers boo = new BookAdditionalMembers();
					boo.setMemberfkId(member);
					boo.setMemberPrimaryId(memList);
					boo.setAccountsId(accounts);
					boo.setStatusUpdate(false);
					boo.setBookingId(boos);
					boo.setActive(true);
					bookAdditionalMembersRepository.save(boo);
				}
			}

			for (Booking boos : bookings) {
				AccountBook accountBook = new AccountBook();
				accountBook.setAccountsId(accounts);
				accountBook.setBookingId(boos);
				accountBook.setActive(true);
				accountBookRepository.save(accountBook);
			}
			
			AccountsSubSector accountsSub = null;
			accountsSub = new AccountsSubSector();
			accountsSub.setFacilityId(facility);
			accountsSub.setSubFacilityId(subFacility);
			accountsSub.setAccountsId(accounts);
			accountsSub.setStatus(true);
			accountsSubSectorRepository.save(accountsSub);
			response.put("status", "Success");
			response.put("accountId", accounts.getAccountsId());
			
		} catch (Exception e) {
			response.put("status", "Failure");
		}
		return response.toString();

	}
	
	

}