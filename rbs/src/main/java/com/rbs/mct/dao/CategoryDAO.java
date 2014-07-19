package com.rbs.mct.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rbs.mct.entity.BaseEntity;
import com.rbs.mct.entity.Category;
import com.rbs.mct.entity.MCTRequest;
import com.rbs.mct.entity.MCTResponse;
import com.rbs.mct.transactionHelper.MCTDatabase;

public class CategoryDAO extends BaseDAO {
	public MCTResponse search(MCTRequest request) {

		MCTResponse response = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<BaseEntity> codes = null;
		Category code = null;
		try {
			response = new MCTResponse();
			Connection con = MCTDatabase.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM mct_category");
			codes = new ArrayList<BaseEntity>();
			while (rs.next()) {
				code = new Category(rs.getInt("category_id"),
						rs.getString("category_name"));
				codes.add(code);
			}
			response.setBaseEntityList(codes);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return response;
	}
}
