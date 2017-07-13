package com.team1.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.team1.data.Order;
import com.team1.data.Status;
import com.team1.data.Type;

@Component
public class InsertOrderService {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public String insertOrder(Order order){
		//final String checkSql = "insert into transaction() ";
		final String sql = "insert into "
				+ "transaction(u_id, t_id, typeOrder, side, "
				+ "lotSize, dateOfTransaction, price, currencyFrom, "
				+ "currencyTo, status, limitPrice) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?)";
		
		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setInt(1, order.getU_id());
				
				Random rand = new Random();
				int transId = rand.nextInt(32767) + 1000;
				ps.setInt(2, transId);
				order.setT_id(transId);
				
				ps.setString(3, order.getTypeOrder().name());
				
				ps.setString(4, order.getSide().name());
				ps.setInt(5, order.getLotSize());
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = new Date();
				ps.setString(6, dateFormat.format(date));
				
				ps.setDouble(7, 0);
				ps.setString(8, order.getCurrencyFrom().name());
				ps.setString(9, order.getCurrencyTo().name());
				
				if(order.getTypeOrder() == Type.MO)
					{
					ps.setDouble(11, order.getMarketPrice());
					order.addOrderInHistoryTable(order.getCurrencyFrom(),order.getCurrencyTo(),order.getPrice(),order.getLotSize(),order.getDateOfTransaction());
					ps.setString(10, Status.COMPLETED.name());
					}
				else{
					ps.setString(10, Status.NOT_COMPLETED.name());
					order.processLimitOrder();
					ps.setDouble(11, order.getLimitPrice());
				}
					
				return ps;
			}

		}, holder);
		
		
		return "Success";
	}

	
}
