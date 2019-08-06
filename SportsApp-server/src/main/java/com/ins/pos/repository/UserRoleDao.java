package com.ins.pos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.UserRole;

public interface UserRoleDao extends CrudRepository<UserRole, Integer> {
}
