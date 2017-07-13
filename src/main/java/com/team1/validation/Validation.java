package com.team1.validation;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.team1.data.Order;
import com.team1.data.Type;
import com.team1.data.User;

public class Validation {
	
	private String currencyFrom, currencyTo;
    private String side;
    private int uid, t_id;
    private String type;
    private int lotSize;
    private double limitPrice;
    
	public String validateOrder (Order order, JdbcTemplate jdbcTemplate)
	{
		//Checking if user is registered or nor
		uid = order.getU_id();
		if(!isUserRegistered(uid, jdbcTemplate)){
			return "User doesn't exit. Please register";
		}
				
		//lot size should be an integer greater than 1
		lotSize = order.getLotSize();
		if (lotSize < 1)
			return "Lot size should be greater than 1";
		
		//limit order needs a limit price
		if (type == Type.LO.name())
		{
			limitPrice = order.getLimitPrice();
			if (limitPrice < 1)
				return "Please enter limit price";
		}
		
		return "success";
	}
	
	@Transactional(readOnly=true)
	public boolean isUserRegistered(int userId, JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		//jdbcTemplate1 = new JdbcTemplate();
		try{
			User user = jdbcTemplate.queryForObject("select * from users where id=?", new Object[]{userId}, new UserRowMapper());
			return true;
		}catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			return false;
		}
	}
}

class UserRowMapper implements RowMapper<User>{
	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException{
		User user = new User();
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		return user;
	}
}

