package com.awesome.feeds;

import java.util.Vector;

public interface MarketDataHandler {
	void onMarketDataUpdate(Vector<String> symbols, float bidPrice,
			int bidSize, float askPrice, int askSize);
}
