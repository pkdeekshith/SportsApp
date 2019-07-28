package com.ins.pos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ins.pos.entity.MemberShipType;

@Repository
public interface MemberShipDao extends CrudRepository<MemberShipType, Long> {
	public Iterable<MemberShipType> findByActive(Boolean active);

	public MemberShipType findByMemberShipTypeName(String memberShipTypeName);
}
