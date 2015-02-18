package com.awesome.dataAccess;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUtils {

	public static Connection setupDB() {
		String jdbcDriver = "com.mysql.jdbc.Driver";
		String databaseUri = "jdbc:mysql://localhost:8889/EquityTrading?"
				+ "user=root&password=root";

		try {
			Class.forName(jdbcDriver);
		} catch (ClassNotFoundException e) {
			System.out.println("Error loading JDBC driver: " + e);
		}

		// Connect to a database.
		Connection cn = null;
		try {
			cn = DriverManager.getConnection(databaseUri);
		} catch (SQLException e) {
			System.out.println("Error connecting to a database: " + e);
		}
		return cn;
	}

	public static ResultSet executeQuery(Connection cn, String query)
			throws SQLException {
		Statement st = cn.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}

	public static void executeUpdate(Connection cn, String query)
			throws SQLException {
		Statement st = cn.createStatement();
		st.executeUpdate(query);
	};

	public static void executePreparedStatement(Connection cn, String query,
			String[] accounts) throws SQLException {
		PreparedStatement ps = cn.prepareStatement(query);
		for (int i = 0; i < accounts.length; i++) {
			ps.setString(i, accounts.toString());
		}
		ps.executeUpdate(query);
	}

	public static ResultSet executeStoredProc(Connection cn, String query,
			String param) throws SQLException {
		CallableStatement st = cn.prepareCall(query);
		st.setString(1, param);
		ResultSet rs = st.executeQuery();
		return rs;
	}
}