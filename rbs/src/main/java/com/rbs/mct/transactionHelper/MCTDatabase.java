package com.rbs.mct.transactionHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MCTDatabase {
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/rbsprod", "root",
					"raj24");
		} catch (SQLException exception) {
			exception.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
