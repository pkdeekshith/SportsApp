package com.ins.pos.service.impl;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ins.pos.dto.BookingDetailsJsonDTO;
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
	
	private final Logger LOGGER = LoggerFactory.getLogger(BookingServiceImpl.class);
	
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
			
			if(member.getMemberTypeValidity().before(new Date())){
				throw new Exception("Membership validity expired on "+member.getMemberTypeValidity()+". Please renew the membership!!");
			}
			
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
						bookingEndDate.add(Calendar.MONTH, 2);
						bookingEndDate.set(Calendar.DAY_OF_MONTH, 4);
						bookingEndDate.set(Calendar.HOUR_OF_DAY, 23);
						bookingEndDate.set(Calendar.MINUTE, 59);
						bookingEndDate.set(Calendar.SECOND, 59);
						bookingEndDate.set(Calendar.MILLISECOND, 0);
					} else {
						bookingStartDate.set(Calendar.HOUR_OF_DAY, 0);
						bookingStartDate.set(Calendar.MINUTE, 0);
						bookingStartDate.set(Calendar.SECOND, 0);
						bookingStartDate.set(Calendar.MILLISECOND, 0);
						bookingEndDate.add(Calendar.MONTH, 1);
						bookingEndDate.set(Calendar.DAY_OF_MONTH, 4);
						bookingEndDate.set(Calendar.HOUR_OF_DAY, 23);
						bookingEndDate.set(Calendar.MINUTE, 59);
						bookingEndDate.set(Calendar.SECOND, 59);
						bookingEndDate.set(Calendar.MILLISECOND, 0);
					}
				} else {
					bookingStartDate.set(Calendar.HOUR_OF_DAY, 0);
					bookingStartDate.set(Calendar.MINUTE, 0);
					bookingStartDate.set(Calendar.SECOND, 0);
					bookingStartDate.set(Calendar.MILLISECOND, 0);
					bookingEndDate.add(Calendar.MONTH, 1);
					bookingEndDate.set(Calendar.DAY_OF_MONTH, 4);
					bookingEndDate.set(Calendar.HOUR_OF_DAY, 23);
					bookingEndDate.set(Calendar.MINUTE, 59);
					bookingEndDate.set(Calendar.SECOND, 59);
					bookingEndDate.set(Calendar.MILLISECOND, 0);
				}

			} else {
				throw new Exception("No booking window is defined for the facility");

			}
			for (TimeTable timeTbl : timeTables) {
				List<AccountsSubSector> accountSubSectorList = accountsSubSectorRepository.findActiveBookingForSubFacility(bookingStartDate.getTime(), bookingEndDate.getTime(), timeTbl.getSessionStartTime(), timeTbl.getSessionEndTime(), "Monthly", true, subFacility,true);
				if(accountSubSectorList.size()>=subFacility.getSlotLimit()) {
					throw new Exception("Slot not available");
				}
			}
			List<Price> priceList = priceRepository.findByActiveAndSubFacilityId(true, subFacility);
			
			
			Accounts accounts = new Accounts();
			accounts.setMemberId(member);
			accounts.setBranchId(branch);
			if(priceList!=null&&!priceList.isEmpty()) {
				accounts.setPayableAmount(priceList.get(0).getRatePerMonth());
				String words = EnglishNumberToWords.convert(accounts.getPayableAmount());
				accounts.setWords(words);
			}
			accounts.setPaidDate(null);
			accounts.setTypeOfBooking("Bookings");
			accounts.setBookingDate(bookedDate.getTime());
			accounts.setBookingDateLast(bookingEndDate.getTime());
			accounts.setCenterId(center);
			accounts.setCautionStatus(false);
			accounts.setCreditAmount(0.00);
			accounts.setActive(false);
			accounts.setBookingApp("Online");
			accounts.setTypeOfBooking("Monthly");
			accounts.setSessionStartTime(timeTables.get(0).getSessionStartTime());
			accounts.setSessionEndTime(timeTables.get(0).getSessionEndTime());
			accounts.setIsCentreBooking(false);
			accounts.setOnHold(true);
			accountsRepository.save(accounts);
			List<Date> bookedDates = new ArrayList<>();
			List<TimeTable> timeTables1 = new ArrayList<>();
			for (TimeTable timeTable : timeTables) {
				Map<Integer, TimeTable> timeTableMap = timeTableRepository
						.findByActiveAndSessionStartTimeAndSessionEndTimeAndFacilityId(true,
								timeTable.getSessionStartTime(), timeTable.getSessionEndTime(), facility)
						.stream().collect(Collectors.toMap(TimeTable::getDayNum, Function.identity()));
				int daysList = Math.round(ChronoUnit.DAYS.between(bookingStartDate.toInstant(),bookingEndDate.toInstant()));
				bookedDates.add(bookingStartDate.getTime());
				Calendar bookDate  = bookingStartDate;
				int dayNeedToChange = Calendar.DAY_OF_WEEK;
				timeTables1.add(timeTableMap.get(bookDate.get(dayNeedToChange)));
				for (int i = 0; i < daysList; i++) {
					bookDate.add(Calendar.DATE, 1);
					if (timeTableMap.containsKey(bookDate.get(Calendar.DAY_OF_WEEK))) {
						bookedDates.add(bookDate.getTime());
						timeTables1.add(timeTableMap.get(bookDate.get(Calendar.DAY_OF_WEEK)));
					} 
				}
				timeTables = timeTables1;
			}
			int i=0;
			for (TimeTable timeTbl : timeTables) {
			Booking booking = new Booking();
			booking.setMemberId(member);
			booking.setBookedDate(bookedDates.isEmpty() ? bookedDate.getTime() : bookedDates.get(i));
			booking.setBookingDate(bookedDate.getTime());
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
			booking.setActive(false);
			booking.setBookingApp("Online");
			booking.setCount(1);
			bookingRepository.save(booking);
			bookings.add(booking);
			i++;
			}
			
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
			response.put("memberId", member.getMemberId());
			
		} catch (Exception e) {
			LOGGER.error("Exception : ",e);
			response.put("status", "Failure");
			response.put("message", e);
		}
		return response.toString();

	}

	@Override
	public List<BookingDetailsJsonDTO> getAllBookingForMember(long parseLong) {
		List<BookingDetailsJsonDTO> bookingDetailsJsonDTOList = new ArrayList<BookingDetailsJsonDTO>();
		Optional<Member> memberOpt = memberRepository.findById(parseLong);
		List<Accounts> accountList = accountsRepository.getAllBookingForMember(memberOpt.get(), "Monthly", true);
		for(Accounts accounts:accountList) {
			BookingDetailsJsonDTO bookingDetailsJsonDTO =new BookingDetailsJsonDTO();
			bookingDetailsJsonDTO.setAccountsId(accounts.getAccountsId());
			bookingDetailsJsonDTO.setBookingEndDate(accounts.getBookingDateLast());
			bookingDetailsJsonDTO.setBookingStartDate(accounts.getBookingDate());
			bookingDetailsJsonDTO.setBookedDate(accounts.getPaidDate());
			for(AccountsSubSector accountsSubSector:accounts.getAccountsSubId()) {
				if(accountsSubSector.getFacilityId()!=null) {
				bookingDetailsJsonDTO.setFacilityId(accountsSubSector.getFacilityId().getFacilityId());
				bookingDetailsJsonDTO.setFacilityName(accountsSubSector.getFacilityId().getFacilityName());
				}
				if(accountsSubSector.getSubFacilityId()!=null) {
				bookingDetailsJsonDTO.setSubFacilityId(accountsSubSector.getSubFacilityId().getSubFacilityId());
				bookingDetailsJsonDTO.setSubFacilityName(accountsSubSector.getSubFacilityId().getSubFacilityName());
				}
			}
			if(accounts.getMemberId()!=null) {
			bookingDetailsJsonDTO.setMemberId(accounts.getMemberId().getMemberId());
			bookingDetailsJsonDTO.setMemberName(accounts.getMemberId().getMemberName());
			}
			bookingDetailsJsonDTO.setPaidAmount(accounts.getPaidAmount());
			bookingDetailsJsonDTO.setSessionEndTime(accounts.getSessionEndTime());
			bookingDetailsJsonDTO.setSessionStartTime(accounts.getSessionStartTime());
			bookingDetailsJsonDTOList.add(bookingDetailsJsonDTO);
		}
		return bookingDetailsJsonDTOList;
	}

	@Override
	public List<BookingDetailsJsonDTO> getUpcomingBookingForMember(long parseLong) {
		List<BookingDetailsJsonDTO> bookingDetailsJsonDTOList = new ArrayList<BookingDetailsJsonDTO>();
		Optional<Member> memberOpt = memberRepository.findById(parseLong);
		Date date = new Date();
		List<Accounts> accountList = accountsRepository.getUpcomingBookingForMember(memberOpt.get(), "Monthly", true, date);
		for(Accounts accounts:accountList) {
			BookingDetailsJsonDTO bookingDetailsJsonDTO =new BookingDetailsJsonDTO();
			bookingDetailsJsonDTO.setAccountsId(accounts.getAccountsId());
			bookingDetailsJsonDTO.setBookingEndDate(accounts.getBookingDateLast());
			bookingDetailsJsonDTO.setBookingStartDate(accounts.getBookingDate());
			bookingDetailsJsonDTO.setBookedDate(accounts.getPaidDate());
			for(AccountsSubSector accountsSubSector:accounts.getAccountsSubId()) {
				if(accountsSubSector.getFacilityId()!=null) {
				bookingDetailsJsonDTO.setFacilityId(accountsSubSector.getFacilityId().getFacilityId());
				bookingDetailsJsonDTO.setFacilityName(accountsSubSector.getFacilityId().getFacilityName());
				}
				if(accountsSubSector.getSubFacilityId()!=null) {
				bookingDetailsJsonDTO.setSubFacilityId(accountsSubSector.getSubFacilityId().getSubFacilityId());
				bookingDetailsJsonDTO.setSubFacilityName(accountsSubSector.getSubFacilityId().getSubFacilityName());
				}
			}
			if(accounts.getMemberId()!=null) {
			bookingDetailsJsonDTO.setMemberId(accounts.getMemberId().getMemberId());
			bookingDetailsJsonDTO.setMemberName(accounts.getMemberId().getMemberName());
			}
			bookingDetailsJsonDTO.setPaidAmount(accounts.getPaidAmount());
			bookingDetailsJsonDTO.setSessionEndTime(accounts.getSessionEndTime());
			bookingDetailsJsonDTO.setSessionStartTime(accounts.getSessionStartTime());
			bookingDetailsJsonDTOList.add(bookingDetailsJsonDTO);
		}
		
		return bookingDetailsJsonDTOList;
	}
	
	

}