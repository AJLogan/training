/**
 * 
 */
package com.awesome.strategies;

import com.awesome.feeds.MarketDataHandler;

/**
 * @author andrew
 *
 */
public class TMA implements MarketDataHandler, Runnable {

	@Override
	public void onMarketDataUpdate(String symbol, float bidPrice, int bidSize,
			float askPrice, int askSize) {
		System.out.print(symbol + " " + bidPrice + " " + bidSize + " "
				+ askPrice + " " + askSize);
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		System.out.println("TMA inside Run");
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

}
