package com.ins.pos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.MatPrice;

public interface MatPriceDao extends CrudRepository<MatPrice, Long>{
	
	public Iterable<MatPrice> findByActive(Boolean active);

	public MatPrice findByMatPriceName(String matPriceName);
	

}
