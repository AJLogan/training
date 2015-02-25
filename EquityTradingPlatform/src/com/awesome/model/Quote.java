package com.awesome.model;

public class Quote {
	private String key;
	private float askPrice;
	private float bidPrice;
	private int askSize;
	private int bidSize;
	private float closePrice;
	private int volume;

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the askPrice
	 */
	public float getAskPrice() {
		return askPrice;
	}

	/**
	 * @param askPrice
	 *            the askPrice to set
	 */
	public void setAskPrice(float askPrice) {
		this.askPrice = askPrice;
	}

	/**
	 * @return the bidPrice
	 */
	public float getBidPrice() {
		return bidPrice;
	}

	/**
	 * @param bidPrice
	 *            the bidPrice to set
	 */
	public void setBidPrice(float bidPrice) {
		this.bidPrice = bidPrice;
	}

	/**
	 * @return the askSize
	 */
	public int getAskSize() {
		return askSize;
	}

	/**
	 * @param askSize
	 *            the askSize to set
	 */
	public void setAskSize(int askSize) {
		this.askSize = askSize;
	}

	/**
	 * @return the bidSize
	 */
	public int getBidSize() {
		return bidSize;
	}

	/**
	 * @param bidSize
	 *            the bidSize to set
	 */
	public void setBidSize(int bidSize) {
		this.bidSize = bidSize;
	}

	/**
	 * @return the closePrice
	 */
	public float getClosePrice() {
		return closePrice;
	}

	/**
	 * @param closePrice
	 *            the closePrice to set
	 */
	public void setClosePrice(float closePrice) {
		this.closePrice = closePrice;
	}

	/**
	 * @return the volume
	 */
	public int getVolume() {
		return volume;
	}

	/**
	 * @param volume
	 *            the volume to set
	 */
	public void setVolume(int volume) {
		this.volume = volume;
	}

}
