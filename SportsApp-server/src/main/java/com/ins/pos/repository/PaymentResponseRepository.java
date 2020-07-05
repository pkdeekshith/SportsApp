package com.ins.pos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.PaymentResponse;

public interface PaymentResponseRepository extends CrudRepository<PaymentResponse, Long> {
	
	PaymentResponse findByOrderId(String orderId);

}