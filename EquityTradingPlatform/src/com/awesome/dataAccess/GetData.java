package com.awesome.dataAccess;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import com.awesome.model.Quote;

// Get quote data for a list of symbols and returns it in a Map

public class GetData {

	public Map<String, Quote> getQuote(Vector<String> stocks) throws Exception {
		// Build the URL
		StringBuilder url = new StringBuilder(
				"http://download.finance.yahoo.com/d/quotes.csv?s=");
		for (String s : stocks.toArray(new String[stocks.size()]))
			url.append(s + ",");
		url.deleteCharAt(url.length() - 1);
		// Properties is for bid and ask
		url.append("&f=a0a5b0b6p0v0&e=.csv");

		// Get the csv lines from Yahoo
		List<String> csv = getCsv(url.toString());

		// Build response
		Map<String, Quote> quotes = new HashMap<String, Quote>();
		int i = 0;
		for (String line : csv) {
			// Parse csv lines
			String[] fields = line.split(",", -1);

			// Should be 4 values if not discard line
			// I think there is an error with this if
			if (fields.length == 6 || fields.length == 8) {
				Quote quote = new Quote();
				try {
					if (fields.length == 6) {

						quote.setAskPrice(Float.parseFloat(fields[0]));
						quote.setAskSize(Integer.parseInt(fields[1]));
						quote.setBidPrice(Float.parseFloat(fields[2]));
						quote.setBidSize(Integer.parseInt(fields[3]));
						quote.setClosePrice(Float.parseFloat(fields[4]));
						quote.setVolume(Integer.parseInt(fields[5]));

					} else {
						quote.setAskPrice(Float.parseFloat(fields[0]));
						quote.setBidPrice(Float.parseFloat(fields[3]));
						quote.setClosePrice(Float.parseFloat(fields[6]));
						quote.setVolume(Integer.parseInt(fields[7]));
						quote.setAskSize((1000 * Integer.parseInt(fields[1]) + Integer.parseInt(fields[2])));
						quote.setBidSize((1000 * Integer.parseInt(fields[4]) + Integer.parseInt(fields[5])));

					}
					// Insert quote with stock as key
					quotes.put(stocks.toArray(new String[stocks.size()])[i],
							quote);
				} catch (NumberFormatException e) {
				}

			}
			i++;
		}
		// check against previous quote
		// write to db
		for (Map.Entry<String, Quote> quote : quotes.entrySet()) {
			writeQuoteToDB(quote);
		}
		return quotes;
	}

	// Returns list of csv from Yahoo
	private List<String> getCsv(String url) throws Exception {
		List<String> response = new ArrayList<String>();
		URL obj = new URL(url);

		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		// This is a GET request
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		// If the response code is not OK then return empty line
		if (responseCode != 200)
			return response;

		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null)
			response.add(inputLine);

		in.close();
		return response;
	}

	private void writeQuoteToDB(Entry<String, Quote> quote) {
		Connection cn = DatabaseUtils.setupDB();
		try {
			String query = "insert into quotes(ticker, askPrice, askSize, bidPrice, bidSize, closePrice, volume) values ('"
					+ quote.getKey()
					+ "',"
					+ quote.getValue().getAskPrice()
					+ ","
					+ quote.getValue().getAskSize()
					+ ","
					+ quote.getValue().getBidPrice()
					+ ","
					+ quote.getValue().getBidSize()
					+ ","
					+ quote.getValue().getClosePrice()
					+ ","
					+ quote.getValue().getVolume() + ")";
			DatabaseUtils.executeUpdate(cn, query);

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
