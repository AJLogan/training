/**
 * 
 */
package com.awesome;

/**
 * @author andrew
 *
 */
public class EquityTradingPlatform extends Thread {
	public void run() {
		System.out.println("My thread is in running state.");
	}

	public static void main(String args[]) {
		EquityTradingPlatform etp = new EquityTradingPlatform();
		etp.start();
	}
}