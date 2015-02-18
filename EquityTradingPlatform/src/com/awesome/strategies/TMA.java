/**
 * 
 */
package com.awesome.strategies;

import com.awesome.feeds.MarketDataHandler;

/**
 * @author andrew
 *
 */
public class TMA implements MarketDataHandler{

	@Override
	public void onMarketDataUpdate(String symbol, float bidPrice, int bidSize,
			float askPrice, int askSize) {
		System.out.print(symbol + " " + bidPrice + " " + bidSize + " " + askPrice + " " + askSize);
		// TODO Auto-generated method stub		
	}

}
