package com.ins.pos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.LeaveType;

public interface LeaveTypeDao extends CrudRepository<LeaveType, Long> {
	public LeaveType findByLeaveTypeName(String leaveTypeName);

	public Iterable<LeaveType> findByActive(boolean active);
}
