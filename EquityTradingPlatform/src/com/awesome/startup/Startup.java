package com.awesome.startup;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.awesome.feeds.MarketDataConsumer;
import com.awesome.strategies.ManualTrader;
import com.awesome.strategies.PBO;
import com.awesome.strategies.TMA;

/**
 * Application Lifecycle Listener implementation class Startup
 *
 */
@WebListener
public class Startup implements ServletContextListener {
	ExecutorService executor = null;
	private MarketDataConsumer app = new MarketDataConsumer();
	private TMA twoPoint = new TMA();
	private PBO breakout = new PBO();
	private ManualTrader mt = new ManualTrader();

	/**
	 * Default constructor.
	 */
	public Startup() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		executor.shutdownNow();
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		executor = Executors.newFixedThreadPool(10); // Max 10 threads.
		executor.execute(app);
		ServletContext ctx = arg0.getServletContext();
		// Startup strategies
		executor.execute(twoPoint);
		executor.execute(breakout);
		executor.execute(mt);
		ctx.setAttribute("app", app);
		ctx.setAttribute("pbo", breakout);
		ctx.setAttribute("tma", twoPoint);
		ctx.setAttribute("ManualTrader", mt);
		 app.addSymbol("BARC.L");
		// app.addSymbol("YHOO");
		// app.addSymbol("AAPL");
		// app.addHandler("AAPL", twoPoint);
		// app.addHandler("YHOO", twoPoint);
		// app.addHandler("AAPL", breakout);
		// app.addHandler("YHOO", breakout);
	}

}
