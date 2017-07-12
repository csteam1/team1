package com.team1.data;

public class LimitOrder extends Order {
	
    private double limit;
        
    public LimitOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LimitOrder(Currency currencyFrom, Currency currencyTo, Side side, int u_id, int t_id, String dateOfTransaction,
			double price, Type type, int lotSize, Status status, double limitPrice) {
		super(currencyFrom, currencyTo, side, u_id, t_id, dateOfTransaction, price, type, lotSize, status, limitPrice);
		
		//this.limit = limit;
		// TODO Auto-generated constructor stub
	}

/*	@Override
    public boolean match(Order order) {
        System.out.println("domain.LimitOrder match");
        return false;
    }*/
}