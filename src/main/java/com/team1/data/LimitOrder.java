package com.team1.data;

public class LimitOrder extends Order {
	
    private double limit;
        
    public LimitOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LimitOrder(Currency currencyFrom, Currency currencyTo, Side side, int u_id, int t_id, String date,
			double price, Type type, int lotSize, Status status) {
		super(currencyFrom, currencyTo, side, u_id, t_id, date, price, type, lotSize, status);
		
		this.limit = limit;
		// TODO Auto-generated constructor stub
	}

	@Override
    public boolean match(Order order) {
        System.out.println("domain.LimitOrder match");
        return false;
    }
}