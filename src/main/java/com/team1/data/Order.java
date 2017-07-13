package com.team1.data;

import com.team1.services.*;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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

	public void addOrderInHistoryTable(JdbcTemplate jdbcTemplate, Currency currencyFrom2, Currency currencyTo2, double price2, int lotSize2, String dateOfTransaction2) {
		// TODO Auto-generated method stub
		String addOrderInHistorySql = "insert into historyTransaction(currencyFrom, currencyTo, price, lotSize, dateOfTransaction)"
				+ "values(?,?,?,?,?)";
		
		jdbcTemplate.update(addOrderInHistorySql, currencyFrom2, currencyTo2, price2, lotSize2, dateOfTransaction2);
		
	}

	public double getMarketPrice(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		
		//Query for price
		String getPriceSql = "select * from historyTransaction "
				+ "where currencyFrom=? and currencyTo=? "
				+ "and dateOfTransaction = max(dateOfTransaction)";
		
		Order order = jdbcTemplate.queryForObject(getPriceSql, new Object[]{currencyFrom.name(), currencyTo.name()}, new TransactionRowMapper());
		
		return order.getPrice();

	}

	public void processLimitOrder(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		
		//query for opposite currency match
		String findSql = "select * from transaction where currencyTo=? and currencyFrom=? and status=?";
		
		
		try{
			Order result = jdbcTemplate.queryForObject(findSql, new Object[]{this.currencyFrom.name(), this.currencyTo.name(), this.status.name()}, new TransactionRowMapper());
			status = Status.COMPLETED;
			addOrderInHistoryTable(jdbcTemplate, currencyFrom,currencyTo,price,lotSize,dateOfTransaction);
			//update status query
			String updateStatusSql = "update transaction set status=? where t_id=?";
			jdbcTemplate.update(updateStatusSql, status, t_id);
			
			Order match = result;
			
			//addOrderInHistoryTable();
			addOrderInHistoryTable(jdbcTemplate, match.getCurrencyFrom(), match.getCurrencyTo(),
					match.getPrice(), match.getLotSize(),match.getDateOfTransaction());
			//update query
			String updateMatchTransSql = "update transaction set status=? where t_id=?";
			jdbcTemplate.update(updateMatchTransSql, Status.COMPLETED, match.getT_id()+ "");
			
		}catch(NullPointerException e){
			
		}
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
	
	
	




class TransactionRowMapper implements RowMapper<Order>
{
	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException{
		Order transaction = new Order();
		transaction.setCurrencyFrom(Currency.valueOf(rs.getString("currencyFrom")));
		transaction.setCurrencyTo(Currency.valueOf(rs.getString("currencyTo")));
		transaction.setDateOfTransaction(rs.getString("dateOfTransaction"));
		transaction.setLimitPrice(rs.getDouble("limitPrice"));
		transaction.setLotSize(rs.getInt("lotSize"));
		transaction.setPrice(rs.getDouble("price"));
		transaction.setSide(Side.valueOf(rs.getString("side")));
		transaction.setStatus(Status.valueOf(rs.getString("status")));
		transaction.setT_id(rs.getInt("t_id"));
		transaction.setTypeOrder(Type.valueOf(rs.getString("typeOrder")));
		transaction.setU_id(rs.getInt("u_id"));
		return transaction;
	}
}
