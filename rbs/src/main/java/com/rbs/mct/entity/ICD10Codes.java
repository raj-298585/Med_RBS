package com.rbs.mct.entity;

import java.io.Serializable;
import java.util.Date;

public class ICD10Codes implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;
	public int id;
	public Date date;
	public String category;
	public Integer categoryId;
	public String cpt;
	public String description;
	private String descMore;
	public double fee;
	public int units;
	public double total;

	public ICD10Codes(int id, String category, String cpt, String description,
			double fee) {
		super();
		this.id = id;
		this.category = category;
		this.cpt = cpt;
		this.description = description;
		this.fee = fee;
		this.description = description;
		if (description.length() > 40)
			descMore = description.substring(0, 37) + "...";
		else
			descMore = description;
	}

	public ICD10Codes() {
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
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
		if (description.length() > 40)
			descMore = description.substring(0, 37) + "...";
		else
			descMore = description;
	}

	public String getDescMore() {
		return descMore;
	}

	public void setDescMore(String descMore) {
		this.descMore = descMore;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
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

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
