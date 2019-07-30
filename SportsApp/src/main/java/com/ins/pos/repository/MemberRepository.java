package com.ins.pos.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Member;

public interface MemberRepository extends CrudRepository<Member, Long> {
	Iterable<Member> findByActiveAndCheckStaff(boolean active,boolean checkActive);
	
	Iterable<Member> findByActiveAndCheckStaffAndSpotCheck(boolean active,boolean checkActive,boolean checks);
	
	public Member findByUserName(String userName);
	
	public Member findByMemberName(String memberName);
	
	@Query(value="SELECT * FROM MEMBER WHERE MEMBER_TYPE_VALIDITY<curdate() AND ACTIVE=? AND CHECK_STAFF=? AND SPOT_CHECK=?",nativeQuery=true)
	Iterable<Member> findActiveAndCheckMemberRenewalAndcheckSpot(boolean active,boolean checkActive,boolean check);

	
	@Query(value="SELECT * FROM MEMBER WHERE MEMBER_TYPE_VALIDITY<curdate() AND ACTIVE=?  AND SPOT_CHECK=?",nativeQuery=true)
	Iterable<Member> findActiveRenewalAndcheckSpot(boolean active,boolean check);

}

