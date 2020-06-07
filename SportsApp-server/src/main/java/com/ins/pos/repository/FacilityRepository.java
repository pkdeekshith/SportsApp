package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Center;
import com.ins.pos.entity.Facility;
import com.ins.pos.entity.FacilityType;

public interface FacilityRepository extends CrudRepository<Facility, Long> {
	public List<Facility> findByActive(Boolean active);

	public List<Facility> findByCenterIdAndOnlineActiveAndActive(Center centerId,
			Boolean onlineActive, Boolean active);
	
	public Facility findByFacilityName(String facilityName);
	
	public Facility findByFacilityIdAndCenterId(long facilityId,Center centerId);

	public List<Facility> findByCenterIdAndFacilityTypeIdAndOnlineActiveAndActive(Center centerId, FacilityType facilityTypeId,
			Boolean onlineActive, Boolean active);

	public List<Facility> findByCenterIdAndActive(Center centerId, boolean active);
	
	public List<Facility> findByCenterId(Center centerId);

}