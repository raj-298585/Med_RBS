package com.rbs.mct.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rbs.mct.entity.BaseEntity;
import com.rbs.mct.entity.Hospital;
import com.rbs.mct.entity.ICD10Codes;
import com.rbs.mct.entity.MCTRequest;
import com.rbs.mct.entity.MCTResponse;
import com.rbs.mct.transactionHelper.MCTDatabase;

public class HospitalDAO extends BaseDAO {
	public MCTResponse search(MCTRequest request) {

		MCTResponse response = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<BaseEntity> codes = null;
		ICD10Codes code = null;
		try {
			response = new MCTResponse();
			Connection con = MCTDatabase.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM rbs_cpts");
			codes = new ArrayList<BaseEntity>();
			while (rs.next()) {
				code = new ICD10Codes(rs.getInt("cpt_id"),
						rs.getString("cpt_category"), rs.getString("cpt_cde"),
						rs.getString("cpt_desc"), rs.getDouble("cpt_fee"));
				codes.add(code);
			}
			response.setBaseEntityList(codes);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return response;
	}

	@Override
	public MCTResponse insert(MCTRequest request) throws Exception {

		MCTResponse response = null;
		java.sql.PreparedStatement stmt = null;
		String query = "INSERT INTO Hospitals(ID,NAME,LOCATION) VALUES (?,?,?)";
		Hospital code = (Hospital) request.getBaseEntity();
		Statement stmtCount = null;
		int count = 1;
		response = new MCTResponse();
		Connection connection = MCTDatabase.getConnection();
		stmtCount = connection.createStatement();
		ResultSet rsCount = stmtCount
				.executeQuery("SELECT COUNT(*) FROM Hospitals");
		if (rsCount.next()) {
			count = rsCount.getInt(1);
		}
		count++;
		stmt = connection.prepareStatement(query);
		stmt.setInt(1, count);
		stmt.setString(2, code.getHospitalName());
		stmt.setString(3, code.getHospitalLoc());
		stmt.executeUpdate();
		return response;
	}
}
