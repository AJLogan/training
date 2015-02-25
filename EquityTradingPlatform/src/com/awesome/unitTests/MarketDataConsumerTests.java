package com.awesome.unitTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.awesome.feeds.MarketDataConsumer;
import com.awesome.strategies.TMA;

public class MarketDataConsumerTests {

	@Test
	public void testAddSymbol() {
		MarketDataConsumer md = new MarketDataConsumer();
		md.addSymbol("AAPL");
		assertEquals(true, md.symbols.contains("AAPL"));
	}

	@Test
	public void testRemoveSymbol() {
		MarketDataConsumer md = new MarketDataConsumer();
		md.addSymbol("AAPL");
		md.removeSymbol("AAPL");
		assertEquals(false, md.symbols.contains("AAPL"));
	}
}
