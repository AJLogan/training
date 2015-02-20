package com.awesome.orderManager;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.util.Iterator;
//import quickfix.*;
//import quickfix.field.*;
//import quickfix.fix42.*;

public class OrderManager {
	// The singleton instance
	private static OrderManager instance = null;

	// private final QuickFixConfigBuilder qfConfig;
	// private FixEngine engine = null;
	// private SocketInitiator fixConnection = null;

	private OrderManager() // private so can't be instantiated
	{
		// LogFactory logFactory = new ScreenLogFactory(true, true, true);
		// qfConfig = new QuickFixConfigBuilder();
		// engine = new FixEngine();
		// MessageStoreFactory messageStoreFactory = new FileStoreFactory(
		// qfConfig.GetFileStoreSettings());
		//
		// quickfix.fix42.MessageFactory messageFactory = new
		// quickfix.fix42.MessageFactory();
		//
		// try {
		// fixConnection = new SocketInitiator(engine, messageStoreFactory,
		// qfConfig.GetSessionSettings(), logFactory, messageFactory);
		// fixConnection.start(); // This starts the connection in another
		// // thread, so it doesn't block
		// } catch (quickfix.ConfigError ce) {
		// System.out.println("Error setting socket acceptor < "
		// + ce.getMessage() + " >");
		// }
	}

	public static OrderManager getInstance() {
		if (instance == null)
			instance = new OrderManager();
		return instance;
	}

	public class OrderResult {
		double price;
		int shares;
		boolean longPosition;
	}

	// Buy a stock
	public OrderResult buyOrder(String stock, double price, int shares) {
		OrderResult o = new OrderResult();
		o.price = price;
		o.shares = shares;
		o.longPosition = true;
		// engine.sendNewBuyOrder(stock, price, shares);
		return o;
	}

	// Sell a stock
	public OrderResult sellOrder(String stock, double price, int shares) {
		OrderResult o = new OrderResult();
		o.price = price;
		o.shares = shares;
		o.longPosition = false;
		// engine.sendNewSellOrder(stock, price, shares);
		return o;
	}
}
