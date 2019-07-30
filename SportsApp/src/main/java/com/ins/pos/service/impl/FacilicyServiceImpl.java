package com.ins.pos.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONArray;
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
			JSONArray facilityTypeIdJSONArray = jsonData.getJSONArray("facilityTypeId");
			Optional<Center> center = centerRepository.findById(Long.parseLong(centerId));
			List<Long> facilityTypeIdArray = new ArrayList<Long>();
			for (Object value : facilityTypeIdJSONArray) {
				facilityTypeIdArray.add(Long.valueOf((String) value));
			}
			Iterable<FacilityType> facilityType = facilityTypeRepository.findAllById(facilityTypeIdArray);
			if (center.isPresent() && facilityType != null) {
				for (FacilityType facType : facilityType) {
					List<Facility> facilityList = facilityRepository
							.findByCenterIdAndFacilityTypeIdAndOnlineActiveAndActive(center.get(), facType, true,true);
					for (Facility facility : facilityList) {
						FacilityJsonDTO facilityJsonDTO = new FacilityJsonDTO();
						BeanUtils.copyProperties(facility, facilityJsonDTO);
						facilityJsonDTOList.add(facilityJsonDTO);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return facilityJsonDTOList;
	}

}
