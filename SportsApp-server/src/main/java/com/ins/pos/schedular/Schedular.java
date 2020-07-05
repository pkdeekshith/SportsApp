package com.ins.pos.schedular;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.ins.pos.entity.Accounts;
import com.ins.pos.entity.PaymentOrderStatus;
import com.ins.pos.repository.AccountsRepository;
import com.ins.pos.repository.PaymentOrderStatusRepository;

@Configuration
@EnableScheduling
public class Schedular {

	@Autowired
	private PaymentOrderStatusRepository paymentOrderStatusRepository;

	@Autowired
	private AccountsRepository accountsRepository;

	@Scheduled(fixedRate = 60000)
	public void scheduleFixedRateTask() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -5);
		List<PaymentOrderStatus> paymentOrderList = paymentOrderStatusRepository
				.findByStatusNotAndCreatedDateLessThan("S", cal.getTime());
		if (paymentOrderList != null) {
			paymentOrderList.forEach((po) -> {
				Accounts a = po.getAccountId1();
				if (a != null) {
					if (a.getOnHold()!=null&&a.getOnHold()) {
						a.setOnHold(false);
					}
					a = accountsRepository.save(a);
				}

				if (po.getAccountId2() != null) {
					Accounts a1 = po.getAccountId2();
					if (a1 != null) {
						if (a1.getOnHold()!=null&&a1.getOnHold()) {
							a1.setOnHold(false);
						}
						a1 = accountsRepository.save(a1);
					}
				}
				po.setStatus("C");
				paymentOrderStatusRepository.save(po);
			});
		}
	}

}
