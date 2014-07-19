package com.rbs.mct.entity;

import java.io.Serializable;

public class LUTUserInformation implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;
	private Long associateId;
	private String userName;
	private String password;
	private Integer roleId;
	private Integer lobId;
	private String emailAddress;
	private char status;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getAssociateId() {
		return associateId;
	}

	public void setAssociateId(Long associateId) {
		this.associateId = associateId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getLobId() {
		return lobId;
	}

	public void setLobId(Integer lobId) {
		this.lobId = lobId;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

}
