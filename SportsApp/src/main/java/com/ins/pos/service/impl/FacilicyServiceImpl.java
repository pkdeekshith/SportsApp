package com.ins.pos.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ins.pos.dto.FacilityJsonDTO;
import com.ins.pos.dto.FacilityTypeJsonDTO;
import com.ins.pos.entity.Center;
import com.ins.pos.entity.Facility;
import com.ins.pos.entity.FacilityType;
import com.ins.pos.repository.CenterRepository;
import com.ins.pos.repository.FacilityRepository;
import com.ins.pos.repository.FacilityTypeRepository;
import com.ins.pos.service.FacilityService;

@Service
public class FacilicyServiceImpl implements FacilityService{

	@Autowired
	private FacilityTypeRepository facilityTypeRepository;
	
	@Autowired
	private FacilityRepository facilityRepository;
	
	@Autowired
	private CenterRepository centerRepository;
	
	@Override
	public List<FacilityTypeJsonDTO> getAllFacilityTypes() {
		List<FacilityTypeJsonDTO> facilityTypeJsonDTOList= new ArrayList<FacilityTypeJsonDTO>();
		for(FacilityType facilityType:facilityTypeRepository.findByActiveAndOnlineActive(true, true)) {
			FacilityTypeJsonDTO facilityTypeJsonDTO = new FacilityTypeJsonDTO();
			BeanUtils.copyProperties(facilityType,facilityTypeJsonDTO );
			facilityTypeJsonDTOList.add(facilityTypeJsonDTO);
		}
		return facilityTypeJsonDTOList;
	}

	@Override
	public List<FacilityJsonDTO> getFacilityForPreferredSport(String data) {
		List<FacilityJsonDTO> facilityJsonDTOList = new ArrayList<FacilityJsonDTO>();
		try {
			JSONObject jsonData = new JSONObject(data);
			String centerId = jsonData.getString("centerId");
			String facilityTypeId = jsonData.getString("facilityTypeId");
			Optional<Center> center = centerRepository.findById(Long.parseLong(centerId));
			Optional<FacilityType> facilityType = facilityTypeRepository.findById(Long.parseLong(facilityTypeId));
			if (center.isPresent() && facilityType.isPresent()) {
				List<Facility> facilityList = facilityRepository
						.findByCenterIdAndFacilityTypeIdAndOnlineActive(center.get(), facilityType.get(), true);
				for (Facility facility : facilityList) {
					FacilityJsonDTO facilityJsonDTO = new FacilityJsonDTO();
					BeanUtils.copyProperties(facility, facilityJsonDTO);
					facilityJsonDTOList.add(facilityJsonDTO);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return facilityJsonDTOList;
	}

}
