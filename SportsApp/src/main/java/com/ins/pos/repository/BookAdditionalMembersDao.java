package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.BookAdditionalMembers;
import com.ins.pos.entity.Member;

public interface BookAdditionalMembersDao extends CrudRepository<BookAdditionalMembers, Long>{
	
	
	
	public Iterable<BookAdditionalMembers> findByActive(Boolean active);
	
	
	
	@Query(value="select * from book_additional_members where booking_id=? and active=1", nativeQuery = true)
	public List<BookAdditionalMembers> memberPrimaryIdAndActive(Long bookingId);
	
	@Query(value="select * from book_additional_members where accounts_id=? and active=1", nativeQuery = true)
	public List<BookAdditionalMembers> accountIdAndActive(Long accountId);


}
