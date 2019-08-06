package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ins.pos.entity.MemberShipType;

@Repository
public interface MemberShipTypeRepository extends CrudRepository<MemberShipType, Long> {
	public List<MemberShipType> findByActiveAndOnlineActive(Boolean active, Boolean onlineActive);

}
