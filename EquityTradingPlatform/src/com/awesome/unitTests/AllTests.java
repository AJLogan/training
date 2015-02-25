package com.awesome.unitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ DatabaseConnectivityTests.class, MarketDataConsumerTests.class,
		QuoteClassTests.class })
public class AllTests {

}
