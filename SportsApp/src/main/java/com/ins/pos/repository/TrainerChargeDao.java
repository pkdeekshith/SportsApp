package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.MasterType;
import com.ins.pos.entity.TrainingCharge;

public interface TrainerChargeDao extends CrudRepository<TrainingCharge, Long>{

	
	public Iterable<TrainingCharge> findByActive(Boolean active);
	
	public List<TrainingCharge> findByMasterTypeIdAndActive(MasterType masterTypeId, Boolean active);
	
	
	
}
