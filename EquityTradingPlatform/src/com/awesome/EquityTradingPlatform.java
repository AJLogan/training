/**
 * 
 */
package com.awesome;

import java.util.Map;

import com.awesome.dataAccess.GetYahooMarketData;

/**
 * @author andrew
 *
 */
public class EquityTradingPlatform implements Runnable {

	public String[] symbols;
	private GetYahooMarketData handler;
	private Map<String, MarketDataHandler> handlerMap;

	public EquityTradingPlatform() {
		this.handler = new GetYahooMarketData();
	}

	void addSymbol(String symbol) {
		// symbols.add(symbol);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Map<String, GetYahooMarketData.QuoteData> data = handler
						.getQuote(symbols);

				for (Map.Entry<String, GetYahooMarketData.QuoteData> value : data
						.entrySet()) {
					String symbol = value.getKey();
					GetYahooMarketData.QuoteData quote = value.getValue();

					if (handlerMap.containsKey(symbol)) {
						MarketDataHandler strategy = handlerMap.get(symbol);
						
						strategy.onMarketDataUpdate(symbol, quote.bidPrice,
								quote.bidSize, quote.askPrice, quote.askSize);
					}

				}

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

	public static void main(String args[]) {
		EquityTradingPlatform etp = new EquityTradingPlatform();
	}
}