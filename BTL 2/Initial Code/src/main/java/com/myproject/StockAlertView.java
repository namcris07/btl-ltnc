package com.myproject;

import java.util.HashMap;
import java.util.Map;

public class StockAlertView implements StockViewer {
    private double alertThresholdHigh;
    private double alertThresholdLow;
    private Map<String, Double> lastAlertedPrices = new HashMap<>(); // TODO: Stores last alerted price per stock

    public StockAlertView(double highThreshold, double lowThreshold) {
        // TODO: Implement constructor
        this.alertThresholdHigh = highThreshold;
        this.alertThresholdLow = lowThreshold;
    }
    private static final double SIGNIFICANT_CHANGE_PERCENT = 0.004;

    @Override
    public void onUpdate(StockPrice stockPrice) {
         String code = stockPrice.getCode();
        double price = stockPrice.getAvgPrice();

        if (!isOutOfThreshold(price)) {
            lastAlertedPrices.remove(code);
            return;
        }

        Double lastPrice = lastAlertedPrices.get(code);

        if (lastPrice == null) {
            triggerAlert(code, price);
            return;
        }

        double delta = Math.abs(price - lastPrice);
        double percentChange = delta / lastPrice;

        if (percentChange >= SIGNIFICANT_CHANGE_PERCENT) {
            triggerAlert(code, price);
        }
    }

    private boolean isOutOfThreshold(double price) {
        return price >= alertThresholdHigh || price <= alertThresholdLow;
    }

    private void triggerAlert(String stockCode, double price) {
        if (price >= alertThresholdHigh) {
            alertAbove(stockCode, price);
        } else {
            alertBelow(stockCode, price);
        }
        lastAlertedPrices.put(stockCode, price); 
    }

    private void alertAbove(String stockCode, double price) {
        Logger.logAlert(stockCode, price);
    }

    private void alertBelow(String stockCode, double price) {
        Logger.logAlert(stockCode, price);
    }
}
