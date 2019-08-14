package com.ins.pos.ep;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ins.pos.dto.BookingDetailsJsonDTO;
import com.ins.pos.dto.BookingJsonDTO;
import com.ins.pos.service.BookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingEndPoint { 
	
	@Autowired
	BookingService bookingService;
	
	@PostMapping("/saveBooking")
	public String saveBooking(@RequestBody BookingJsonDTO data) {
		return bookingService.saveBooking(data);
	}
	
	@RequestMapping("/getAllBookingForMember/{id}")
	public List<BookingDetailsJsonDTO> getAllBookingForMember(@PathVariable String id) {
		return bookingService.getAllBookingForMember(Long.parseLong(id));
	}

	@RequestMapping("/getUpcomingBookingForMember/{id}")
	public List<BookingDetailsJsonDTO> getUpcomingBookingForMember(@PathVariable String id) {
		return bookingService.getUpcomingBookingForMember(Long.parseLong(id));
	}
	
}
