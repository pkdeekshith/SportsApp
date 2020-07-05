package com.ins.pos.dto;

public class CenterDetailsJsonDTO {

	private Long centreId;
	private String centreName;
	private Boolean onlineActive;
	private Boolean active;
	public Long getCentreId() {
		return centreId;
	}
	public void setCentreId(Long centreId) {
		this.centreId = centreId;
	}
	public String getCentreName() {
		return centreName;
	}
	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}
	public Boolean getOnlineActive() {
		return onlineActive;
	}
	public void setOnlineActive(Boolean onlineActive) {
		this.onlineActive = onlineActive;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}

	
}
