package com.ins.pos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ins.pos.entity.PaymentOrderStatus;

public interface PaymentOrderStatusRepository extends JpaRepository<PaymentOrderStatus, String> {

	PaymentOrderStatus findByOrderId(String orderId);

	List<PaymentOrderStatus> findByStatusNotAndCreatedDateLessThan(String status, Date createdDate);

	PaymentOrderStatus findByLeftPadAndOrderIdAndRightPad(String leftPad, String orderId, String rightPad);

	@Query(value = "SELECT p FROM PaymentOrderStatus p WHERE p.createdDate>=:startDate and p.createdDate<:endDate and p.status IN :status")
	public List<PaymentOrderStatus> getAllPaymentOrders(@Param("startDate") Date startDate,
			@Param("endDate") Date endDate, @Param("status") List<String> status);

	}
