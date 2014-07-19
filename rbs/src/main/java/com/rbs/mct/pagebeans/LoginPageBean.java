package com.rbs.mct.pagebeans;

import com.rbs.mct.entity.LUTUserInformation;

public class LoginPageBean {

	private LUTUserInformation loginentity = new LUTUserInformation();
	private boolean isLoggedIn;
	private String displayName;
	private String accessLevel;

	public LUTUserInformation getLoginentity() {
		return loginentity;
	}

	public void setLoginentity(LUTUserInformation loginentity) {
		this.loginentity = loginentity;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String authenticate() {

		return "home";
	}

	public String logout() {
		return "";
	}
}
