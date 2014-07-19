package com.rbs.mct.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.rbs.mct.entity.BaseEntity;
import com.rbs.mct.entity.ICD10Codes;
import com.rbs.mct.entity.MCTRequest;
import com.rbs.mct.entity.MCTResponse;
import com.rbs.mct.entity.UserDetails;
import com.rbs.mct.transactionHelper.MCTDatabase;

public class UserDetailsDAO extends BaseDAO {
	public MCTResponse search(MCTRequest request) throws Exception {

		MCTResponse response = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<BaseEntity> details = null;
		UserDetails userdetail = null;
		response = new MCTResponse();
		Connection con = MCTDatabase.getConnection();
		stmt = con.createStatement();
		rs = stmt.executeQuery("SELECT * FROM User_Details");
		details = new ArrayList<BaseEntity>();
		while (rs.next()) {
			userdetail = new UserDetails(rs.getString("FirstName"),
					rs.getString("LastName"), rs.getString("email"),
					rs.getString("Phone"), rs.getString("HospitalName"),
					rs.getString("User_Id"), rs.getString("Password"),
					rs.getDate("DateOfCreation"), rs.getString("Role"));
			details.add(userdetail);
		}
		response.setBaseEntityList(details);

		return response;
	}

	@Override
	public MCTResponse insert(MCTRequest request) throws Exception {
		System.out.println("In the insert");
		MCTResponse response = null;
		java.sql.PreparedStatement stmtUserDetailTable = null;
		java.sql.PreparedStatement stmtUserLoginTable = null;
		String queryLoginTable = "INSERT INTO User(User_Id,Password) VALUES (?,?)";
		String queryUserDetail = "INSERT INTO User_Details(DateOfCreation,Password,User_Id,email,Role,Phone,HospitalName,FirstName,LastName) VALUES (?,?,?,?,?,?,?,?,?)";
		UserDetails userDetails = (UserDetails) request.getBaseEntity();

		response = new MCTResponse();
		Connection connection = MCTDatabase.getConnection();
		stmtUserLoginTable = connection.prepareStatement(queryLoginTable);
		stmtUserLoginTable.setString(1, userDetails.getUserName());
		stmtUserLoginTable.setString(2, userDetails.getPassword());
		stmtUserLoginTable.executeUpdate();
		stmtUserDetailTable = connection.prepareStatement(queryUserDetail);
		stmtUserDetailTable.setDate(1, new java.sql.Date(userDetails
				.getDateOfCreation().getTime()));
		stmtUserDetailTable.setString(2, userDetails.getPassword());
		stmtUserDetailTable.setString(3, userDetails.getUserName());
		stmtUserDetailTable.setString(4, userDetails.getEmailID());
		stmtUserDetailTable.setString(5, userDetails.getRole());
		stmtUserDetailTable.setString(6, userDetails.getContactNumber());
		stmtUserDetailTable.setString(7, userDetails.getContactNumber());
		stmtUserDetailTable.setString(8, userDetails.getFirstName());
		stmtUserDetailTable.setString(9, userDetails.getLastName());
		stmtUserDetailTable.executeUpdate();
		return response;
	}
}
