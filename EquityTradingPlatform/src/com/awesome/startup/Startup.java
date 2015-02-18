package com.awesome.startup;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.awesome.feeds.MarketDataConsumer;

/**
 * Application Lifecycle Listener implementation class Startup
 *
 */
@WebListener
public class Startup implements ServletContextListener {
	ExecutorService executor = null;

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
		System.out.println("contextDestroyed");
		executor.shutdownNow();
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("contextInitialized");
		executor = Executors.newFixedThreadPool(10); // Max 10 threads.
		executor.execute(new MarketDataConsumer());
	}

}