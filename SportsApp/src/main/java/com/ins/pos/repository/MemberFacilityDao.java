package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Member;
import com.ins.pos.entity.MemberFacility;

public interface MemberFacilityDao extends CrudRepository<MemberFacility, Long> {
	public List<MemberFacility> findByMember(Member member);
	public void deleteByMember(Member member);
}

