package com.simple.stocks.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.simple.stocks.dto.StocksDto;
import com.simple.stocks.dto.TradeDto;

public class SimpleStocksMain {

	static Map<String, StocksDto> stockData = new HashMap<String, StocksDto>();
	static List<TradeDto> tradeDtoList = new ArrayList<TradeDto>();
	static Scanner scanner = new Scanner(System.in);
	static {
		StocksDto stocksDto = new StocksDto();
		stocksDto.setStockSymbol("TEA");
		stocksDto.setType("Common");
		stocksDto.setLastDividend(0d);
		stocksDto.setFixedDividend(null);
		stocksDto.setParValue(100d);
		stockData.put("TEA", stocksDto);

		stocksDto = new StocksDto();
		stocksDto.setStockSymbol("POP");
		stocksDto.setType("Common");
		stocksDto.setLastDividend(8d);
		stocksDto.setFixedDividend(null);
		stocksDto.setParValue(100d);
		stockData.put("POP", stocksDto);

		stocksDto = new StocksDto();
		stocksDto.setStockSymbol("ALE");
		stocksDto.setType("Common");
		stocksDto.setLastDividend(23d);
		stocksDto.setFixedDividend(null);
		stocksDto.setParValue(60d);
		stockData.put("ALE", stocksDto);

		stocksDto = new StocksDto();
		stocksDto.setStockSymbol("GIN");
		stocksDto.setType("Preferred");
		stocksDto.setLastDividend(8d);
		stocksDto.setFixedDividend(2d);
		stocksDto.setParValue(100d);
		stockData.put("GIN", stocksDto);

		stocksDto = new StocksDto();
		stocksDto.setStockSymbol("JOE");
		stocksDto.setType("Common");
		stocksDto.setLastDividend(13d);
		stocksDto.setFixedDividend(null);
		stocksDto.setParValue(250d);
		stockData.put("JOE", stocksDto);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		while (true) {
			System.out.println("\n");
			System.out.println("Welcome to Simple Stocks Application");
			System.out.println("Enter 1 to calculate the dividend yield");
			System.out.println("Enter 2 to calculate the P/E Ratio");
			System.out.println("Enter 3 to Record a trade");
			System.out
					.println("Enter 4 to calculate the Volume Weighted Stock");
			System.out.println("Enter 5 to calculate the GBCE All Share Index");
			System.out.println("Enter 6 to Exit");

			Integer sentence = scanner.nextInt();

			SimpleStocksTask simpleStocksTask = new SimpleStocksTask(stockData,
					tradeDtoList);
			switch (sentence) {

			case 1:

				simpleStocksTask.calculateDividendYield(scanner);
				break;

			case 2:

				simpleStocksTask.calculatePERatio(scanner);
				break;

			case 3:

				simpleStocksTask.recordTrade(scanner);
				break;

			case 4:

				simpleStocksTask.calculateVolumeWeightedStockPrice();
				break;

			case 5:

				simpleStocksTask.calculateGBCEAllShareIndex();
				break;

			}
			if (sentence == 6) {
				System.out.println("End of Application");
				scanner.close();
				break;
			}
		}
	}

}
