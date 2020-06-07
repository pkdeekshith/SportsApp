package com.ins.pos.service.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ins.pos.dto.AccountReportDTO;
import com.ins.pos.dto.AccountReportOutputDTO;
import com.ins.pos.dto.BookingReportDTO;
import com.ins.pos.dto.BookingReportOutputDTO;
import com.ins.pos.dto.ConsolidatedReportByDateDTO;
import com.ins.pos.dto.ConsolidatedReportByDateOutputDTO;
import com.ins.pos.dto.ConsolidatedReportByFacilityDTO;
import com.ins.pos.dto.ConsolidatedReportByFacilityOutputDTO;
import com.ins.pos.dto.SpotBookingReportDTO;
import com.ins.pos.dto.SpotBookingReportOutputDTO;
import com.ins.pos.dto.SummaryReportDTO;
import com.ins.pos.dto.SummaryReportOutputDTO;
import com.ins.pos.entity.AccountBook;
import com.ins.pos.entity.Accounts;
import com.ins.pos.entity.Booking;
import com.ins.pos.entity.Center;
import com.ins.pos.entity.Facility;
import com.ins.pos.entity.SubFacility;
import com.ins.pos.repository.AccountsRepository;
import com.ins.pos.repository.BookingRepository;
import com.ins.pos.repository.CenterRepository;
import com.ins.pos.repository.FacilityRepository;
import com.ins.pos.repository.SubFacilityRepository;
import com.ins.pos.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private AccountsRepository accountsRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private CenterRepository centerRepository;

	@Autowired
	private FacilityRepository facilityRepository;

	@Autowired
	private SubFacilityRepository subFacilityRepository;

	@Override
	public SummaryReportOutputDTO getAllBookingForDays(String data) {
		String status = "Failure";
		SummaryReportOutputDTO summaryReportOutputDTO = new SummaryReportOutputDTO();
		List<SummaryReportDTO> list = null;
		Map<String, SummaryReportDTO> map = new HashMap<String, SummaryReportDTO>();
		try {
			JSONObject request = new JSONObject(data);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String bookingAppInput = request.getString("bookingApp");
			List<String> bookingApp = new ArrayList<String>();
			if (bookingAppInput.equals("All")) {
				bookingApp.add("Offline");
				bookingApp.add("Online");
				bookingApp.add("Mobile");
			} else {
				bookingApp.add(bookingAppInput);
			}
			Date fromDate = simpleDateFormat.parse(request.getString("fromDate"));
			Date endDate = simpleDateFormat.parse(request.getString("endDate"));
			Calendar endDateCalender = new GregorianCalendar();
			endDateCalender.setTime(endDate);
			endDateCalender.set(Calendar.HOUR_OF_DAY, 23);
			endDateCalender.set(Calendar.MINUTE, 59);
			endDateCalender.set(Calendar.SECOND, 29);
			endDate = endDateCalender.getTime();
			List<Accounts> accountList = accountsRepository.getAllBookingForDays(fromDate, endDate, true, bookingApp);
			for (Accounts account : accountList) {
				SummaryReportDTO summaryReportDTO;
				String date = simpleDateFormat.format(account.getBookingDate());
				if (map.containsKey(date)) {
					summaryReportDTO = map.get(date);
				} else {
					summaryReportDTO = new SummaryReportDTO();
					map.put(date, summaryReportDTO);
				}
				summaryReportDTO.setDate(date);
				if (account.getTypeOfBooking().equals("Quick Bookings")) {
					summaryReportDTO.setQuickBooking(summaryReportDTO.getQuickBooking() + account.getPaidAmount());
					summaryReportDTO.setTotal(summaryReportDTO.getTotal() + account.getPaidAmount());
				} else if (account.getTypeOfBooking().equals("New Member")) {
					summaryReportDTO
							.setMemberRegistration(summaryReportDTO.getMemberRegistration() + account.getPaidAmount());
					summaryReportDTO.setTotal(summaryReportDTO.getTotal() + account.getPaidAmount());
				} else if (account.getTypeOfBooking().equals("Member Renewal")) {
					summaryReportDTO.setRenewal(summaryReportDTO.getRenewal() + account.getPaidAmount());
					summaryReportDTO.setTotal(summaryReportDTO.getTotal() + account.getPaidAmount());
				} else if (account.getTypeOfBooking().equals("Bookings")||account.getTypeOfBooking().equals("Monthly")) {
					summaryReportDTO.setBooking(summaryReportDTO.getBooking() + account.getPaidAmount());
					summaryReportDTO.setTotal(summaryReportDTO.getTotal() + account.getPaidAmount());
				}

			}
			list = new ArrayList<SummaryReportDTO>(map.values());
			status = "Success";
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		summaryReportOutputDTO.setStatus(status);
		summaryReportOutputDTO.setData(list);
		return summaryReportOutputDTO;
	}

	@Override
	public SpotBookingReportOutputDTO getSpotBookingForDays(String data) {
		String status = "Failure";
		SpotBookingReportOutputDTO spotBookingReportOutputDTO = new SpotBookingReportOutputDTO();
		List<SpotBookingReportDTO> list = null;
		Map<String, SpotBookingReportDTO> map = new HashMap<String, SpotBookingReportDTO>();
		double totalAmount = 0;
		try {
			JSONObject request = new JSONObject(data);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String bookingAppInput = request.getString("bookingApp");
			List<String> bookingApp = new ArrayList<String>();
			if (bookingAppInput.equals("All")) {
				bookingApp.add("Offline");
				bookingApp.add("Online");
				bookingApp.add("Mobile");
			} else {
				bookingApp.add(bookingAppInput);
			}
			String sessionType = request.getString("sessionType");
			Date fromDate = simpleDateFormat.parse(request.getString("date"));
			Date endDate = simpleDateFormat.parse(request.getString("date"));
			Calendar endDateCalender = new GregorianCalendar();
			endDateCalender.setTime(endDate);
			endDateCalender.set(Calendar.HOUR_OF_DAY, 23);
			endDateCalender.set(Calendar.MINUTE, 59);
			endDateCalender.set(Calendar.SECOND, 29);
			endDate = endDateCalender.getTime();
			List<Accounts> accountList = accountsRepository.getSpotBookingForDays(fromDate, endDate, "Quick Bookings",
					true, bookingApp);
			System.out.println(accountList.size());

			for (Accounts account : accountList) {
				SpotBookingReportDTO spotBookingReportDTO = null;
				String slot = null;
				double pricePerHour = 0;
				double paidAmount = account.getPaidAmount();
				System.out.println(account.getAccountsId() + " : " + account.getPaidAmount());

				for (AccountBook accountBook : account.getAccountTypeLinkRole()) {
					Booking booking = accountBook.getBookingId();
					int dur = (booking.getSessionEndTime() - booking.getSessionStartTime()) / 60;
					pricePerHour = paidAmount / dur;
					slot = getTime12hr(booking.getSessionStartTime(), booking.getSessionEndTime());
				}
				String key = slot + pricePerHour;

				if (sessionType.equals("M")) {
					if (key.contains("AM")) {
						if (map.containsKey(key)) {
							spotBookingReportDTO = map.get(key);
						} else {
							spotBookingReportDTO = new SpotBookingReportDTO();
							map.put(key, spotBookingReportDTO);
						}
						spotBookingReportDTO.setAmount(spotBookingReportDTO.getAmount() + paidAmount);
						spotBookingReportDTO.setSlot(slot);
						spotBookingReportDTO.setCount(spotBookingReportDTO.getCount() + 1);
						spotBookingReportDTO.setPrice(pricePerHour);
						totalAmount += paidAmount;
					}
				} else if (sessionType.equals("E")) {
					if (key.contains("PM")) {
						if (map.containsKey(key)) {
							spotBookingReportDTO = map.get(key);
						} else {
							spotBookingReportDTO = new SpotBookingReportDTO();
							map.put(key, spotBookingReportDTO);
						}
						spotBookingReportDTO.setAmount(spotBookingReportDTO.getAmount() + paidAmount);
						spotBookingReportDTO.setSlot(slot);
						spotBookingReportDTO.setCount(spotBookingReportDTO.getCount() + 1);
						spotBookingReportDTO.setPrice(pricePerHour);
						totalAmount += paidAmount;
					}
				} else {
					if (map.containsKey(key)) {
						spotBookingReportDTO = map.get(key);
					} else {
						spotBookingReportDTO = new SpotBookingReportDTO();
						map.put(key, spotBookingReportDTO);
					}
					spotBookingReportDTO.setAmount(spotBookingReportDTO.getAmount() + paidAmount);
					spotBookingReportDTO.setSlot(slot);
					spotBookingReportDTO.setCount(spotBookingReportDTO.getCount() + 1);
					spotBookingReportDTO.setPrice(pricePerHour);
					totalAmount += paidAmount;
				}

			}
			list = new ArrayList<SpotBookingReportDTO>(map.values());
			status = "Success";
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		spotBookingReportOutputDTO.setStatus(status);
		spotBookingReportOutputDTO.setData(list);
		spotBookingReportOutputDTO.setTotalAmount(totalAmount);
		return spotBookingReportOutputDTO;
	}

	public String getTime12hr(int startTm, int endTm) {

		DecimalFormat df = new DecimalFormat("##.00");
		String az = "";
		String startAmPm = "";
		String endAmPm = "";
		String bz = "";
		if (startTm != 0 && endTm != 0) {
			int a = (int) (startTm / 60);
			if (a > 12) {
				a = a - 12;
				startAmPm = "PM";
			} else if (a == 12) {
				startAmPm = "PM";
			} else {
				startAmPm = "AM";
			}
			double aMin = startTm % 60;
			int b = (int) (endTm / 60);
			if (b > 12) {
				b = b - 12;
				endAmPm = "PM";
			} else if (b == 12) {
				endAmPm = "PM";
			} else {
				endAmPm = "AM";
			}
			double bMin = endTm % 60;
			az = (df.format(a + aMin * .01)).replace('.', ':');
			bz = (df.format(b + bMin * .01)).replace('.', ':');
		}
		return (az + startAmPm + "-" + bz + endAmPm);

	}

	@Override
	public BookingReportOutputDTO getBookingReport(String data) {
		String status = "Failure";
		BookingReportOutputDTO bookingReportOutputDTO = new BookingReportOutputDTO();
		List<BookingReportDTO> listBooking = new ArrayList<BookingReportDTO>();
		bookingReportOutputDTO.setData(listBooking);
		try {
			List<Booking> bookingList = null;
			JSONObject request = new JSONObject(data);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String bookingAppInput = request.getString("bookingApp");
			List<String> bookingApp = new ArrayList<String>();
			if (bookingAppInput.equals("All")) {
				bookingApp.add("Offline");
				bookingApp.add("Online");
				bookingApp.add("Mobile");
			} else {
				bookingApp.add(bookingAppInput);
			}
			Date fromDate = simpleDateFormat.parse(request.getString("fromDate"));
			Date endDate = simpleDateFormat.parse(request.getString("endDate"));
			Calendar endDateCalender = new GregorianCalendar();
			endDateCalender.setTime(endDate);
			endDateCalender.set(Calendar.HOUR_OF_DAY, 23);
			endDateCalender.set(Calendar.MINUTE, 59);
			endDateCalender.set(Calendar.SECOND, 29);
			endDate = endDateCalender.getTime();
			String center = request.getString("centerId");
			String facility = request.getString("facilityId");
			String subFacility = request.getString("subfacilityId");
			if (center.equals("All") && facility.equals("All") && subFacility.equals("All")) {
				bookingList = bookingRepository.getAllBookingForDays(fromDate, endDate, bookingApp);
			} else if (facility.equals("All") && subFacility.equals("All")) {
				if (!center.equals("All")) {
					Optional<Center> centerOpt = centerRepository.findById(Long.parseLong(center));
					bookingList = bookingRepository.getAllBookingForDaysAndCenter(fromDate, endDate, centerOpt.get(),
							bookingApp);
				}

			} else if (subFacility.equals("All")) {
				if (!facility.equals("All")) {
					if (!(center.equals("All"))) {
						Optional<Center> centerOpt = centerRepository.findById(Long.parseLong(center));
						Facility facilities = facilityRepository.findByFacilityIdAndCenterId(Long.parseLong(facility),
								centerOpt.get());
						bookingList = bookingRepository.getAllBookingForDaysAndCenterAndFacility(fromDate, endDate,
								centerOpt.get(), facilities, bookingApp);
					}
				}
			} else {
				if ((!center.equals("All") && !facility.equals("All") && !subFacility.equals("All"))) {
					Optional<Center> centerOpt = centerRepository.findById(Long.parseLong(center));
					Facility facilities = facilityRepository.findByFacilityIdAndCenterId(Long.parseLong(facility),
							centerOpt.get());
					SubFacility subFacilities = subFacilityRepository.findByFacilityIdAndSubFacilityId(facilities,
							Long.parseLong(subFacility));
					bookingList = bookingRepository.getAllBookingForDaysAndCenterAndFacilityAndSubFacility(fromDate,
							endDate, centerOpt.get(), facilities, subFacilities, bookingApp);
				}
			}

			for (Booking book : bookingList) {
				BookingReportDTO bookingReportDTO = new BookingReportDTO();
				bookingReportDTO.setBookedDate(book.getBookedDate());
				bookingReportDTO.setBookingDate(book.getBookingDate());
				bookingReportDTO.setBookingId(book.getBookingId());
				bookingReportDTO.setCenterName(book.getCenterId().getCentreName());
				bookingReportDTO.setFacility(book.getFacilityId().getFacilityName());
				bookingReportDTO.setMemberName(book.getMemberId().getMemberName());
				bookingReportDTO.setSession(getTime12hr(book.getSessionStartTime(), book.getSessionEndTime()));
				bookingReportDTO.setSubfacility(book.getSubFacilityId().getSubFacilityName());
				listBooking.add(bookingReportDTO);
			}
			status = "Success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		bookingReportOutputDTO.setStatus(status);
		return bookingReportOutputDTO;

	}

	@Override
	public AccountReportOutputDTO getAccountReport(String data) {
		String status = "Failure";
		Map<String, String> keyMap = new HashMap<String, String>();
		keyMap.put("MemberRegistration", "New Member");
		keyMap.put("MemberRenewal", "Member Renewal");
		keyMap.put("Bookings", "Bookings");
		keyMap.put("QuickBookings", "Quick Bookings");
		keyMap.put("All", "All");
		double totalDebitAmount = 0;
		double totalCreditAmount = 0;

		AccountReportOutputDTO accountReportOutputDTO = new AccountReportOutputDTO();
		List<AccountReportDTO> listBooking = new ArrayList<AccountReportDTO>();
		accountReportOutputDTO.setData(listBooking);
		try {
			List<Accounts> accountList = null;
			JSONObject request = new JSONObject(data);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String bookingAppInput = request.getString("bookingApp");
			List<String> bookingApp = new ArrayList<String>();
			if (bookingAppInput.equals("All")) {
				bookingApp.add("Offline");
				bookingApp.add("Online");
				bookingApp.add("Mobile");
			} else {
				bookingApp.add(bookingAppInput);
			}
			Date fromDate = simpleDateFormat.parse(request.getString("fromDate"));
			Date endDate = simpleDateFormat.parse(request.getString("endDate"));
			Calendar endDateCalender = new GregorianCalendar();
			endDateCalender.setTime(endDate);
			endDateCalender.set(Calendar.HOUR_OF_DAY, 23);
			endDateCalender.set(Calendar.MINUTE, 59);
			endDateCalender.set(Calendar.SECOND, 29);
			endDate = endDateCalender.getTime();
			String center = request.getString("centerId");
			String facility = request.getString("facilityId");
			String subFacility = request.getString("subfacilityId");
			String paymentType = request.getString("typeOfPayment");
			if (center.equals("All") && facility.equals("All") && subFacility.equals("All")
					&& paymentType.equals("All")) {
				accountList = accountsRepository.getAllSelectedDays(fromDate, endDate, bookingApp);
			} else if (paymentType.equals("All")) {
				if (center.equals("All") && facility.equals("All") && subFacility.equals("All")) {
					accountList = accountsRepository.getAllMemberAccountSelectedDaysAllPayment(fromDate, endDate,
							bookingApp);
				} else if (facility.equals("All") && subFacility.equals("All")) {

					if (!center.equals("All")) {

						Optional<Center> centerOpt = centerRepository.findById(Long.parseLong(center));

						accountList = accountsRepository.getAllSelectedDaysAndCenterAllPayment(fromDate, endDate,
								centerOpt.get(), bookingApp);
					}
				} else if (subFacility.equals("All")) {
					if (!facility.equals("All")) {
						if (!(center.equals("All"))) {
							Optional<Center> centerOpt = centerRepository.findById(Long.parseLong(center));
							Facility facilities = facilityRepository
									.findByFacilityIdAndCenterId(Long.parseLong(facility), centerOpt.get());
							accountList = accountsRepository.getAllSelectedDaysCenterAndFacilityAllPayment(fromDate,
									endDate, centerOpt.get(), facilities, bookingApp);
						}
					}
				} else {
					if ((!center.equals("All") && !facility.equals("All") && !subFacility.equals("All"))) {
						Optional<Center> centerOpt = centerRepository.findById(Long.parseLong(center));
						Facility facilities = facilityRepository.findByFacilityIdAndCenterId(Long.parseLong(facility),
								centerOpt.get());
						SubFacility subFacilities = subFacilityRepository.findByFacilityIdAndSubFacilityId(facilities,
								Long.parseLong(subFacility));
						accountList = accountsRepository.getAllSelectedDaysAndCenterAndFacilityAndSubFacilityAllPayment(
								fromDate, endDate, centerOpt.get(), facilities, subFacilities, bookingApp);
					}
				}

			} else {
				if (paymentType.equals("New Member") || paymentType.equals("Member Renewal")) {
					if (center.equals("All")) {
						accountList = accountsRepository.getAllMemberAccountSelectedDays(fromDate, endDate, paymentType,
								bookingApp);
					} else {
						Optional<Center> centerOpt = centerRepository.findById(Long.parseLong(center));
						accountList = accountsRepository.getAllMemberAccountSelectedDaysCenter(fromDate, endDate,
								paymentType, centerOpt.get(), bookingApp);
					}
				} else if (paymentType.equals("Bookings") || paymentType.equals("Quick Bookings")) {
					if (center.equals("All") && facility.equals("All") && subFacility.equals("All")) {
						accountList = accountsRepository.getBookingAccountSelectedDays(fromDate, endDate, paymentType,
								bookingApp);
					} else if (facility.equals("All") && subFacility.equals("All")) {

						if (!center.equals("All")) {

							Optional<Center> centerOpt = centerRepository.findById(Long.parseLong(center));

							accountList = accountsRepository.getAllSelectedDaysAndCenter(fromDate, endDate,
									centerOpt.get(), paymentType, bookingApp);
						}
					} else if (subFacility.equals("All")) {
						if (!facility.equals("All")) {
							if (!(center.equals("All"))) {
								Optional<Center> centerOpt = centerRepository.findById(Long.parseLong(center));
								Facility facilities = facilityRepository
										.findByFacilityIdAndCenterId(Long.parseLong(facility), centerOpt.get());
								accountList = accountsRepository.getAllSelectedDaysCenterAndFacility(fromDate, endDate,
										centerOpt.get(), facilities, paymentType, bookingApp);
							}
						}
					} else {
						if ((!center.equals("All") && !facility.equals("All") && !subFacility.equals("All"))) {
							Optional<Center> centerOpt = centerRepository.findById(Long.parseLong(center));
							Facility facilities = facilityRepository
									.findByFacilityIdAndCenterId(Long.parseLong(facility), centerOpt.get());
							SubFacility subFacilities = subFacilityRepository
									.findByFacilityIdAndSubFacilityId(facilities, Long.parseLong(subFacility));
							accountList = accountsRepository.getAllSelectedDaysAndCenterAndFacilityAndSubFacility(
									fromDate, endDate, centerOpt.get(), facilities, subFacilities, paymentType,
									bookingApp);
						}
					}
				}
			}
			for (Accounts account : accountList) {
				AccountReportDTO accountReportDTO = new AccountReportDTO();
				accountReportDTO.setCreditAmount(account.getCreditAmount());
				accountReportDTO.setDebitAmount(account.getPaidAmount());
				accountReportDTO.setMemberName(account.getMemberId().getMemberName());
				accountReportDTO.setReceiptDate(account.getPaidDate());
				accountReportDTO.setReceiptNo(account.getAccountsId());
				accountReportDTO.setReceiptType(keyMap.get(account.getTypeOfBooking()));
				totalDebitAmount += account.getCreditAmount();
				totalCreditAmount += account.getPaidAmount();
				listBooking.add(accountReportDTO);

			}
			status = "Success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		accountReportOutputDTO.setTotalCreditAmount(totalCreditAmount);
		accountReportOutputDTO.setTotalDebitAmount(totalDebitAmount);
		accountReportOutputDTO.setData(listBooking);
		accountReportOutputDTO.setStatus(status);
		return accountReportOutputDTO;

	}

	@Override
	public ConsolidatedReportByDateOutputDTO getConsolidatedReportByDate(String data) {

		String status = "Failure";
		double totalAmount = 0;

		ConsolidatedReportByDateOutputDTO consolidatedReportByDateOutputDTO = new ConsolidatedReportByDateOutputDTO();
		List<ConsolidatedReportByDateDTO> listBooking = null;
		Map<Long, ConsolidatedReportByDateDTO> map = new HashMap<Long, ConsolidatedReportByDateDTO>();
		try {
			List<Accounts> accountList = null;
			JSONObject request = new JSONObject(data);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String bookingAppInput = request.getString("bookingApp");
			List<String> bookingApp = new ArrayList<String>();
			if (bookingAppInput.equals("All")) {
				bookingApp.add("Offline");
				bookingApp.add("Online");
				bookingApp.add("Mobile");
			} else {
				bookingApp.add(bookingAppInput);
			}
			Date fromDate = simpleDateFormat.parse(request.getString("fromDate"));
			Date endDate = simpleDateFormat.parse(request.getString("endDate"));
			Calendar endDateCalender = new GregorianCalendar();
			endDateCalender.setTime(endDate);
			endDateCalender.set(Calendar.HOUR_OF_DAY, 23);
			endDateCalender.set(Calendar.MINUTE, 59);
			endDateCalender.set(Calendar.SECOND, 29);
			endDate = endDateCalender.getTime();
			String center = request.getString("centerId");
			String facility = request.getString("facilityId");
			if (center.equals("All") && facility.equals("All")) {
				accountList = accountsRepository.getAllSelectedDays(fromDate, endDate, bookingApp);
			} else if (facility.equals("All") && (!center.equals("All"))) {

				Optional<Center> centerOpt = centerRepository.findById(Long.parseLong(center));

				accountList = accountsRepository.getAllSelectedDaysAndCenterAllPayment(fromDate, endDate,
						centerOpt.get(), bookingApp);
			} else if (!facility.equals("All") && !(center.equals("All"))) {
				Optional<Center> centerOpt = centerRepository.findById(Long.parseLong(center));
				Facility facilities = facilityRepository.findByFacilityIdAndCenterId(Long.parseLong(facility),
						centerOpt.get());
				accountList = accountsRepository.getAllSelectedDaysCenterAndFacilityAllPayment(fromDate, endDate,
						centerOpt.get(), facilities, bookingApp);
			}
			String dateOutput = request.getString("fromDate") + " to " + request.getString("endDate");
			for (Accounts account : accountList) {
				ConsolidatedReportByDateDTO accountReportDTO = null;
				long key = account.getAccountsSubId().iterator().hasNext()
						? account.getAccountsSubId().iterator().next().getFacilityId().getFacilityId()
						: 0L;
				if (key != 0L) {
					if (map.containsKey(key)) {
						accountReportDTO = map.get(key);
					} else {
						accountReportDTO = new ConsolidatedReportByDateDTO();
						map.put(key, accountReportDTO);
					}
					accountReportDTO.setDate(dateOutput);
					accountReportDTO.setFacility(
							account.getAccountsSubId().iterator().next().getFacilityId().getFacilityName());
					accountReportDTO.setAmount(accountReportDTO.getAmount() + account.getPaidAmount());
					totalAmount += account.getPaidAmount();
				}
			}
			listBooking = new ArrayList<ConsolidatedReportByDateDTO>(map.values());
			status = "Success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		consolidatedReportByDateOutputDTO.setData(listBooking);
		consolidatedReportByDateOutputDTO.setTotalAmount(totalAmount);
		consolidatedReportByDateOutputDTO.setStatus(status);
		return consolidatedReportByDateOutputDTO;

	}

	@Override
	public ConsolidatedReportByFacilityOutputDTO getConsolidatedReportByFacility(String data) {

		String status = "Failure";
		double totalAmount = 0;

		ConsolidatedReportByFacilityOutputDTO consolidatedReportByFacilityOutputDTO = new ConsolidatedReportByFacilityOutputDTO();
		List<ConsolidatedReportByFacilityDTO> listBooking = null;
		Map<String, ConsolidatedReportByFacilityDTO> map = new HashMap<String, ConsolidatedReportByFacilityDTO>();
		try {
			List<Accounts> accountList = null;
			JSONObject request = new JSONObject(data);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String bookingAppInput = request.getString("bookingApp");
			List<String> bookingApp = new ArrayList<String>();
			if (bookingAppInput.equals("All")) {
				bookingApp.add("Offline");
				bookingApp.add("Online");
				bookingApp.add("Mobile");
			} else {
				bookingApp.add(bookingAppInput);
			}
			Date fromDate = simpleDateFormat.parse(request.getString("fromDate"));
			Date endDate = simpleDateFormat.parse(request.getString("endDate"));
			Calendar endDateCalender = new GregorianCalendar();
			endDateCalender.setTime(endDate);
			endDateCalender.set(Calendar.HOUR_OF_DAY, 23);
			endDateCalender.set(Calendar.MINUTE, 59);
			endDateCalender.set(Calendar.SECOND, 29);
			endDate = endDateCalender.getTime();
			String center = request.getString("centerId");
			String facility = request.getString("facilityId");
			if (center.equals("All") && facility.equals("All")) {
				accountList = accountsRepository.getAllSelectedDays(fromDate, endDate, bookingApp);
			} else if (facility.equals("All") && (!center.equals("All"))) {

				Optional<Center> centerOpt = centerRepository.findById(Long.parseLong(center));

				accountList = accountsRepository.getAllSelectedDaysAndCenterAllPayment(fromDate, endDate,
						centerOpt.get(), bookingApp);
			} else if (!facility.equals("All") && !(center.equals("All"))) {
				Optional<Center> centerOpt = centerRepository.findById(Long.parseLong(center));
				Facility facilities = facilityRepository.findByFacilityIdAndCenterId(Long.parseLong(facility),
						centerOpt.get());
				accountList = accountsRepository.getAllSelectedDaysCenterAndFacilityAllPayment(fromDate, endDate,
						centerOpt.get(), facilities, bookingApp);
			}

			for (Accounts account : accountList) {
				ConsolidatedReportByFacilityDTO accountReportDTO = null;
				long facilityId = account.getAccountsSubId().iterator().hasNext()
						? account.getAccountsSubId().iterator().next().getFacilityId().getFacilityId()
						: 0L;
				if (facilityId != 0L) {
					String key = simpleDateFormat.format(account.getPaidDate())
							+ account.getAccountsSubId().iterator().next().getFacilityId().getFacilityId();
					if (map.containsKey(key)) {
						accountReportDTO = map.get(key);
					} else {
						accountReportDTO = new ConsolidatedReportByFacilityDTO();
						map.put(key, accountReportDTO);
					}
					accountReportDTO.setDate(account.getPaidDate());
					accountReportDTO.setFacility(
							account.getAccountsSubId().iterator().next().getFacilityId().getFacilityName());
					accountReportDTO.setAmount(accountReportDTO.getAmount() + account.getPaidAmount());
					totalAmount += account.getPaidAmount();
				}

			}
			listBooking = new ArrayList<ConsolidatedReportByFacilityDTO>(map.values());
			status = "Success";
		} catch (Exception e) {
			e.printStackTrace();
		}
		consolidatedReportByFacilityOutputDTO.setData(listBooking);
		consolidatedReportByFacilityOutputDTO.setTotalAmount(totalAmount);
		consolidatedReportByFacilityOutputDTO.setStatus(status);
		return consolidatedReportByFacilityOutputDTO;

	}
}