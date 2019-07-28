package com.ins.pos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class MatPrice {
	@Id
	@GeneratedValue
	@Column
	private Long matPriceId;
	
	@Column
	private String matPriceName;
	
	@Column
	private Double matPriceCost;
	
	@Column
	private Boolean active;

	public Long getMatPriceId() {
		return matPriceId;
	}

	public void setMatPriceId(Long matPriceId) {
		this.matPriceId = matPriceId;
	}

	public String getMatPriceName() {
		return matPriceName;
	}

	public void setMatPriceName(String matPriceName) {
		this.matPriceName = matPriceName;
	}

	public Double getMatPriceCost() {
		return matPriceCost;
	}

	public void setMatPriceCost(Double matPriceCost) {
		this.matPriceCost = matPriceCost;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	

}
