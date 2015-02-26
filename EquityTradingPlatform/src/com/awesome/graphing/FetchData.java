package com.awesome.graphing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import com.awesome.dataAccess.DatabaseUtils;
import com.awesome.model.Quote;

public class FetchData {
	public static ArrayList<Quote> getGraphData(Vector<String> stocks,
			int offset) {
		ArrayList<Quote> dataList = new ArrayList<Quote>();
		ResultSet rs = null;
		StringBuilder sb = new StringBuilder();
		String prefix = "";
		if (stocks.size() > 1) {
			int i = 0;
			for (i = 0; i < stocks.size(); i++) {
				sb.append(prefix);
				prefix = ",";
				sb.append("'" + stocks.get(i) + "'");
			}
		} else {
			for (int i = 0; i < stocks.size(); i++) {
				sb.append("'" + stocks.get(i) + "'");
			}
		}
		int size = stocks.size();
		String query = "SELECT askPrice, askSize, bidPrice, bidSize, volume from quotes  where ticker in ("
				+ sb + ") limit " + size + " offset " + offset + ";";
		Connection cn = DatabaseUtils.setupDB();
		if (!stocks.isEmpty()) {
			try {
				rs = DatabaseUtils.executeQuery(cn, query);
				while (rs.next()) {
					Quote data = new Quote();
					data.setAskPrice(rs.getFloat("askPrice"));
					data.setAskSize(rs.getInt("askSize"));
					data.setBidPrice(rs.getFloat("bidPrice"));
					data.setBidSize(rs.getInt("bidSize"));
					data.setVolume(rs.getInt("volume"));
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
		}
		return dataList;
	}
}