package com.awesome.dataAccess;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuotesQueries {

	private String query;

	public ResultSet getTop20() throws SQLException {
		query = "select id, ticker, askSize, askPrice, bidPrice, bidSize, closePrice, volume from EquityTrading.quotes limit 20";
		ResultSet rs = DatabaseUtils.executeQuery(DatabaseUtils.setupDB(),
				query);
		return rs;
	}// getTop20

	public ResultSet getByTicker(String inTicker) throws SQLException {
		query = "select id, ticker, askSize, askPrice, bidPrice, bidSize, closePrice, volume from EquityTrading.quotes where ticker = '"
				+ inTicker + "'";
		ResultSet rs = DatabaseUtils.executeQuery(DatabaseUtils.setupDB(),
				query);
		return rs;
	}// getTicker

	public ResultSet getAskPrice() throws SQLException {
		query = "select ticker, askPrice from EquityTrading.quotes";
		ResultSet rs = DatabaseUtils.executeQuery(DatabaseUtils.setupDB(), query);
		return rs;
	}// getTicker
	
	public ResultSet quoteChartQuery() throws SQLException{
		query = "select ticker as label, askPrice as price from EquityTrading.quotes";
		ResultSet rs = DatabaseUtils.executeQuery(DatabaseUtils.setupDB(), query);
		return rs;
	}
}
