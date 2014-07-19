package com.rbs.mct.pagebeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.model.LazyDataModel;

import com.rbs.mct.delegate.MCTDelegate;
import com.rbs.mct.entity.BaseEntity;
import com.rbs.mct.entity.MCTRequest;
import com.rbs.mct.entity.MCTResponse;
import com.rbs.mct.entity.UserDetails;

public class ManageUsers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 943914288734060605L;

	private LazyDataModel<UserDetails> userModel;
	private UserDetails userDetails;
	private List<UserDetails> userDetalisList;

	public ManageUsers() {
		initLoad();
		userDetails = new UserDetails();
	}

	private void initLoad() {
		userDetalisList = retrieveUserDetails();
	}

	public LazyDataModel<UserDetails> getUserModel() {
		return userModel;
	}

	public void setUserModel(LazyDataModel<UserDetails> userModel) {
		this.userModel = userModel;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public List<UserDetails> getUserDetalisList() {
		return userDetalisList;
	}

	public void setUserDetalisList(List<UserDetails> userDetalisList) {
		this.userDetalisList = userDetalisList;
	}

	private List<UserDetails> retrieveUserDetails() {

		List<BaseEntity> resultList = null;
		List<UserDetails> userDetailsList = null;
		MCTRequest req = new MCTRequest();
		req.setEntityType(com.rbs.mct.constants.Constants.USERDETAIL_DAO);
		MCTDelegate delegate = new MCTDelegate();
		try {
			MCTResponse res = delegate.requestSearch(req);
			resultList = res.getBaseEntityList();
			userDetailsList = new ArrayList<UserDetails>();
			Iterator<BaseEntity> itr = resultList.iterator();
			while (itr.hasNext()) {
				UserDetails currentUserDetails = (UserDetails) itr.next();
				userDetailsList.add(currentUserDetails);
			}
			System.out.println("Size:" + userDetailsList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userDetailsList;
	}

	public String save() {

		MCTRequest req = new MCTRequest();
		req.setEntityType(com.rbs.mct.constants.Constants.USERDETAIL_DAO);
		userDetails.setUserName(userDetails.getEmailID());
		userDetails.setDateOfCreation(new Date());

		userDetails.setPassword(((int) Math.random() * 10000) + "");
		req.setBaseEntity(userDetails);
		MCTDelegate delegate = new MCTDelegate();
		try {
			MCTResponse res = delegate.requestInsert(req);
			initLoad();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Success :", "User has been added");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Failed to add :", e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, msg);
			e.printStackTrace();
		}
		return "";
	}

}
