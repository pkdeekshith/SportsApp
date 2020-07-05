package com.ins.pos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ins.pos.dto.CenterJsonDTO;
import com.ins.pos.entity.Center;
import com.ins.pos.repository.CenterRepository;
import com.ins.pos.service.CenterService;

@Service
public class CenterServiceImpl implements CenterService {

	@Autowired
	private CenterRepository centerRepository;

	@Override
	public List<CenterJsonDTO> getAllCenters() {
		List<Center> centerList = centerRepository.findByOnlineActiveAndActive(true, true);
		List<CenterJsonDTO> centerJSONList = new ArrayList<CenterJsonDTO>();
		for (Center center : centerList) {
			CenterJsonDTO centerJsonDTO = new CenterJsonDTO();
			centerJsonDTO.setCentreId(center.getCentreId());
			centerJsonDTO.setCentreName(center.getCentreName());
			centerJSONList.add(centerJsonDTO);
		}
		return centerJSONList;
	}

}
