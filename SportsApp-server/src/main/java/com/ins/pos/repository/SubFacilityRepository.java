package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Facility;
import com.ins.pos.entity.SubFacility;

public interface SubFacilityRepository extends CrudRepository<SubFacility, Long> {
	public List<SubFacility> findByFacilityIdAndActiveAndOnlineActive(Facility facility, Boolean active, Boolean onlineActive);
	public List<SubFacility> findByFacilityId(Facility facility);

	public SubFacility findByFacilityIdAndSubFacilityId(Facility facility, long subFacilityId);
}
