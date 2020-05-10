package com.ionutmurgu;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList() {
        this.list = new LinkedHashMap<>();
    }

    public int addStock(StockItem item) {
        if (item != null) {
            // check if we have this item
            StockItem inStock = list.getOrDefault(item.getName(), item);
            // if we have this item, adjust the quantity
            if (inStock != item) {
                item.adjustStock(inStock.quantityInStock());
            }

            list.put(item.getName(), item);
            return item.quantityInStock();
        }
        return 0;
    }

    public void sellStock(Map<StockItem, Integer> list) {
        for (Map.Entry<StockItem, Integer> s : list.entrySet()) {
            s.getKey().adjustStock(-s.getValue());
        }
    }


    public int reserveStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);
        if (((inStock != null)) && (inStock.quantityInStock() >= quantity) && (quantity > 0)) {
            inStock.reserveItems(inStock, quantity);
            return quantity;
        }
        return 0;
    }

    public int unReserveStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);
        if (((inStock != null)) && (inStock.quantityInStock() >= quantity) && (quantity > 0) && (quantity <= inStock.getReserved())) {
            // inStock.adjustStock(-quantity);
            inStock.unReserveItems(inStock, quantity);
            //  inStock.adjustReserved(-quantity);
            return quantity;
        }
        return 0;
    }

    public StockItem get(String key) {
        return list.get(key);
    }

    public Map<String, Double> priceList() {
        Map<String, Double> prices = new LinkedHashMap<>();
        for (Map.Entry<String, StockItem> item : list.entrySet()) {
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    public Map<String, StockItem> Items() {
        return Collections.unmodifiableMap(list);
    }


    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;
        for (Map.Entry<String, StockItem> item : list.entrySet()) {
            StockItem stockItem = item.getValue();

            double itemValue = stockItem.getPrice() * stockItem.quantityInStock();

            s = s + stockItem + ". There are " + stockItem.quantityInStock() + " in stock. Value of items: ";
            s = s + String.format("%.2f", itemValue) + "\n";
            totalCost += itemValue;
        }

        return s + "Total stock value " + String.format("%.2f", totalCost);
    }
}