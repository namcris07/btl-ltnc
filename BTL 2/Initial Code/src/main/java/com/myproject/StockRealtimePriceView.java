package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockRealtimePriceView implements StockViewer {
    private final Map<String, Double> lastPrices = new HashMap<>();

    @Override
    public void onUpdate(StockPrice stockPrice) {
        // TODO: Implement logic to check if price has changed and log it
        String stockCode = stockPrice.getCode();
        double currentPrice = stockPrice.getAvgPrice();

        if (lastPrices.containsKey(stockCode)) {
            double lastPrice = lastPrices.get(stockCode);
            if (Double.compare(lastPrice, currentPrice) != 0) {
                Logger.logRealtime(stockCode, currentPrice);
            }
        }

        lastPrices.put(stockCode, currentPrice);
    }
}
