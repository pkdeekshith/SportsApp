package com.ins.pos.dto;

import java.util.List;

public class BooingWindowFacilityJsonDTO {
	private List<FacilityJsonDTO> facility;
	private List<String> message;

	public List<FacilityJsonDTO> getFacility() {
		return facility;
	}

	public void setFacility(List<FacilityJsonDTO> facility) {
		this.facility = facility;
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}
}
