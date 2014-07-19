package com.rbs.mct.entity;

import java.io.Serializable;
import java.util.Date;

public class Invoice implements Serializable {
	private int id;
	private Date date;
	private String category;
	private String cpt;
	private String description;
	private float units;
	private double fee;
	private double total;

	public Invoice(Date date,int id, String category, String cpt, String description,
			double fee) {
		super();
		this.date=date;
		this.id = id;
		this.category = category;
		this.cpt = cpt;
		this.description = description;
		this.fee = fee;
	}

	public Invoice() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCpt() {
		return cpt;
	}

	public void setCpt(String cpt) {
		this.cpt = cpt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getUnits() {
		return units;
	}

	public void setUnits(float units) {
		this.units = units;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
