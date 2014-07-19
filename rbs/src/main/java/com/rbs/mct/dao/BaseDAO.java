package com.rbs.mct.dao;

import java.util.HashMap;

import com.rbs.mct.entity.MCTRequest;
import com.rbs.mct.entity.MCTResponse;

public class BaseDAO {

	public MCTResponse search(MCTRequest request) throws Exception {
		return null;
	};

	public MCTResponse insert(MCTRequest request) throws Exception {
		return null;
	};

	public MCTResponse update(MCTRequest request) throws Exception {
		return null;
	};

	public MCTResponse validateUserCredentials(
			HashMap<String, Object> searchCriteria) throws Exception {
		return null;
	};

	public MCTResponse delete(MCTRequest request) throws Exception {
		return null;
	};
}
