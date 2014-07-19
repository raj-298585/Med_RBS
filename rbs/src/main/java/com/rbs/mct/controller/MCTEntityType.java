package com.rbs.mct.controller;

import com.rbs.mct.dao.BaseDAO;
import com.rbs.mct.dao.CategoryDAO;
import com.rbs.mct.dao.HospitalDAO;
import com.rbs.mct.dao.ICDCodeDAO;
import com.rbs.mct.dao.UserDetailsDAO;

public enum MCTEntityType {

	ICDCodes {
		public BaseDAO createDAO() {
			return new ICDCodeDAO();
		}

	},
	UserDetail {
		public BaseDAO createDAO() {
			return new UserDetailsDAO();
		}

	},

	Hospital {
		public BaseDAO createDAO() {
			return new HospitalDAO();
		}

	},
	LUTLob {
		public BaseDAO createDAO() {
			return null;
		}

	},
	ActivityDao {
		public BaseDAO createDAO() {
			return null;
		}
	},

	LUTRole {
		public BaseDAO createDAO() {
			return null;
		}
	},
	Category {
		public BaseDAO createDAO() {
			return new CategoryDAO();
		}
	},
	LUTTask {
		public BaseDAO createDAO() {
			return null;
		}

	},
	ReportLUTTask {
		public BaseDAO createDAO() {
			return null;
		}

	};

	MCTEntityType() {
	}

	MCTEntityType(String entityType) {
	}

	public abstract BaseDAO createDAO();
}
