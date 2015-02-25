package com.awesome.graphing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.awesome.dataAccess.DatabaseUtils;
import com.awesome.model.Quote;

public class FetchData {
	public static ArrayList<Quote> getGraphData() {
		ArrayList<Quote> dataList = new ArrayList<Quote>();
		ResultSet rs = null;
		String query = "SELECT askPrice from quotes limit 300;";
		Connection cn = DatabaseUtils.setupDB();

		try {
			rs = DatabaseUtils.executeQuery(cn, query);
			while (rs.next()) {
				Quote data = new Quote();
				data.setAskPrice(rs.getFloat("askPrice"));
				dataList.add(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataList;
	}
}