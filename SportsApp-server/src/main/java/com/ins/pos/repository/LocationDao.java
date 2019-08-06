package com.ins.pos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Location;

public interface LocationDao extends CrudRepository<Location, Long> {
	public Location findByLocationName(String locationName);

	public Iterable<Location> findByActive(boolean active);

}
