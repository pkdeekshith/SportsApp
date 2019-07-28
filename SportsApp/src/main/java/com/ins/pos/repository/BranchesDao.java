package com.ins.pos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Branch;


public interface BranchesDao extends CrudRepository<Branch, Long>{
	

}
