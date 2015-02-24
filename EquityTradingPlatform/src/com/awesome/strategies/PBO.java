package com.awesome.strategies;

import java.util.Vector;

import com.awesome.feeds.MarketDataHandler;

public class PBO implements MarketDataHandler, Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMarketDataUpdate(Vector<String> symbols, float bidPrice,
			int bidSize, float askPrice, int askSize) {
		// TODO Auto-generated method stub
		System.out.println("Price Breakout Strategy");
	}

}
