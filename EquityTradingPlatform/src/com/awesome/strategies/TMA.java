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
import com.awesome.feeds.MarketDataHandler;
import com.awesome.model.Quote;
import com.awesome.orderManager.OrderManager;

/**
 * @author james
 *
 */
public class TMA implements MarketDataHandler, Runnable {
	/*
	 * Required fields
	 */
	private float shortMA;
	private float longMA;
	private boolean run = true;
	private boolean pullTrigger = false;
	private int count = 0;
	private int sellCount = 0;
	private float shortAvg = 0;
	private int buyCount = 0;
	private  Queue<Float> squoteList = new LinkedList<Float>();
	private  Queue<Float> lsquoteList = new LinkedList<Float>();

	/**
	 * Acts as the main method for the class so to speak
	 */
	@Override
	public void onMarketDataUpdate(Vector<String> symbols, float bidPrice,
			int bidSize, float askPrice, int askSize) {

		GetData yahooReader = new GetData();
		Vector<String> quotes = symbols;

		while (run) {
			// Get Quotes
			Map<String, Quote> data;
			try {
				data = yahooReader.getQuote(quotes);

				// This structure is good - keep it!
				for (Map.Entry<String, Quote> entry : data.entrySet()) {
					Quote quote = entry.getValue();
					// output the stok to screen
					System.out.printf("%s [%d x %.2f] x [%.2f x %d]\n",
							entry.getKey(), quote.getBidSize(),
							quote.getBidPrice(), quote.getAskPrice(),
							quote.getAskSize());

					// Calculates the short moving avg
					Thread shortThread = new Thread() {
						public void run() {
							// set the spread for the short
							shortMA = calcSMA(quote, 5, squoteList,
									entry.getKey());
							// OUTPUT HERE
						}
					};

					// Calculates the long moving avg
					Thread longThread = new Thread() {
						public void run() {
							// set the spread of the long
							longMA = calcLMA(quote, 20, lsquoteList,
									entry.getKey());
							// OUTPUT HERE
						}
					};

					// start the threads to the MA
					shortThread.start();
					longThread.start();
					// hardcode the dealer
					String dealer = "james";
					if (shortMA != 0) {
						queryMA(shortMA, longMA);
					}
					// Call the trigger and pass both the LMA and SMA to it

					try {
						if (pullTrigger = true && longMA != 0) {
							trigger(shortMA, longMA, entry, quote, dealer);
						}
					} catch (SQLException ex) {
						System.out.println(ex.getMessage());
					}

				}
			} catch (Exception e) {
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
	public float calcSMA(Quote quote, int spread, Queue<Float> quoteList,
			String symbol) {
		float sum = 0;


		if (quoteList.size() <= spread) {
			quoteList.add(quote.getAskPrice());
			// System.out.println("Space");
		} else {
			// System.out.println("Full");
			/*
			 * If list is full then the sum of the list is calculated
			 */
			for (float prices : quoteList) {
				sum += prices;
			}

			/*
			 * The average is then calculated after the for loop has got the sum
			 */
			shortAvg = sum / quoteList.size();
			quoteList.poll();

		}

		return shortAvg;
	}

	/**
	 * Calculates the moving averages This will be used as the input for the
	 * graph
	 * 
	 * @param askPrice
	 * @param spread
	 * @param quoteList
	 */
	public float calcLMA(Quote quote, int spread, Queue<Float> quoteList,
			String symbol) {
		float sum = 0;
		
		// The thread will define the spread of the SMA
		// Thread.sleep(500);
		// define the spread here
		if (quoteList.size() <= spread) {
			quoteList.add(quote.getAskPrice());
			// System.out.println("Space");
		} else {
			// System.out.println("Full");
			/*
			 * If list is full then the sum of the list is calculated
			 */
			for (float prices : quoteList) {
				sum += prices;
			}

			/*
			 * The average is then calculated after the for loop has got the sum
			 */
			longMA = sum / quoteList.size();
			// if (spread < 6) {
			// System.err.println(symbol + " SMA = " + movingAvg);
			quoteList.poll();

			pullTrigger = true;
		}

		return longMA;
	}

	private void queryMA(double shortMA, double longMA) throws SQLException {

		Connection cn = DatabaseUtils.setupDB();
		Statement st = null;
		ResultSet rs = null;
		try {
			// Join product hnd eproduct_cateogry where category = parameter
			// passed in
			// issue with quote.volume
			String query = "insert into movingAvg(sma, lma) values ('"
					+ shortMA + "','" + longMA + "')";
			DatabaseUtils.executeUpdate(cn, query);
			System.out.println("MA STORED!!");

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
	}

	/*
	 * Required fields
	 */
	private double buyTotal = 0;
	private double sellTotal = 0;

	/**
	 * Trigger executes the trade to the stock broker
	 * 
	 * @param shortMA
	 * @param longMA
	 * @param entry
	 * @param quote
	 * @param dealer
	 * @throws SQLException
	 */
	public void trigger(float shortMA, float longMA,
			Map.Entry<String, Quote> entry, Quote quote, String dealer)
			throws SQLException {

		double buyPriceSize = 0;
		double sellPriceSize = 0;
		int buy = -1;
		int sell = 1;
		double profitLoss = 0;
		if (shortMA >= longMA) {
			System.out.println("Time to buy");
			OrderManager.getInstance().buyOrder(entry.getKey(),
					quote.getBidPrice(), quote.getBidSize());
			buyPriceSize = queryTrades(entry, quote, dealer, buy,
					quote.getBidPrice(), quote.getBidSize());
			buyTotal += buyPriceSize;
			buyCount++;

		} else if( shortMA <= longMA){
			System.out.println("Time to sell");
			OrderManager.getInstance().sellOrder(entry.getKey(),
					quote.getAskPrice(), quote.getBidSize());
			sellPriceSize = queryTrades(entry, quote, dealer, sell,
					quote.getAskPrice(), quote.getAskSize());
			sellTotal += sellPriceSize;
			sellCount++;

		}
		// Calculate Profit/Loss
		profitLoss = sellTotal - buyTotal;

		if(buyTotal != 0 && sellTotal !=0){
		exitCondition(buyTotal, sellTotal, quote, entry, buyPriceSize,
				sellPriceSize);
		}
	}

	/**
	 * Needs to continuously check the value difference between the askPrice and
	 * the current askPrice
	 * 
	 * @param buyTotal
	 * @param sellTotal
	 * @param tradesValue
	 */
	public void exitCondition(double buyTotal, double sellTotal, Quote quote,
			Map.Entry<String, Quote> entry, double buyPriceSize,
			double sellPriceSize) {

		double equity = 0;
		double gain = buyTotal + sellTotal;
		double gainPercentage = (gain) / buyTotal;// =100%
		// (gain * 100) / buyTotal
		count++;
		double tradesValue = (buyTotal + sellTotal);

		System.out.printf("Percent : %.2f\n", gainPercentage);
		System.out.printf("Position 	: %.2f\n", tradesValue);
		System.out.printf("Buy Total 	: %.2f\n", buyPriceSize);
		System.out.printf("Sell Total 	: %.2f\n", sellPriceSize);
		// OUTPUT HERE

		System.out.println("_____________________________________");
		// System.out.printf("Percent : %.2f\n", lossPercentage);

		if (gainPercentage > 10) {
			System.out.println("Profit of 1%");
			System.out.println("Start Sell Back");
			// worth of how much we sold
			equity = sellTotal + buyTotal;
			System.out.printf("Profit : %.2f\n", equity);
			System.out.println(entry.getKey());
			System.out.println("Total Trades : " + count);
			System.out.println("Total Buys : " + buyCount);
			System.out.println("Total Sells : " + sellCount);
			run = false;

		} else if (gainPercentage < -10) {
			System.out.println("Loss of -1%");
			System.out.println("Start Buy Back");
			// worth of how much we sold
			equity = buyTotal + sellTotal;
			System.out.printf("Profit : %.2f\n", equity);
			System.out.println("Total Trades : " + count);
			System.out.println("Total Buys : " + buyCount);
			System.out.println("Total Sells : " + sellCount);
			run = false;
		}
	}

	private float queryTrades(Map.Entry<String, Quote> entry, Quote quote,
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
					+ quote.getAskPrice()
					+ "','"
					+ dealer
					+ "','"
					+ price
					* size * type + "')";
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

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}