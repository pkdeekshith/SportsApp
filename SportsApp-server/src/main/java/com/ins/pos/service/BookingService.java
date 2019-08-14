package com.ins.pos.service;

import java.util.List;

import com.ins.pos.dto.BookingDetailsJsonDTO;
import com.ins.pos.dto.BookingJsonDTO;

public interface BookingService {

	public String saveBooking(BookingJsonDTO data);

	public List<BookingDetailsJsonDTO> getAllBookingForMember(long parseLong);

	public List<BookingDetailsJsonDTO> getUpcomingBookingForMember(long parseLong);
	
}
