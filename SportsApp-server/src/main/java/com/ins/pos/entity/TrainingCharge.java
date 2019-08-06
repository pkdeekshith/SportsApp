package com.ins.pos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class TrainingCharge {
	
	@Id
	@GeneratedValue
	@Column
	private Long trainerChargeId;
	
	@Column
	private Double trainerCharge;
	


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "facility_id", nullable = false)
	private Facility facilityId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "masterType_id", nullable = false)
	private MasterType masterTypeId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trainer_final_id", nullable = false)
	private Trainer trainerFinalId;
	
	@Column
	private Boolean active;

	

	public Long getTrainerChargeId() {
		return trainerChargeId;
	}

	public void setTrainerChargeId(Long trainerChargeId) {
		this.trainerChargeId = trainerChargeId;
	}

	public Trainer getTrainerFinalId() {
		return trainerFinalId;
	}

	public void setTrainerFinalId(Trainer trainerFinalId) {
		this.trainerFinalId = trainerFinalId;
	}

	public Double getTrainerCharge() {
		return trainerCharge;
	}

	public void setTrainerCharge(Double trainerCharge) {
		this.trainerCharge = trainerCharge;
	}

	public Facility getFacilityId() {
		return facilityId;
	}

	public void setFacilityId(Facility facilityId) {
		this.facilityId = facilityId;
	}

	public MasterType getMasterTypeId() {
		return masterTypeId;
	}

	public void setMasterTypeId(MasterType masterTypeId) {
		this.masterTypeId = masterTypeId;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
	
	

}
