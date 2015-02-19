package com.awesome.dataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TradeQueries {
	private String query;

	public ResultSet getTop20() throws SQLException {
		query = "select id, ticker, volume, price, dealer from EquityTrading.trades limit 20";
		ResultSet rs = DatabaseUtils.executeQuery(DatabaseUtils.setupDB(),
				query);
		return rs;
	}

	public ResultSet getTicker(String inTicker) throws SQLException {
		query = "select id, ticker, volume, price, dealer from EquityTrading.trades where ticker = '"
				+ inTicker + "'";
		ResultSet rs = DatabaseUtils.executeQuery(DatabaseUtils.setupDB(),
				query);
		return rs;
	}

	public ResultSet getAveragePrice(String inTicker) throws SQLException {
		query = "select ticker, (sum(price)/count(ticker)) as `Average` from trades where ticker = '"
				+ inTicker + "'";
		ResultSet rs = DatabaseUtils.executeQuery(DatabaseUtils.setupDB(),
				query);
		return rs;
	}

	public ResultSet getAveragePerUnit(String inTicker) throws SQLException {
		query = "select ticker, (sum(price)/sum(volume)) as `Average` from trades where ticker = '"
				+ inTicker + "'";
		ResultSet rs = DatabaseUtils.executeQuery(DatabaseUtils.setupDB(),
				query);
		return rs;
	}

}
