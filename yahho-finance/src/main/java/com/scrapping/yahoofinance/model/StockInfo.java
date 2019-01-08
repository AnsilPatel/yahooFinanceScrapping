package com.scrapping.yahoofinance.model;

public class StockInfo {
	private final String previousClose;
	private final String open;
	private final String bid;
	private final String ask;
	private final String daysRange;
	private final String FiftyTWoWeekRange;
	private final String volume;
	private final String avgVolume;
	private final String marketCap;
	private final String beta;
	private final String pERatio;
	private final String ePS;
	private final String earningsDate;
	private final String forwardDividendYield;
	private final String exDividendDate;
	private final String oneYearTargetEst;

	public StockInfo(String previousClose, String open, String bid, String ask, String daysRange,
			String fiftyTWoWeekRange, String volume, String avgVolume, String marketCap, String beta, String pERatio,
			String ePS, String earningsDate, String forwardDividendYield, String exDividendDate,
			String oneYearTargetEst) {
		this.previousClose = previousClose;
		this.open = open;
		this.bid = bid;
		this.ask = ask;
		this.daysRange = daysRange;
		FiftyTWoWeekRange = fiftyTWoWeekRange;
		this.volume = volume;
		this.avgVolume = avgVolume;
		this.marketCap = marketCap;
		this.beta = beta;
		this.pERatio = pERatio;
		this.ePS = ePS;
		this.earningsDate = earningsDate;
		this.forwardDividendYield = forwardDividendYield;
		this.exDividendDate = exDividendDate;
		this.oneYearTargetEst = oneYearTargetEst;
	}

	public String getPreviousClose() {
		return previousClose;
	}

	public String getOpen() {
		return open;
	}

	public String getBid() {
		return bid;
	}

	public String getAsk() {
		return ask;
	}

	public String getDaysRange() {
		return daysRange;
	}

	public String getFiftyTWoWeekRange() {
		return FiftyTWoWeekRange;
	}

	public String getVolume() {
		return volume;
	}

	public String getAvgVolume() {
		return avgVolume;
	}

	public String getMarketCap() {
		return marketCap;
	}

	public String getBeta() {
		return beta;
	}

	public String getpERatio() {
		return pERatio;
	}

	public String getePS() {
		return ePS;
	}

	public String getEarningsDate() {
		return earningsDate;
	}

	public String getForwardDividendYield() {
		return forwardDividendYield;
	}

	public String getExDividendDate() {
		return exDividendDate;
	}

	public String getOneYearTargetEst() {
		return oneYearTargetEst;
	}

}
