package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ins.pos.entity.AccountsSubSector;

public interface AccountsSubSectorDao extends CrudRepository<AccountsSubSector, Long>{
	
	
	public Iterable<AccountsSubSector> findByStatus(Boolean active);
	
	@Query(value="SELECT a FROM AccountsSubSector a JOIN FETCH a.subFacilityId JOIN FETCH a.facilityId WHERE a.accountsId.accountsId = :accountsId")
	public List<AccountsSubSector> findByAccountsId(@Param("accountsId") Long accountsId);

}
