package com.team1.data;

public class Order{
    private Currency currencyFrom, currencyTo;
    private Side side;
    private int u_id, t_id;
    private String dateOfTransaction;
    private double price;
    private Type typeOrder;
    private int lotSize;
    private Status status;
    private double limitPrice;
    
    
    public Order(){}

	public Order(Currency currencyFrom, Currency currencyTo, Side side, int u_id, int t_id, String dateOfTransaction, double price,
			Type typeOrder, int lotSize, Status status, double limitPrice) {
		super();
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.side = side;
		this.u_id = u_id;
		this.t_id = t_id;
		this.dateOfTransaction = dateOfTransaction;
		this.price = price;
		this.typeOrder = typeOrder;
		this.lotSize = lotSize;
		this.status = status;
		this.limitPrice = limitPrice;
	}

/*	@Override
	public int compareTo(Order o) {
		
		return 0;
	}
*/
	public Currency getCurrencyFrom() {
        return currencyFrom;
    }

	public Currency getCurrencyTo() {
		return currencyTo;
	}

	public String getDateOfTransaction() {
		return dateOfTransaction;
	}

	
	public double getLimitPrice() {
		return limitPrice;
	}

	public int getLotSize() {
		return lotSize;
	}

	public double getPrice() {
		return price;
	}

    
    public Side getSide() {
        return side;
    }

    public Status getStatus() {
		return status;
	}

    public int getT_id() {
		return t_id;
	}

	public Type getTypeOrder() {
		return typeOrder;
	}

	public int getU_id() {
		return u_id;
	}

/*	public abstract boolean match(Order order);*/

	public void setCurrencyFrom(Currency currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public void setCurrencyTo(Currency currencyTo) {
		this.currencyTo = currencyTo;
	}

	public void setDateOfTransaction(String dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public void setLimitPrice(double limitPrice) {
		this.limitPrice = limitPrice;
	}

	public void setLotSize(int lotSize) {
		this.lotSize = lotSize;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setT_id(int t_id) {
		this.t_id = t_id;
	}

	public void setTypeOrder(Type typeOrder) {
		this.typeOrder = typeOrder;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	/*@Override
    public String toString() {
        return "Order{" +
                "currency=" + currencyFrom +
                ", amount=" +
                ", side=" + side +
                '}';
    }*/	
}