package com.team1.data;

public abstract class Order implements Comparable<Order>{
    private Currency currencyFrom, currencyTo;
    private Side side;
    private int u_id, t_id;
    private String date;
    private double price;
    private Type type;
    private int lotSize;
    private Status status;
    private double limitPrice;
    
    public double getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(double limitPrice) {
		this.limitPrice = limitPrice;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Order(){}

	
	public Order(Currency currencyFrom, Currency currencyTo, Side side, int u_id, int t_id, String date, double price,
			Type type, int lotSize, Status status, double limitPrice) {
		super();
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.side = side;
		this.u_id = u_id;
		this.t_id = t_id;
		this.date = date;
		this.price = price;
		this.type = type;
		this.lotSize = lotSize;
		this.status = status;
		this.limitPrice = limitPrice;
	}

	public abstract boolean match(Order order);

	@Override
	public int compareTo(Order o) {
		
		return 0;
	}

    
    @Override
    public String toString() {
        return "Order{" +
                "currency=" + currencyFrom +
                ", amount=" +
                ", side=" + side +
                '}';
    }

    public Currency getCurrencyFrom() {
        return currencyFrom;
    }

    public Side getSide() {
        return side;
    }

	public void setCurrencyFrom(Currency currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public Currency getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(Currency currencyTo) {
		this.currencyTo = currencyTo;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public int getT_id() {
		return t_id;
	}

	public void setT_id(int t_id) {
		this.t_id = t_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getLotSize() {
		return lotSize;
	}

	public void setLotSize(int lotSize) {
		this.lotSize = lotSize;
	}	
}