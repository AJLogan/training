package com.awesome.dataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class TradeQueries {
	private String query;

	public ResultSet getTop20(Connection cn) throws SQLException {
		query = "select id, ticker, volume, price, dealer from EquityTrading.trades limit 20";
		ResultSet rs = DatabaseUtils.executeQuery(cn, query);
		return rs;
	}

	public ResultSet getByTicker(Connection cn, String inTicker)
			throws SQLException {
		query = "select id, ticker, volume, price, dealer from EquityTrading.trades where ticker = '"
				+ inTicker + "'";
		ResultSet rs = DatabaseUtils.executeQuery(cn, query);
		return rs;
	}

	public ResultSet getStocksBeingWatched(Connection cn, Vector<String> stocks)
			throws SQLException {
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
		query = "select id, ticker, volume, price, dealer from EquityTrading.trades where ticker in ("
				+ sb + ") group by ticker;";
		System.out.println(query);
		ResultSet rs = DatabaseUtils.executeQuery(cn, query);
		return rs;
	}

	public ResultSet getAveragePrice(Connection cn, String inTicker)
			throws SQLException {
		query = "select ticker, (sum(price)/count(ticker)) as `Average` from trades where ticker = '"
				+ inTicker + "'";
		ResultSet rs = DatabaseUtils.executeQuery(cn, query);
		return rs;
	}

	public ResultSet getAveragePerUnit(Connection cn, String inTicker)
			throws SQLException {
		query = "select ticker, (sum(price)/sum(volume)) as `Average` from trades where ticker = '"
				+ inTicker + "'";
		ResultSet rs = DatabaseUtils.executeQuery(cn, query);
		return rs;
	}

}
