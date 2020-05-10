package com.ionutmurgu;

public class Main {
    private static StockList stockList = new StockList();

    public static void main(String[] args) {

        StockItem temp = new StockItem("bread", 2.5, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 6.8, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 3000, 4);
        stockList.addStock(temp);

        temp = new StockItem("chair", 59.99, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 0.50, 2000);
        stockList.addStock(temp);
        temp = new StockItem("cup", 0.45, 7);
        stockList.addStock(temp);

        temp = new StockItem("door", 150.50, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 3, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 260, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);


        System.out.println(stockList);


        Basket ionutBasket = new Basket("Ionut");
        reserveItem(ionutBasket, "cake", 7);
        unReserveItem(ionutBasket, "cake", 3);
        System.out.println(ionutBasket);

        reserveItem(ionutBasket, "cake", 1);
        System.out.println(ionutBasket);

        reserveItem(ionutBasket, "car", 1);                    //Added 1 car
        System.out.println(ionutBasket);

        reserveItem(ionutBasket, "car", 1);                    //Added 1 car, car count shold be 2
        System.out.println(ionutBasket);

        unReserveItem(ionutBasket, "car", 1);                  //Removed 1 car, next count should show 1 car
        System.out.println(ionutBasket);
        reserveItem(ionutBasket, "spanner", 5);
        System.out.println(ionutBasket);

        reserveItem(ionutBasket, "juice", 4);
        reserveItem(ionutBasket, "cup", 12);
        reserveItem(ionutBasket, "bread", 1);
        System.out.println(ionutBasket);
        System.out.println(ionutBasket.Items());                         //before checkout

        ionutBasket = checkout(ionutBasket, true);

        System.out.println(ionutBasket.Items());                          //after checkout
        System.out.println(stockList);
        //System.out.println(stockList.priceList());
    }

//    public static int sellItem(Basket basket, String item, int quantity){
//        StockItem stockItem = stockList.get(item);
//        if(stockItem == null){
//            System.out.println("We don't sell " + item);
//            return 0;
//        }
//        if(stockList.sellStock(item,quantity) != 0){
//            return quantity;
//        }
//        return 0;
//    }

    public static int reserveItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if (stockList.reserveStock(item, quantity) >= 0) {
            basket.addToBasket(stockItem, quantity);
            return quantity;
        }
        return 0;
    }

    public static int unReserveItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if (stockList.unReserveStock(item, quantity) >= 0) {
            basket.removeFromBasket(stockItem, quantity);
            return quantity;
        }
        return 0;
    }

    public static Basket checkout(Basket basket, boolean option) {
        if (option) {
            stockList.sellStock(basket.Items());
            basket = new Basket(basket.getName());
        }
        return basket;
    }


}
