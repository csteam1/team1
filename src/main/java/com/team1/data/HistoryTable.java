package com.team1.data;

public class HistoryTable {
	
	public HistoryTable() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HistoryTable(Currency currencyFrom, Currency currencyTo, String dateOfTransaction, double price, int lotSize) {
		super();
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.dateOfTransaction = dateOfTransaction;
		this.price = price;
		this.lotSize = lotSize;
	}
	Currency currencyFrom, currencyTo; String dateOfTransaction;
	double price;
	int lotSize;
	public Currency getCurrencyFrom() {
		return currencyFrom;
	}
	public void setCurrencyFrom(Currency currencyFrom) {
		this.currencyFrom = currencyFrom;
	}
	public Currency getCurrencyTo() {
		return currencyTo;
	}
	public void setCurrencyTo(Currency currencyTo) {
		this.currencyTo = currencyTo;
	}
	public String getDateOfTransaction() {
		return dateOfTransaction;
	}
	public void setDateOfTransaction(String dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getLotSize() {
		return lotSize;
	}
	public void setLotSize(int lotSize) {
		this.lotSize = lotSize;
	}
	
	
	

}
