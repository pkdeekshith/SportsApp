package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Role;
import com.ins.pos.entity.User;
import com.ins.pos.entity.UserRoles;


public interface UserRolesRepository extends CrudRepository<UserRoles, Long>{
	
	UserRoles findByUserAndRoleAndActive(User user, Role role, Boolean active);
	
	List<UserRoles> findByUserAndActive(User user, Boolean active);

	
}
