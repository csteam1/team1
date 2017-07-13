package com.team1.services;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.team1.data.Currency;
import com.team1.data.Order;
import com.team1.data.Side;
import com.team1.data.Status;
import com.team1.data.Type;

public class CancelOrder {

	Enum<Status> orderStatus;
	public String cancelOrder (int orderId, JdbcTemplate jdbcTemplate, int userId)
	{
		
		//Query for gettting status of order from orderId
		String orderStatusQuery = "select * from transaction where t_id=? and u_id=?";
		
		Order order = jdbcTemplate.queryForObject(orderStatusQuery, new Object[]{orderId, userId}, new TransactionRowMapper());
		orderStatus = order.getStatus();
		
		if (orderStatus == Status.COMPLETED)
			return "Order cannot be canceled. It is already executed ";
		else if (orderStatus == Status.CANCELED)
			return "Order is already cancelled";
		else 
		{
			String update_status = "update transaction set status=? where u_id=?";
			jdbcTemplate.update(update_status, Status.CANCELED, userId);
			//Insert query for updating status as cancelled
			return "Order cancelled";
		}
			
	}
	
	
}