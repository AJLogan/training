package com.awesome.feeds;

public interface MarketDataHandler {
	void onMarketDataUpdate(String symbol, float bidPrice, int bidSize, float askPrice, int askSize);
}
