package com.simple.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.simple.stocks.dto.StocksDto;
import com.simple.stocks.dto.TradeDto;

public class stockUtil {

	/**
	 * @param marketPrice
	 * @param stockSymbol
	 * @return
	 */
	// This method calculates the DividendYield by formula ( lastDividend/Market
	// Price ) if type is common and ( Fixed Dividend .Par Value/Market Price )
	// if type is Preferred.
	public static Double calculateDividend(Double marketPrice,
			String stockSymbol, Map<String, StocksDto> stockData) {

		StocksDto stocksDto = stockData.get(stockSymbol);

		if (stocksDto != null) {
			Double dividendYield = null;
			if (stocksDto.getType().equalsIgnoreCase("Common")) {
				dividendYield = stocksDto.getLastDividend() / marketPrice;
			} else if (stocksDto.getType().equalsIgnoreCase("Preferred")) {
				dividendYield = (stocksDto.getFixedDividend() * stocksDto
						.getParValue()) / (100 * marketPrice);
			}
			return dividendYield;
		} else {
			System.out
					.println(" No corresponding  stock found for given stock Symbol ");
			return null;
		}

	}

	// This method is used to record Trade . Inputs from users are Stock
	// Symbol,quantity of shares,buy or sell ,buy or sell and timestamp
	public static Double getVolumeWeightedStockPrice(List<TradeDto> tradeDtoList) {
		Double numerator = 0d;
		Double denominator = 0d;
		Double volumeWeighted = 0d;
		for (TradeDto tradeDto : tradeDtoList) {
			Calendar calendar1 = Calendar.getInstance();
			Calendar calendar2 = Calendar.getInstance();
			calendar1.setTime(tradeDto.getTimeStamp());
			calendar2.setTime(new Date());
			long milsecs1 = calendar1.getTimeInMillis();
			long milsecs2 = calendar2.getTimeInMillis();
			long diff = milsecs2 - milsecs1;
			long dminutes = diff / (60 * 1000);
			if (dminutes < 15) {
				numerator = (tradeDto.getTradePrice() * tradeDto
						.getQuantityOfShare()) + numerator;
				denominator = tradeDto.getQuantityOfShare() + denominator;
			}
		}

		if (denominator != 0) {
			volumeWeighted = numerator / denominator;
		} else {
			volumeWeighted = numerator;
		}
		return volumeWeighted;
	}

	// This method is used to calculate GBCE All Share Index. Formula used is
	// Geometric Mean of prices for all stocks
	public static double geometricMean(List<TradeDto> tradeDtoList) {
		double sum = 1;
		for (TradeDto tradeDto : tradeDtoList) {
			sum *= tradeDto.getTradePrice();
		}
		return Math.pow(sum, 1.0 / tradeDtoList.size());

	}

}
