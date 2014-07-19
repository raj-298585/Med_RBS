package com.rbs.mct.controller;

import com.rbs.mct.dao.BaseDAO;
import com.rbs.mct.entity.MCTRequest;
import com.rbs.mct.entity.MCTResponse;

public class MCTController {

	public MCTResponse requestUpdate(MCTRequest mctRequest) throws Exception {
		BaseDAO baseDAO = MCTEntityType.valueOf(mctRequest.getEntityType())
				.createDAO();
		MCTResponse response;
		response = baseDAO.update(mctRequest);
		return response;
	}

	/**
	 * This method will search the DB based on the provided criteria and return
	 * an ArrayList of records which are fetched.
	 * 
	 * @param MCTRequest
	 * @return ArrayList of entity object
	 * @throws Exception
	 */
	public MCTResponse requestSearch(MCTRequest mctRequest) throws Exception {
		BaseDAO baseDAO = MCTEntityType.valueOf(mctRequest.getEntityType())
				.createDAO();
		MCTResponse response = baseDAO.search(mctRequest);
		return response;
	}

	/**
	 * This method will insert the rows into the database.
	 * 
	 * @param MCTRequest
	 * @return the inserted row information
	 * @throws Exception
	 */
	public MCTResponse requestInsert(MCTRequest mctRequest) throws Exception {
		BaseDAO baseDAO = MCTEntityType.valueOf(mctRequest.getEntityType())
				.createDAO();
		MCTResponse response;
		response = baseDAO.insert(mctRequest);
		return response;
	}

	/**
	 * This method will validate the user login information
	 * 
	 * @param MCTRequest
	 * @return response after validation
	 * @throws Exception
	 */
	public MCTResponse validateUserCredentials(MCTRequest mctRequest)
			throws Exception {
		BaseDAO baseDAO = MCTEntityType.valueOf(mctRequest.getEntityType())
				.createDAO();
		MCTResponse response = new MCTResponse();
		response = baseDAO.validateUserCredentials(mctRequest
				.getEntitySearchMap());
		return response;
	}

	/**
	 * @param MCTRequest
	 *            Delete an Entity from DB
	 */

	public MCTResponse requestDelete(MCTRequest mctRequest) throws Exception {
		BaseDAO baseDAO = MCTEntityType.valueOf(mctRequest.getEntityType())
				.createDAO();
		MCTResponse response;
		response = baseDAO.delete(mctRequest);
		return response;
	}
}
