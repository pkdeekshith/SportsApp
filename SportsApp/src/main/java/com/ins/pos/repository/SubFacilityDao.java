package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Facility;
import com.ins.pos.entity.SubFacility;

public interface SubFacilityDao extends CrudRepository<SubFacility, Long> {
	public List<SubFacility> findByFacilityIdAndActive(Facility facility, Boolean active);

	public List<SubFacility> findByActive(boolean active);
	
	
	
	
	@Query(value="select * from sub_facility where sub_facility_name=? and facility_id=?",nativeQuery=true)
	public SubFacility subFacilityNamesAndFacilityID(String name,Long facilityId);
}
