package com.ins.pos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Trainer {
	
@Id
@GeneratedValue
@Column
private Long trainerFinalId;

@Column
private String trainerName;

@Column
private String emailId;


@Column
private String phoneNo;
public String getPhoneNo() {
	return phoneNo;
}

public void setPhoneNo(String phoneNo) {
	this.phoneNo = phoneNo;
}

@Column 
private Boolean active;


public Long getTrainerFinalId() {
	return trainerFinalId;
}

public void setTrainerFinalId(Long trainerFinalId) {
	this.trainerFinalId = trainerFinalId;
}

public String getTrainerName() {
	return trainerName;
}

public void setTrainerName(String trainerName) {
	this.trainerName = trainerName;
}

public String getEmailId() {
	return emailId;
}

public void setEmailId(String emailId) {
	this.emailId = emailId;
}

public Boolean getActive() {
	return active;
}

public void setActive(Boolean active) {
	this.active = active;
}


}
