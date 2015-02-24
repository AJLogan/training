package com.awesome.strategies;

import java.util.Vector;

import com.awesome.feeds.MarketDataHandler;

public class PBO implements MarketDataHandler, Runnable {

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

	@Override
	public void onMarketDataUpdate(Vector<String> symbols, float bidPrice,
			int bidSize, float askPrice, int askSize) {
		// TODO Auto-generated method stub
		Vector<String> quotes = symbols;
		System.out.println("Price Breakout Strategy");
		System.out.println(quotes.toString());
	}

}
