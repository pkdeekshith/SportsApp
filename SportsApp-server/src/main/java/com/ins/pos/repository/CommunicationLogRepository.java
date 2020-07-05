package com.ins.pos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ins.pos.entity.CommunicationLog;
import com.ins.pos.entity.PaymentOrderStatus;

public interface CommunicationLogRepository extends JpaRepository<CommunicationLog, String> {

	PaymentOrderStatus findByOrderId(String orderId);

}
