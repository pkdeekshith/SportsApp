package com.ins.pos.ep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	

}
