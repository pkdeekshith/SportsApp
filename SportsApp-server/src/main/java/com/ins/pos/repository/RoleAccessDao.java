package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Role;
import com.ins.pos.entity.RoleAccess;

public interface RoleAccessDao extends CrudRepository<RoleAccess, Long> {
	List<RoleAccess> findByRoleId(Role roleId);
}