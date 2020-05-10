package com.ionutmurgu;

import java.util.HashMap;

public class StockItem implements Comparable<StockItem> {
    private final String name;
    private double price;
    private int quantityStock = 0;
    private int reserved = 0;
    private HashMap<StockItem, Integer> reservedItems;

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityStock = 0;
        this.reservedItems = new HashMap<>();
    }

    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
        this.reservedItems = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int quantityInStock() {
        return quantityStock;
    }

    public int getReserved() {
        return reserved;
    }

    public void adjustReserved(int reserved) {
        int newReserved = this.reserved + reserved;
        if (newReserved >= 0) {
            this.reserved = newReserved;
        }
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }

    public void adjustStock(int quantity) {
        int newQuantity = this.quantityStock + quantity;
        if (newQuantity >= 0) {
            this.quantityStock = newQuantity;
        }
    }

    public void reserveItems(StockItem item, int quantity) {
        int newReserved = this.reserved + quantity;
        if (newReserved >= 0 && quantityStock - newReserved >= 0) {
            this.reserved += newReserved;
            reservedItems.put(item, quantity);
        }
    }

    public void unReserveItems(StockItem item, int quantity) {
        if (reserved - quantity >= 0) {
            this.reserved -= quantity;
            reservedItems.remove(item, quantity);
        }
    }


    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering StockItem.equals");
        if (obj == this) {
            return true;
        }

        if ((obj == null) || (obj.getClass() != this.getClass())) {
            return false;
        }

        String objName = ((StockItem) obj).getName();
        return this.name.equals((objName));
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 31;
    }

    @Override
    public int compareTo(StockItem o) {
        System.out.println("Entering StockItem.compareTo");
        if (this == o) {
            return 0;
        }

        if (o != null) {
            return this.name.compareTo(o.getName());
        }

        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.name + ": price " + this.price;
    }
}

