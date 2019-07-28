package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.FacilityType;

public interface FacilityTypeRepository extends CrudRepository<FacilityType, Long> {
	public FacilityType findByFacilityTypeName(String facilityTypeName);

	public List<FacilityType> findByActiveAndOnlineActive(boolean active, boolean onlineActive);
}
