package com.ins.pos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.PaymentRequest;

public interface PaymentRequestRepository extends CrudRepository<PaymentRequest, Long> {

}