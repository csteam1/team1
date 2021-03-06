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
		
		jdbcTemplate.update(addOrderInHistorySql, currencyFrom2.name(), currencyTo2.name(), price2, lotSize2, dateOfTransaction2);
		
	}

	public double getMarketPrice(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		
		//Query for price
		String getPriceSql = "select * from historyTransaction ht "
				+ "join ( select max(mx.dateOfTransaction) as maxId"
				+ " from historyTransaction mx)m where m.maxId = ht.dateOfTransaction "
				+ "and currencyFrom=? and currencyTo=? ";
		
		Order order = jdbcTemplate.queryForObject(getPriceSql, new Object[]{currencyFrom.name(), currencyTo.name()}, new HistoryTransactionRowMapper());
		
		return order.getPrice();

	}

	public void processLimitOrder(JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		
		//query for opposite currency match
		String findSql = "select * from transaction where currencyTo=? and currencyFrom=? and status=?";
		Order match = null;
		
		
		List<Order> result = jdbcTemplate.query(findSql, new Object[]{this.currencyFrom.name(), this.currencyTo.name(), (Status.NOT_COMPLETED).name()}, new TransactionRowMapper());
		
		if(result.isEmpty()) return;
		
		double max = -1;
		for(int i=0;i<result.size();i++){
			if(Math.abs((result.get(i).getLimitPrice()) - this.getLimitPrice())> max){
				max = Math.abs((result.get(i).getLimitPrice()) - this.getLimitPrice());
				match = result.get(i);
			}
		}
		
		
		
		
		this.status = Status.COMPLETED;
		addOrderInHistoryTable(jdbcTemplate, this.currencyFrom,this.currencyTo,this.price,this.lotSize,this.dateOfTransaction);
		//update status query
//			String updateStatusSql = "update transaction set status=? where t_id=?";
//			jdbcTemplate.update(updateStatusSql, this.status.name(), this.t_id);
		
		
		
		//update query
		String updateMatchTransSql = "update transaction set status=? where t_id=?";
		jdbcTemplate.update(updateMatchTransSql, (Status.COMPLETED).name(), match.getT_id()+ "");
		//addOrderInHistoryTable();
		addOrderInHistoryTable(jdbcTemplate, match.getCurrencyFrom(), match.getCurrencyTo(),
				match.getPrice(), match.getLotSize(),match.getDateOfTransaction());
		
			
		
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




class HistoryTransactionRowMapper implements RowMapper<Order>
{
	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException{
		Order transaction = new Order();
		transaction.setCurrencyFrom(Currency.valueOf(rs.getString("currencyFrom")));
		transaction.setCurrencyTo(Currency.valueOf(rs.getString("currencyTo")));
		transaction.setDateOfTransaction(rs.getString("dateOfTransaction"));
		transaction.setLotSize(rs.getInt("lotSize"));
		transaction.setPrice(rs.getDouble("price"));

		return transaction;
	}
}
