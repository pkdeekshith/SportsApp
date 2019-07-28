package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.AccountBook;
import com.ins.pos.entity.Accounts;
import com.ins.pos.entity.Booking;


public interface AccountTypeDao extends CrudRepository<AccountBook, Long>{

	public List<AccountBook> findByAccountsIdAndActive(Accounts id, boolean active);
	@Query("select ab from AccountBook ab JOIN FETCH  ab.accountsId where ab.bookingId.bookingId = ?1")
	public AccountBook findByBookingId(Long bookingId);
}
