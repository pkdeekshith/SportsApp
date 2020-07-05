package com.ins.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ins.pos.entity.PaymentLog;


public interface PaymentLogRepository extends JpaRepository<PaymentLog, Long>{

	
}
