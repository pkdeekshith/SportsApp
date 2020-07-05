package com.ins.pos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ins.pos.entity.Center;
import com.ins.pos.entity.Member;
import com.ins.pos.entity.MemberCenter;

public interface MemberCenterRepository extends CrudRepository<MemberCenter, Long> {
	MemberCenter findByMember(Member member);
	 
	@Query("select m from MemberCenter mc join mc.member m where mc.center=:center and m.memberTypeValidity>=:memberTypeValidity and m.active=:active")
	public List<Member> getMemberforCenter(@Param("center") Center center,
			@Param("memberTypeValidity") Date memberTypeValidity, @Param("active") Boolean active);
	}

