package com.simple.stocks.task;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

import com.simple.stocks.dto.StocksDto;
import com.simple.stocks.dto.TradeDto;
import com.simple.util.stockUtil;

public class SimpleStocksTask {

	private Map<String, StocksDto> stockData = new HashMap<String, StocksDto>();
	private List<TradeDto> tradeDtoList = new ArrayList<TradeDto>();

	SimpleStocksTask(Map<String, StocksDto> stockData,
			List<TradeDto> tradeDtoList) {
		this.stockData = stockData;
		this.tradeDtoList = tradeDtoList;
	}

	// This method calculates the DividendYield by formula ( lastDividend/Market
	// Price ) if type is common and ( Fixed Dividend .Par Value/Market Price )
	// if type is Preferred.

	public void calculateDividendYield(Scanner scanner) {

		System.out.println("Enter the market price ");
		Double marketPrice = null;
		while (true) {
			marketPrice = scanner.nextDouble();
			if (marketPrice != null) {
				break;
			} else {
				System.out.println(" please enter valid marketPrice ");
			}

		}
		String stockSymbol = null;

		while (true) {

			System.out.println("Enter the stock ");

			stockSymbol = scanner.next();

			if (stockSymbol != null) {
				break;
			} else {
				System.out.println(" please enter valid stockSymbol ");
			}
		}
		Double dividendYield = stockUtil.calculateDividend(marketPrice,
				stockSymbol, stockData);

		System.out.println("dividend yield = " + dividendYield);
	}

	// This method calculates P/E Ratio by formula ( MarketPrice/Dividend )

	public void calculatePERatio(Scanner scanner) {

		System.out.println("Enter the market price ");

		Double marketPrice = scanner.nextDouble();

		System.out.println("Enter the stock ");

		String stockSymbol = scanner.next();

		Double dividendYield = stockUtil.calculateDividend(marketPrice,
				stockSymbol, stockData);

		if (dividendYield == null || dividendYield == 0) {
			System.out.println("Error in input . Please try again");
		} else {
			Double peRatio = marketPrice / dividendYield;
			System.out.println("P/E ratio is " + peRatio);
		}
	}

	// This method is used to record Trade . Inputs from users are Stock
	// Symbol,quantity of shares,buy or sell ,buy or sell and timestamp
	public void recordTrade(Scanner scanner) {

		System.out.println("Please record a trade ");
		while (true) {
			TradeDto tradeDto = new TradeDto();

			System.out.println("Enter Stock Symbol");
			String stockSymbol = scanner.next();
			tradeDto.setStockSymbol(stockSymbol);
			Integer quantityOfShares = null;
			do {
				System.out.println("Enter quantity of shares");
				quantityOfShares = scanner.nextInt();

			} while (quantityOfShares == 0 || quantityOfShares == null);

			tradeDto.setQuantityOfShare(quantityOfShares);

			System.out
					.println("Enter whether its buy or sell ( Enter B for Buy and S for Sell");
			String buyorSell = scanner.next();
			tradeDto.setBuySellIndicator(buyorSell);

			System.out.println("Enter trade price");
			Integer tradePrice = scanner.nextInt();
			tradeDto.setTradePrice(tradePrice);

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS",
					Locale.US);
			System.out
					.println("Enter date and time in the format yyyy-MM-ddTHH:mm");
			System.out.println("For example, it is now "
					+ format.format(new Date()));
			Date date = null;
			scanner = new Scanner(System.in);
			while (date == null) {
				String line = scanner.nextLine();
				try {
					date = format.parse(line);
				} catch (ParseException e) {
					e.printStackTrace();
					System.out
							.println("Sorry, that's not valid. Please try again.");
				}
			}

			tradeDto.setTimeStamp(date);
			tradeDtoList.add(tradeDto);
			System.out
					.println(" Do you want to enter another trade . Please enter Y to enter or N to exit");
			String nextTrade = scanner.nextLine();
			if (nextTrade.trim().equalsIgnoreCase("Y")) {
				continue;
			} else {
				break;
			}

		}
	}

	// This method is used to calculate Volume Weighted Stock Price. Formula
	// used is ( SumOf ( Trade Price * Quantity) / SumOF(Quantity)).
	public void calculateVolumeWeightedStockPrice() {

		Double volumeWeighted = stockUtil
				.getVolumeWeightedStockPrice(tradeDtoList);

		System.out.println("Volume Weighted Stock Price " + volumeWeighted);
	}

	// This method is used to calculate GBCE All Share Index. Formula used is
	// Geometric Mean of prices for all stocks
	public void calculateGBCEAllShareIndex() {

		Double gBCEAllShareIndex = stockUtil.geometricMean(tradeDtoList);

		System.out.println("GBCE All Share Index " + gBCEAllShareIndex);
	}

}
