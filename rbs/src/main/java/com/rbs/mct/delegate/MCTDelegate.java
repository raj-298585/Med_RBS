package com.rbs.mct.delegate;

import com.rbs.mct.controller.MCTController;
import com.rbs.mct.entity.MCTRequest;
import com.rbs.mct.entity.MCTResponse;

public class MCTDelegate {

	public MCTResponse requestUpdate(MCTRequest LUTRequest) throws Exception {
		MCTController mctController = new MCTController();
		MCTResponse mctResponse;
		mctResponse = mctController.requestUpdate(LUTRequest);
		return mctResponse;
	}

	public MCTResponse requestSearch(MCTRequest LUTRequest) throws Exception {
		MCTController mctController = new MCTController();
		MCTResponse mctResponse;
		mctResponse = mctController.requestSearch(LUTRequest);
		return mctResponse;
	}

	public MCTResponse requestInsert(MCTRequest LUTRequest) throws Exception {
		MCTController mctController = new MCTController();
		MCTResponse mcTResponse;
		mcTResponse = mctController.requestInsert(LUTRequest);
		return mcTResponse;
	}

	public MCTResponse requestDelete(MCTRequest LUTRequest) throws Exception {
		MCTController mctController = new MCTController();
		MCTResponse mctResponse;
		mctResponse = mctController.requestDelete(LUTRequest);
		return mctResponse;
	}

	public MCTResponse validateUserCredentials(MCTRequest pdtRequest)
			throws Exception {
		MCTController mctController = new MCTController();
		MCTResponse mctResponse = new MCTResponse();
		mctResponse = mctController.validateUserCredentials(pdtRequest);
		return mctResponse;
	}

}
