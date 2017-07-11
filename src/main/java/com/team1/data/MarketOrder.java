package com.team1.data;

public class MarketOrder extends Order {

    @Override
    public boolean match(Order order) {
        System.out.println("domain.MarketOrder match");
        return false;
    }

    public MarketOrder(){}

    public MarketOrder(Currency currency, double amount, Side side) {
        super(currency, amount, side);
    }


}
