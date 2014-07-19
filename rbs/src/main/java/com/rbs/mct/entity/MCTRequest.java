package com.rbs.mct.entity;

import java.util.HashMap;
import java.util.List;

public class MCTRequest {

	protected String entityType;
	protected HashMap<String, Object> entitySearchMap;
	protected BaseEntity baseEntity;
	protected List<BaseEntity> baseEntityList;

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public HashMap<String, Object> getEntitySearchMap() {
		return entitySearchMap;
	}

	public void setEntitySearchMap(HashMap<String, Object> entitySearchMap) {
		this.entitySearchMap = entitySearchMap;
	}

	public BaseEntity getBaseEntity() {
		return baseEntity;
	}

	public void setBaseEntity(BaseEntity baseEntity) {
		this.baseEntity = baseEntity;
	}

	public List<BaseEntity> getBaseEntityList() {
		return baseEntityList;
	}

	public void setBaseEntityList(List<BaseEntity> baseEntityList) {
		this.baseEntityList = baseEntityList;
	}

}
