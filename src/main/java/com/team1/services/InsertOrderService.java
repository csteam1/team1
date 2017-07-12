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
				+ "transaction(user_id, transaction_id, side, size, "
				+ "time, price, currency_from, currency_to, "
				+ "transaction_state, limit_price) "
				+ "values(?,?,?,?,?,?,?,?,?,?)";
		
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(0, order.getU_id());
				ps.setInt(1, 0);
				ps.setString(2, order.getSide().name());
				ps.setInt(3, order.getLotSize());
				ps.setString(4, "dummy");
				ps.setDouble(5, order.getPrice());
				ps.setString(6, order.getCurrencyFrom().name());
				ps.setString(7, order.getCurrencyTo().name());
				ps.setString(8, order.getStatus().name());
				ps.setDouble(9, order.getLimitPrice());
				return ps;
			}
		}, holder);
		order.setT_id(0);
		return "Success";
	}
}
