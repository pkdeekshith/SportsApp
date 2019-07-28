package com.ins.pos.repository;


import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Center;

public interface CenterRepository extends CrudRepository<Center, Long> {
	public Center findByCentreName(String centreName);

	public Iterable<Center> findByActive(Boolean active);
	
}
