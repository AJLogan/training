/**
 * 
 */
package com.awesome.feeds;

import java.util.Map;

import com.awesome.dataAccess.GetData;
import com.awesome.strategies.TMA;

/**
 * @author andrew
 *
 */
public class MarketDataConsumer implements Runnable {

	public String[] symbols = { "AAPL" };
	private GetData handler;
	private Map<String, MarketDataHandler> handlerMap;

	TMA twopoint = new TMA();

	public MarketDataConsumer() {
		this.handler = new GetData();
	}

	public void addSymbol(String symbol) {
		// symbols.add(symbol)
	}

	public void addHandler(String symbol, MarketDataHandler strategy) {
		// Check Symbol Is currently subscribed to
		// do with a list of handlers
		handlerMap.put(symbol, strategy);
	}

	@Override
	public void run() {

		// handlerMap.put("YHOO", twopoint);

		System.out.println("Running");
		while (true) {
			try {
				Map<String, GetData.Quote> data = handler.getQuote(symbols);
				for (Map.Entry<String, GetData.Quote> value : data.entrySet()) {
					GetData.Quote quote = value.getValue();

					// THIS DOESNT CONTAIN ANYTHING YET!!!
					// if (handlerMap.containsKey(symbol)) {
					// MarketDataHandler strategy = handlerMap.get(symbol);
					//
					// strategy.onMarketDataUpdate(symbol, quote.bidPrice,
					// quote.bidSize, quote.askPrice, quote.askSize);
					// }

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

}