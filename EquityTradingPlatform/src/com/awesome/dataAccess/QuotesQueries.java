package com.awesome.dataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;

import com.awesome.jsonparser.ParseToJson;

public class QuotesQueries {

	private String query;

	public ResultSet getTop20(Connection cn) throws SQLException {
		query = "select id, ticker, askSize, askPrice, bidPrice, bidSize, closePrice, volume from EquityTrading.quotes limit 20";
		ResultSet rs = DatabaseUtils.executeQuery(cn,
				query);
		return rs;
	}// getTop20

	public ResultSet getByTicker(Connection cn, String inTicker)
			throws SQLException {
		query = "select id, ticker, askSize, askPrice, bidPrice, bidSize, closePrice, volume from EquityTrading.quotes where ticker = '"
				+ inTicker + "'";
		ResultSet rs = DatabaseUtils.executeQuery(cn, query);
		return rs;
	}// getTicker

	public ResultSet getAskPrice(Connection cn) throws SQLException {
		query = "select ticker, askPrice from EquityTrading.quotes limit 100";
		ResultSet rs = DatabaseUtils.executeQuery(cn, query);
		return rs;
	}// getTicker

	public ResultSet quoteChartQuery(Connection cn) throws SQLException {
		query = "select ticker as label, askPrice as price from EquityTrading.quotes";
		ResultSet rs = DatabaseUtils.executeQuery(cn, query);
		return rs;
	}
	
	public JSONArray getAsk() throws JSONException, SQLException {
		JSONArray jArray = new JSONArray();
		Connection quotePanelCN = DatabaseUtils.setupDB();
		try {
			QuotesQueries qq = new QuotesQueries();
			ResultSet rs2 = qq.getAskPrice(quotePanelCN);
			while (rs2.next()) {
				ParseToJson qtj = new ParseToJson();
				//jarray.add(qtj.convert(rs2));
				rs2.getFloat("askPrice");
				//out.println(qtj.convert(rs2).toString());
				jArray.put(rs2.getFloat("askPrice"));
			}// while
				return jArray;
		} catch (SQLException e) {
			throw e;
		}// catch
		finally {
			if (quotePanelCN != null) {
				quotePanelCN.close();
			}
		}
	}
}
