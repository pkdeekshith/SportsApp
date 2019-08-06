package com.ins.pos.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ins.pos.entity.Center;
import com.ins.pos.entity.Facility;
import com.ins.pos.entity.MasterType;
import com.ins.pos.entity.Price;
import com.ins.pos.entity.SubFacility;

public interface PriceRepository extends CrudRepository<Price, Long> {
	List<Price> findByActive(boolean active);

	List<Price> findByActiveAndSubFacilityId(boolean b, SubFacility subFacility);

	List<Price> findByActiveAndFacilityId(boolean b, Facility facility);

	List<Price> findByActiveAndCentreId(boolean b, Center center);
	
	Price findByActiveAndSubFacilityIdAndMasterTypeId(Boolean active, SubFacility subFacilityId, MasterType masterTypeId);

	Price findByActiveAndFacilityIdAndMasterTypeId(Boolean active, Facility facility, MasterType masterTypeId);


	Price findByActiveAndCentreIdAndMasterTypeId(Boolean active,Center center,MasterType masterTypeId);

}
