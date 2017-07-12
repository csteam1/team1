package com.team1.data;

public class MarketOrder extends Order {

    @Override
    public boolean match(Order order) {
        System.out.println("domain.MarketOrder match");
        return false;
    }

    public MarketOrder(){}

	public MarketOrder(Currency currencyFrom, Currency currencyTo, Side side, int u_id, int t_id, String date,
			double price, Type type, int lotSize, Status status, double limitPrice) {
		super(currencyFrom, currencyTo, side, u_id, t_id, date, price, type, lotSize, status, limitPrice);
		// TODO Auto-generated constructor stub
	}
}
