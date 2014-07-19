package com.rbs.mct.entity;

import java.io.Serializable;
import java.util.Date;

public class UserDetails implements Serializable, BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3843865566929863637L;
	private String firstName;
	private String lastName;
	private String emailID;
	private String contactNumber;
	private String company;
	private String userName;
	private String password;
	private Date dateOfCreation;
	private String role;

	public UserDetails() {

	}

	public UserDetails(String firstName, String lastName, String emailID,
			String contactNumber, String company, String userName,
			String password, Date dateOfCreation, String role) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailID = emailID;
		this.contactNumber = contactNumber;
		this.company = company;
		this.userName = userName;
		this.password = password;
		this.dateOfCreation = dateOfCreation;
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateOfCreation() {
		return dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
