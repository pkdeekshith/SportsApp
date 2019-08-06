package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.LeaveTab;
import com.ins.pos.entity.SubFacility;

public interface LeaveTabDao extends CrudRepository<LeaveTab, Long>{
	public Iterable<LeaveTab> findByActive(Boolean active);

	public List<LeaveTab> findByActiveAndSubFacilityId(boolean b, SubFacility subFacility);
}