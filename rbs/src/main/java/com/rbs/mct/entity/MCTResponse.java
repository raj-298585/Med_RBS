package com.rbs.mct.entity;

import java.util.ArrayList;
import java.util.List;

public class MCTResponse {

	List<BaseEntity> baseEntityList;
	ArrayList<String> messages;

	public List<BaseEntity> getBaseEntityList() {
		return baseEntityList;
	}

	public void setBaseEntityList(List<BaseEntity> baseEntityList) {
		this.baseEntityList = baseEntityList;
	}

	public ArrayList<String> getMessages() {
		return messages;
	}

	public void setMessages(ArrayList<String> messages) {
		this.messages = messages;
	}

}
