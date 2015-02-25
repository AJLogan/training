/**
 * 
 */
package com.awesome.feeds;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.awesome.dataAccess.GetData;
import com.awesome.model.Quote;

/**
 * @author andrew
 *
 */
public class MarketDataConsumer implements Runnable {
	public Vector<String> symbols = new Vector<String>();
	private GetData handler;
	public Map<String, MarketDataHandler> handlerMap = new HashMap<String, MarketDataHandler>();

	public MarketDataConsumer() {
		this.handler = new GetData();
	}

	public void addSymbol(String symbol) {
		if (symbols.contains(symbol) != true) {
			symbols.add(symbol);
		} else
			return;
	}

	public void removeSymbol(String symbol) {
		if (symbols.size() != 0) {
			symbols.remove(symbols.indexOf(symbol));
		} else
			return;
	}

	public void addHandler(String symbol, MarketDataHandler strategy) {
		// Check Symbol Is currently subscribed to
		if (handlerMap.containsKey(symbol) != true) {
			handlerMap.put(symbol, strategy);
		} else
			return;
	}

	public void removeHandler(String symbol, MarketDataHandler strategy) {
		// Check Symbol Is currently subscribed to
		if (handlerMap.containsKey(symbol) != true) {
			handlerMap.remove(symbol, strategy);
		} else
			return;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Map<String, Quote> data = handler.getQuote(symbols);
				for (Map.Entry<String, Quote> value : data.entrySet()) {
					Quote quote = value.getValue();
					// THIS DOESNT CONTAIN ANYTHING YET!!!
					if (handlerMap.containsKey(value.getKey())) {
						MarketDataHandler strategy = handlerMap.get(symbols);
						strategy = handlerMap.get(value.getKey());
						System.out.println(strategy);
						strategy.onMarketDataUpdate(symbols,
								quote.getBidPrice(), quote.getBidSize(),
								quote.getAskPrice(), quote.getAskSize());
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
}