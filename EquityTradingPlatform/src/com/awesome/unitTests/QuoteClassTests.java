package com.awesome.unitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.awesome.model.Quote;

public class QuoteClassTests {

	@Test
	public void testGetKey() {
		Quote quote = new Quote();
		quote.setKey("AAPL");
		assertEquals(true, quote.getKey() == null);
	}

	@Test
	public void testSetKey() {
		Quote quote = new Quote();
		quote.setKey("AAPL");
		assertEquals(true, quote.getKey() == "AAPL");
	}

	@Test
	public void testGetAskPrice() {
		Quote quote = new Quote();
		assertEquals(true, quote.getAskPrice() == 0);
	}

	@Test
	public void testSetAskPrice() {
		Quote quote = new Quote();
		quote.setAskPrice(12.1f);
		assertEquals(true, quote.getAskPrice() == 12.1f);
	}

	@Test
	public void testGetBidPrice() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetBidPrice() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetAskSize() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetAskSize() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetBidSize() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetBidSize() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetClosePrice() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetClosePrice() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testGetVolume() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testSetVolume() {
		fail("Not yet implemented"); // TODO
	}

}
