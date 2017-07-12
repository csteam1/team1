package com.team1.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.team1.data.Currency;
import com.team1.data.Order;
import com.team1.data.Side;
import com.team1.data.Status;
import com.team1.data.Type;
import com.team1.data.User;

@Repository
public class UserRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public String register (final User user){
		System.out.println(user.getEmail() + user.getName());
		final String checkSql = "select * from users where email=?";
		try{
			User tempUser = jdbcTemplate.queryForObject(checkSql, new Object[]{user.getEmail()}, new UserRowMapper());
			
			return "User already exists!!!";
		
		}catch(EmptyResultDataAccessException e){
			
		
		
			final String sql = "insert into users(name,email) values(?,?)";
			KeyHolder holder = new GeneratedKeyHolder();
			jdbcTemplate.update(new PreparedStatementCreator(){
				@Override
				public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					
					ps.setString(1, user.getName());
					ps.setString(2, user.getEmail());
					return ps;
				}
			}, holder);
			
			int newUserId = holder.getKey().intValue();
			user.setId(newUserId);
			return "Your User ID: "+ user.getId();
		
		
		}
	}
	
	@Transactional(readOnly=true)
	public List<Map<String, Object>> getUserTransactions(int userId) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForList("select * from transaction where u_id=?", new Object[]{userId});
	}
	
	@Transactional(readOnly=true)
	public User getUserDetails(int userId) {
		// TODO Auto-generated method stub
		
		return jdbcTemplate.queryForObject("select * from users where id=?", new Object[]{userId}, new UserRowMapper());

	}
	
	@Transactional(readOnly=true)
	public List<Map<String, Object>> getAllTransactions() {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForList("select * from transaction");
	}

	
	
	
	
}


class UserRowMapper implements RowMapper<User>
{
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException{
		User user = new User();
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		return user;
	}
}


//class TransactionRowMapper implements RowMapper<Order>
//{
//	@Override
//	public Order mapRow(ResultSet rs, int rowNum) throws SQLException{
//		Order transaction = new Order();
//		transaction.setCurrencyFrom(Currency.valueOf(rs.getString("currencyFrom")));
//		transaction.setCurrencyTo(Currency.valueOf(rs.getString("currencyTo")));
//		transaction.setDateOfTransaction(rs.getString("dateOfTransaction"));
//		transaction.setLimitPrice(rs.getDouble("limitPrice"));
//		transaction.setLotSize(rs.getInt("lotSize"));
//		transaction.setPrice(rs.getDouble("price"));
//		transaction.setSide(Side.valueOf(rs.getString("side")));
//		transaction.setStatus(Status.valueOf(rs.getString("status")));
//		transaction.setT_id(rs.getInt("t_id"));
//		transaction.setTypeOrder(Type.valueOf(rs.getString("typeOrder")));
//		transaction.setU_id(rs.getInt("u_id"));
//		return transaction;
//	}
//}
