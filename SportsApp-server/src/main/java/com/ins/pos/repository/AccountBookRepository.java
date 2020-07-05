package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.AccountBook;
import com.ins.pos.entity.Accounts;


public interface AccountBookRepository extends CrudRepository<AccountBook, Long>{
	
	List<AccountBook> findByAccountsId(Accounts accountsId);

	
}
