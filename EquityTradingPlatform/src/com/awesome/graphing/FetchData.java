package com.awesome.graphing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.awesome.dataAccess.DatabaseUtils;
import com.awesome.model.Quote;

public class FetchData {
	public static ArrayList<Quote> getGraphData(int offset) {
		ArrayList<Quote> dataList = new ArrayList<Quote>();
		ResultSet rs = null;
		String query = "SELECT askPrice from quotes  where ticker = 'BARC.L' limit 1 offset " + offset + ";";
		Connection cn = DatabaseUtils.setupDB();
		try {
			rs = DatabaseUtils.executeQuery(cn, query);
			while (rs.next()) {
				Quote data = new Quote();
				data.setAskPrice(rs.getFloat("askPrice"));
				dataList.add(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (cn != null)
				try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return dataList;
	}
}