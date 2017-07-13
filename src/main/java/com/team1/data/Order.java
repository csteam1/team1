package com.team1.data;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

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
    
    @Autowired
	private JdbcTemplate jdbcTemplate;
    
    
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

	public void addOrderInHistoryTable(Currency currencyFrom2, Currency currencyTo2, double price2, int lotSize2, String dateOfTransaction2) {
		// TODO Auto-generated method stub
		
	}

	public double getMarketPrice() {
		// TODO Auto-generated method stub
		
		//Query for price
		String getPriceSql = "select price from historyTransaction "
				+ "where currencyFrom=? and currencyTo=? "
				+ "and dateOfTransaction = max(dateOfTransaction)";
		double market_price = 0;
		
//		Order order = jdbcTemplate.query(getPriceSql,{currencyFrom.name(), currencyTo.name()}, new);
		return market_price;

	}

	public void processLimitOrder() {
		// TODO Auto-generated method stub
		
		//query for opposite currency match
		String findSql = "select * from transaction where currencyFrom=? and currencyTo=? and status=?";
		
		List<Map<String, Object>> result = jdbcTemplate.queryForList(findSql, new Object[]{currencyFrom.name(), currencyTo.name(), status.name()});

		
		if (!result.isEmpty()){

			status = Status.COMPLETED;
			addOrderInHistoryTable(currencyFrom,currencyTo,price,lotSize,dateOfTransaction);
			//update status query
			String updateStatusSql = "update transaction set status=? where t_id=?";
			jdbcTemplate.update(updateStatusSql, status, t_id);
			
			Map<String, Object> match = result.get(0);
			
			//addOrderInHistoryTable();
			addOrderInHistoryTable(Currency.valueOf((match.get("currencyFrom")).toString()),
					Currency.valueOf((match.get("currencyTo")).toString()),
					(double)match.get("price"),(int)match.get("lotSize"),(match.get("dateOfTransaction")).toString());
			//update query
			String updateMatchTransSql = "update transaction set status=? where t_id=?";
			jdbcTemplate.update(updateMatchTransSql, Status.COMPLETED, (match.get("t_id")).toString());
		
		}
		
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