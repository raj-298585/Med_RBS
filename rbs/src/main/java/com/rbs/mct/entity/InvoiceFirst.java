package com.rbs.mct.entity;

import java.io.Serializable;
import java.util.Date;

public class InvoiceFirst implements Serializable, BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date date;
	private String category;
	private String cpt;
	private String description;
	private Double fee;
	private Integer units;
	private Float total;

	public InvoiceFirst() {
	}

	public InvoiceFirst(String category, String cpt, String description, Double fee) {
		super();
		this.category = category;
		this.cpt = cpt;
		this.description = description;
		this.fee = fee;
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

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

}
