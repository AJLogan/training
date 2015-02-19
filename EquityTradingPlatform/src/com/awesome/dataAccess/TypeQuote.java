package com.awesome.dataAccess;


/**
 * 
 * @author james
 *
 */
public class TypeQuote {

	/**
	 * Quote fields
	 */
	private String tick;
	private float askPrice;
	private float bidPrice;
	private int askSize;
	private int bidSize;
	private float closePrice;
	private int volume;

	/**
	 * Bollinger Bands Constructor
	 */
	public TypeQuote(String tick, float cloasePrice) {
		this.tick = tick;
		this.closePrice = cloasePrice;
	}

	/**
	 * Default constructor
	 */
	public TypeQuote() {

	}

	public float getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(float askPrice) {
		this.askPrice = askPrice;
	}

	public float getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(float bidPrice) {
		this.bidPrice = bidPrice;
	}

	public int getAskSize() {
		return askSize;
	}

	public void setAskSize(int askSize) {
		this.askSize = askSize;
	}

	public int getBidSize() {
		return bidSize;
	}

	public void setBidSize(int bidSize) {
		this.bidSize = bidSize;
	}

	public float getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(float closePrice) {
		this.closePrice = closePrice;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getTick() {
		return tick;
	}

	public void setTick(String tick) {
		this.tick = tick;
	}

}
