package com.ins.pos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Holiday;

public interface HolidayDao extends CrudRepository<Holiday, Long> {
	

}
