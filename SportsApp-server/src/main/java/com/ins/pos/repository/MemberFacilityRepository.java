package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Member;
import com.ins.pos.entity.MemberFacility;

public interface MemberFacilityRepository extends CrudRepository<MemberFacility, Long> {
	public List<MemberFacility> findByMemberAndActive(Member member, Boolean active);
	
	}

