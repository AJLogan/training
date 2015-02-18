package com.awesome.dataAccess;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TradeQueries {
	
	private String top20="select id, ticker, volume, price, dealer from EquityTrading.trades limit 20";
	
	public ResultSet getTop20() throws SQLException{
		ResultSet rs = DatabaseUtils.executeQuery(DatabaseUtils.setupDB(), top20);
		return rs;
	}

}
