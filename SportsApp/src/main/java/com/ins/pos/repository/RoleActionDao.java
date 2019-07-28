package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Role;
import com.ins.pos.entity.RoleAction;

public interface RoleActionDao extends CrudRepository<RoleAction, Long> {

	public Iterable<RoleAction> findByActive(boolean active);

	public RoleAction findByRoleId(Role roleId);

	public List<RoleAction> findByPageNameAndRoleId(String pageName, Role roleId);

	public RoleAction findByActionNameAndRoleIdAndActive(String actionName, Role role, Boolean active);

}
