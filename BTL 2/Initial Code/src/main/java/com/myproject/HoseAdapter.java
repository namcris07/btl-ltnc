package com.myproject;

import java.util.List;

public class HoseAdapter implements PriceFetcher {
    private HosePriceFetchLib hoseLib;
    private List<String> stockCodes;
 
    public HoseAdapter(HosePriceFetchLib hoseLib, List<String> stockCodes) {
        // TODO: Implement constructor
        this.hoseLib = hoseLib;
        this.stockCodes = stockCodes;
    }

    @Override
    public List<StockPrice> fetch() {
        // TODO: Fetch stock data and convert it to StockPrice list
        List<StockPrice> prices = new java.util.ArrayList<>();
        List<HoseData> hoseDataList = hoseLib.getPrices(stockCodes);
        for (HoseData hoseData : hoseDataList) {
            prices.add(convertToStockPrice(hoseData));
        }
        return prices;
    }

    private StockPrice convertToStockPrice(HoseData hoseData) {
        // TODO: Convert HoseData to StockPrice
        return new StockPrice(
            hoseData.getStockCode(),
            hoseData.getPrice(),
            hoseData.getVolume(),
            hoseData.getTimestamp());
    }
}
