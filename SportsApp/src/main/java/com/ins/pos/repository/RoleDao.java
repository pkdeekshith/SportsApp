package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Role;

public interface RoleDao extends CrudRepository<Role, Long> {
	List<Role> findByActive(boolean active);
	
	
	public Role findByRoleName(String roleName);
	
	
}
