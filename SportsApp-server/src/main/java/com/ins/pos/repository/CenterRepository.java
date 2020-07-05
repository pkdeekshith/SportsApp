package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Center;

public interface CenterRepository extends CrudRepository<Center, Long> {
	public Center findByCentreName(String centreName);

	public Iterable<Center> findByActive(Boolean active);

	public List<Center> findByOnlineActiveAndActive(Boolean onlineActive, Boolean active);

}
