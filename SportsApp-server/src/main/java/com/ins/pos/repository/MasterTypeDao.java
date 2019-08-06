package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.MasterType;

public interface MasterTypeDao extends CrudRepository<MasterType, Long> {

	Iterable<MasterType> findByActive(boolean active);

	public MasterType findByMasterTypeName(String masterTypeName);

	List<MasterType> findByCenterCheckAndActive(boolean centerCheck, boolean active);

	List<MasterType> findByFacilityCheckAndActive(boolean facilityCheck, boolean active);

	List<MasterType> findBySubFacilityCheckAndActive(boolean subFacilityCheck, boolean active);

}
