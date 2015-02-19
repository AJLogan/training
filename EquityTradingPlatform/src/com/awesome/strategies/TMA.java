/**
 * 
 */
package com.awesome.strategies;

import java.util.Map;
import java.util.Vector;

import com.awesome.dataAccess.GetData;
import com.awesome.dataAccess.GetData.Quote;
import com.awesome.feeds.MarketDataHandler;

/**
 * @author andrew
 *
 */
public class TMA implements MarketDataHandler, Runnable {

	@Override
	public void onMarketDataUpdate(Vector<String> symbols, float bidPrice,
			int bidSize, float askPrice, int askSize) {
		System.out.print(symbols + " " + bidPrice + " " + bidSize + " "
				+ askPrice + " " + askSize);
		// TODO Auto-generated method stub
		// true or false should be returned
		twoPointMovingAvg();
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

	public GetData yahooDat;
	public Vector<String> stocks;

	public boolean twoPointMovingAvg() {

		return true;
	}

	public void longAverage() {

		try {
			// yahooDat.getQuote(stocks);
			while (true) {
				// Get Quotes
				Map<String, Quote> data = yahooDat.getQuote(stocks);
				for (Map.Entry<String, GetData.Quote> entry : data.entrySet()) {
					GetData.Quote quote = entry.getValue();
					System.out.printf("%s [%d x %.2f] x [%.2f x %d]\n",
							entry.getKey(), quote.bidSize, quote.bidPrice,
							quote.askPrice, quote.askSize);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void shortAverage() {

	}

}
