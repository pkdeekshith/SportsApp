package com.ins.pos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Trainer;

public interface TrainerDao extends CrudRepository<Trainer, Long>{
	
	
	
	
	public Trainer findByTrainerName(String trainerName);
	
	Iterable<Trainer> findByActive(Boolean active);

}
