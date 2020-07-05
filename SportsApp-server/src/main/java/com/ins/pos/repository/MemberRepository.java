package com.ins.pos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Member;

public interface MemberRepository extends CrudRepository<Member, Long> {
	Iterable<Member> findByActiveAndCheckStaff(boolean active,boolean checkActive);
	
	Iterable<Member> findByActiveAndCheckStaffAndSpotCheck(boolean active,boolean checkActive,boolean checks);
	
	public List<Member> findByUserNameAndActive(String userName,Boolean active);
	
	public Member findByMemberName(String memberName);
	
	public Member findByMemberIdAndActive(Long memberId,Boolean active);
	
	public List<Member> findByActiveAndMemberTypeValidityGreaterThanEqual(Boolean active, Date memberTypeValidity);
	
	
	@Query(value="SELECT * FROM MEMBER WHERE MEMBER_TYPE_VALIDITY<curdate() AND ACTIVE=? AND CHECK_STAFF=? AND SPOT_CHECK=?",nativeQuery=true)
	Iterable<Member> findActiveAndCheckMemberRenewalAndcheckSpot(boolean active,boolean checkActive,boolean check);

	
	@Query(value="SELECT * FROM MEMBER WHERE MEMBER_TYPE_VALIDITY<curdate() AND ACTIVE=?  AND SPOT_CHECK=?",nativeQuery=true)
	Iterable<Member> findActiveRenewalAndcheckSpot(boolean active,boolean check);

}

