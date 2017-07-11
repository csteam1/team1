package com.team1.data;

public abstract class Order implements Comparable<Order>{
    private Currency currency;
    private double amount;
    private Side side;

    public Order(){}
    
    public Order(Currency currency, double amount, Side side) {
        this.currency = currency;
        this.amount = amount;
        this.side = side;
    }

    public abstract boolean match(Order order);

	@Override
	public int compareTo(Order o) {
		
		return 0;
	}

    
    @Override
    public String toString() {
        return "Order{" +
                "currency=" + currency +
                ", amount=" + amount +
                ", side=" + side +
                '}';
    }

    public Currency getCurrency() {
        return currency;
    }


    public double getAmount() {
        return amount;
    }

    public Side getSide() {
        return side;
    }

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setSide(Side side) {
		this.side = side;
	}   
}