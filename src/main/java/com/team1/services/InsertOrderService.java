package com.team1.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.team1.data.Order;

@Component
public class InsertOrderService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String insertOrder(Order order){
		//final String checkSql = "insert into transaction() ";
		final String sql = "insert into "
				+ "transaction(u_id, t_id, side, typeOrder, "
				+ "lotSize, dateOfTransaction, price, currencyFrom, "
				+ "currencyTo, status, limitPrice) "
				+ "values(?,?,?,?,?,?,?,?,?,?)";
		
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(0, order.getU_id());
				ps.setInt(1, 0);
				ps.setString(2, order.getTypeOrder().name());
				ps.setString(3, order.getSide().name());
				ps.setInt(4, order.getLotSize());
				ps.setString(5, "dummy");
				ps.setDouble(6, order.getPrice());
				ps.setString(7, order.getCurrencyFrom().name());
				ps.setString(8, order.getCurrencyTo().name());
				ps.setString(9, order.getStatus().name());
				ps.setDouble(10, order.getLimitPrice());
				return ps;
			}
		}, holder);
		order.setT_id(0);
		return "Success";
	}
}
