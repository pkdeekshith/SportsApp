package com.ins.pos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.CenterType;

public interface CenterTypeDao extends CrudRepository<CenterType, Long> {
	public CenterType findByCenterTypeName(String centerTypeName);

	public Iterable<CenterType> findByActive(boolean active);

}
