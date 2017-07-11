package com.team1.data;

public class LimitOrder extends Order {
	
    private double limit;
    
    public LimitOrder(Currency currency, double amount, Side side, double limit) {
        super(currency, amount, side);
        this.limit = limit;
    }
    
    @Override
    public boolean match(Order order) {
        System.out.println("domain.LimitOrder match");
        return false;
    }
}
