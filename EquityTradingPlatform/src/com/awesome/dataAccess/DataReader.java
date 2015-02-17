/**
 * 
 */
package com.awesome.dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author andrew
 *
 */
public class DataReader {
	/*
	 * Database Connection Parameters
	 */
	private static String strConn = "jdbc:mysql://localhost:8889/EquityTrading?user=root&password=root";
	private static String strDriver = "com.mysql.jdbc.Driver";

	// Set up Database connection and return a connection
	private static Connection setDBConnection() {
		// Load JDBC Driver
		try {
			Class.forName(strDriver);
		} catch (ClassNotFoundException e) {
			System.out.println("Error loading JDBC driver: " + e);
		}

		// Connect to a database.
		Connection cn = null;
		try {
			cn = DriverManager.getConnection(strConn);
		} catch (SQLException e) {
			System.out.println("Error connecting to a database: " + e);
		}
		return cn;
	}
	
//	Read market data from database
}
