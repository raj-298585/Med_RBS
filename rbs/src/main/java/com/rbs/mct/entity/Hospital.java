package com.rbs.mct.entity;

import java.io.Serializable;

public class Hospital implements Serializable, BaseEntity {
	private Integer hospitalId;
	private String hospitalName;
	private String hospitalLoc;

	public Integer getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(Integer hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalLoc() {
		return hospitalLoc;
	}

	public void setHospitalLoc(String hospitalLoc) {
		this.hospitalLoc = hospitalLoc;
	}

}
