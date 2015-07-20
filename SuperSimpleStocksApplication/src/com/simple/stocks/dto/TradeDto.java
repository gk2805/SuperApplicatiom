package com.simple.stocks.dto;

import java.util.Date;

public class TradeDto {

	private String stockSymbol;
	private Date timeStamp;
	private Integer quantityOfShare;
	private String buySellIndicator;
	private Integer tradePrice;

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Integer getQuantityOfShare() {
		return quantityOfShare;
	}

	public void setQuantityOfShare(Integer quantityOfShare) {
		this.quantityOfShare = quantityOfShare;
	}

	public String getBuySellIndicator() {
		return buySellIndicator;
	}

	public void setBuySellIndicator(String buySellIndicator) {
		this.buySellIndicator = buySellIndicator;
	}

	public Integer getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(Integer tradePrice) {
		this.tradePrice = tradePrice;
	}

}
