package com.awesome.dataAccess;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// Get quote data for a list of symbols and returns it in a Map

public class GetData {
	// Quote Data class
	public class Quote {
		public float askPrice;
		public float bidPrice;
		public int askSize;
		public int bidSize;

		public float closePrice;
		public int volume;
	}

	public Map<String, Quote> getQuote(String[] stocks) throws Exception {
		// Build the URL
		StringBuilder url = new StringBuilder(
				"http://download.finance.yahoo.com/d/quotes.csv?s=");

		for (String s : stocks)
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

						quote.askPrice = Float.parseFloat(fields[0]);
						quote.askSize = Integer.parseInt(fields[1]);
						quote.bidPrice = Float.parseFloat(fields[2]);
						quote.bidSize = Integer.parseInt(fields[3]);
						quote.closePrice = Float.parseFloat(fields[4]);
						quote.volume = Integer.parseInt(fields[5]);

					} else {
						quote.askPrice = Float.parseFloat(fields[0]);
						quote.bidPrice = Float.parseFloat(fields[3]);
						quote.closePrice = Float.parseFloat(fields[6]);
						quote.volume = Integer.parseInt(fields[7]);

						quote.askSize = (1000 * Integer.parseInt(fields[1]) + Integer
								.parseInt(fields[2]));
						quote.bidSize = (1000 * Integer.parseInt(fields[4]) + Integer
								.parseInt(fields[5]));

					}
					// Insert quote with stock as key
					quotes.put(stocks[i], quote);
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
		try {
			String query = "insert into quotes(ticker, askPrice, askSize, bidPrice, bidSize, closePrice, volume) values ('"
					+ quote.getKey()
					+ "',"
					+ quote.getValue().askPrice
					+ ","
					+ quote.getValue().askSize
					+ ","
					+ quote.getValue().bidPrice
					+ ","
					+ quote.getValue().bidSize
					+ ","
					+ quote.getValue().closePrice
					+ ","
					+ quote.getValue().volume + ")";
			DatabaseUtils.executeUpdate(DatabaseUtils.setupDB(), query);

		} catch (SQLException e) {
			System.out.println(e.getMessage());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
