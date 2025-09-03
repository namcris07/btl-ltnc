package com.myproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StockFeeder {
    private List<Stock> stockList = new ArrayList<>();
    private Map<String, List<StockViewer>> viewers = new HashMap<>();
    private static StockFeeder instance = null;

    // TODO: Implement Singleton pattern
    private StockFeeder() {}

    public static StockFeeder getInstance() {
        // TODO: Implement Singleton logic
        if (instance == null) {
            instance = new StockFeeder();
        }
        return instance;
    }

    public void addStock(Stock stock) {
        // TODO: Implement adding a stock to stockList
        stockList.add(stock);
    }

    public void registerViewer(String code, StockViewer stockViewer) {
        // TODO: Implement registration logic, including checking stock existence
        boolean stockExists = false;
        for (Stock stock : stockList) {
            if (stock.getCode().equals(code)) {
                stockExists = true;
                break;
            }
        }
        
        if (!stockExists) {
            Logger.errorRegister(code);
            return;
        }
        
        viewers.computeIfAbsent(code, k -> new ArrayList<>()).add(stockViewer);
    }    

    public void unregisterViewer(String code, StockViewer stockViewer) {
        // TODO: Implement unregister logic, including error logging
        boolean stockExists = false;
        for (Stock stock : stockList) {
            if (stock.getCode().equals(code)) {
                stockExists = true;
                break;
            }
        }
        
        if (!stockExists || !viewers.containsKey(code) || !viewers.get(code).contains(stockViewer)) {
            Logger.errorUnregister(code);
            return;
        }
        
        viewers.get(code).remove(stockViewer);
        if (viewers.get(code).isEmpty()) {
            viewers.remove(code);
        }
    }

    public void notify(StockPrice stockPrice) {
        // TODO: Implement notifying registered viewers about price updates
        List<StockViewer> stockViewers = viewers.get(stockPrice.getCode());
        if (stockViewers != null) {
            for (StockViewer viewer : stockViewers) {
                viewer.onUpdate(stockPrice);
            }
        }
    }
}
