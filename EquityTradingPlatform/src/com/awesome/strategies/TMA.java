/**
 * 
 */
package com.awesome.strategies;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Vector;

import com.awesome.dataAccess.DatabaseUtils;
import com.awesome.dataAccess.GetData;
import com.awesome.dataAccess.GetData.Quote;
import com.awesome.feeds.MarketDataHandler;

/**
 * @author james
 *
 */
public class TMA implements MarketDataHandler, Runnable {
	static float sAvg = 0;
	static float lAvg = 0;
	static boolean run = true;
	static int count = 0;
	static int sellCount = 0;
	static int buyCount = 0;
	private static Queue<Float> squoteList = new LinkedList<Float>();
	private static Queue<Float> lsquoteList = new LinkedList<Float>();

	@Override
	public void onMarketDataUpdate(Vector<String> symbols, float bidPrice,
			int bidSize, float askPrice, int askSize) {
		GetData yahooReader = new GetData();
		Vector<String> quotes = new Vector<String>();
		quotes.add("TESO");
		// TESO TCO.SG
		// TATN:RM
		// FB
		// YHOO
		// IBMD
		while (run) {
			// Get Quotes
			Map<String, Quote> data;
			try {
				data = yahooReader.getQuote(quotes);

				// This structure is good - keep it!
				for (Map.Entry<String, Quote> entry : data.entrySet()) {
					Quote quote = entry.getValue();
					System.out.printf("%s [%d x %.2f] x [%.2f x %d]\n",
							entry.getKey(), quote.bidSize, quote.bidPrice,
							quote.askPrice, quote.askSize);

					Thread shortThread = new Thread() {
						public void run() {
							sAvg = calcMA(quote.askPrice, 5, squoteList);
						}
					};

					Thread longThread = new Thread() {
						public void run() {
							lAvg = calcMA(quote.askPrice, 20, lsquoteList);
						}
					};

					shortThread.start();
					longThread.start();
					String dealer = "james";

					try {
						trigger(sAvg, lAvg, entry, quote, dealer);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					count++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void run() {
		while (true) {
			try {

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Calculates the moving averages This will be used as the input for the
	 * graph
	 * 
	 * @param askPrice
	 * @param spread
	 * @param quoteList
	 */
	public synchronized static float calcMA(Float askPrice, int spread,
			Queue<Float> quoteList) {
		float sum = 0;
		float sMA = 0;

		try {
			// The thread will define the spread of the SMA
			Thread.sleep(500);
			// define the spread here
			if (quoteList.size() <= spread) {
				quoteList.add(askPrice);
				// System.out.println("Space");
			} else {
				// System.out.println("Full");
				for (float data : quoteList) {
					sum += data;
				}
				sMA = sum / quoteList.size();
				if (spread < 6) {
					// System.out.println("SMA = " + sMA);
					quoteList.poll();

				} else {
					// System.out.println("LMA = " + sMA);
					quoteList.poll();
				}
			}
		} catch (InterruptedException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return sMA;
	}

	private static double buyTotal = 0;
	private static double sellTotal = 0;

	/**
	 * Trigger executes the trade to the stock broker
	 * 
	 * @param sAvg
	 * @param lAvg
	 * @param entry
	 * @param quote
	 * @param dealer
	 * @throws SQLException
	 */
	public static void trigger(float sAvg, float lAvg,
			Map.Entry<String, Quote> entry, Quote quote, String dealer)
			throws SQLException {

		double buyPL = 0;
		double sellPl = 0;
		int buy = -1;
		int sell = 1;
		if (sAvg >= lAvg) {
			System.out.println("Time to buy");
			buyPL = queryDB(entry, quote, dealer, buy, quote.bidPrice,
					quote.bidSize);
			buyTotal += buyPL;
			buyCount++;
		} else {
			System.out.println("Time to sell");
			sellPl = queryDB(entry, quote, dealer, sell, quote.askPrice,
					quote.askSize);
			sellTotal += sellPl;
			++sellCount;
		}
		double pLTotal = (buyTotal + sellTotal);

		System.out.printf("Position 	: %.2f\n", pLTotal);
		System.out.printf("Buy Total 	: %.2f\n", buyPL);
		System.out.printf("Sell Total 	: %.2f\n", sellPl);
		System.out.println("_____________________________________");

		// Problems start here
		exitCondition(buyTotal, sellTotal, pLTotal, quote);
	}

	/**
	 * Needs to continuously check the value difference between the askPrice and
	 * the current askPrice
	 * 
	 * @param buyTotalTESO
	 * @param sellTotal
	 * @param pLTotal
	 */
	public static void exitCondition(double buyTotal, double sellTotal,
			double pLTotal, Quote quote) {

		// Gain = (SP) - (CP)
		double equity = 0;
		double boughtAt = (buyTotal);
		double soldAt = (sellTotal);
		double gain = boughtAt - soldAt;
		double dif = (gain / (boughtAt + soldAt) / 2);
		// double dif2 = (gain*100/boughtAt)/100;
		System.out.printf("Percent : %.2f\n", dif);
		// System.out.printf("Percent : %.2f\n",dif2);

		if (dif > 1) {
			System.out.println("Profit of 1%");
			run = false;
			System.out.println("Start Sell Back");
			// worth of how much we sold
			equity = (pLTotal * quote.bidPrice) - (pLTotal * quote.askPrice);
			System.out.printf("Profit : %.2f\n", equity);
			System.out.println("Total Trades : " + count);
			System.out.println("Total Buys : " + buyCount);
			System.out.println("Total Sells : " + sellCount);

		} else if (dif < -1) {
			System.out.println("Loss of -1%");
			run = false;
			System.out.println("Start Buy Back");
			// worth of how much we sold
			equity = (pLTotal * quote.askPrice) - (pLTotal * quote.bidPrice);
			System.out.printf("Profit : %.2f\n", equity);
			System.out.println("Total Trades : " + count);
			System.out.println("Total Buys : " + buyCount);
			System.out.println("Total Sells : " + sellCount);
		} else {
			run = true;
		}
	}

	private static float queryDB(Map.Entry<String, Quote> entry, Quote quote,
			String dealer, int type, float price, float size)
			throws SQLException { // }
		Connection cn = DatabaseUtils.setupDB();
		Statement st = null;
		ResultSet rs = null;
		try {
			// Join product and eproduct_cateogry where category = parameter
			// passed in
			// issue with quote.volume
			String query = "insert into trades(ticker, volume, price, dealer, profit_loss) values ('"
					+ entry.getKey()
					+ "','"
					+ size
					+ "','"
					+ quote.askPrice
					+ "','" + dealer + "','" + price * size * type + "')";
			DatabaseUtils.executeUpdate(cn, query);
			System.out.println("Trade made!");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (cn != null)
				cn.close();
		}
		// issue with quote.volume
		return price * size * type;
	}
}
