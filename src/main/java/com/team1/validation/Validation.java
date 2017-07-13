package com.team1.validation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.team1.data.Currency;
import com.team1.data.Order;
import com.team1.data.Side;
import com.team1.data.Status;
import com.team1.data.Type;
import com.team1.data.User;

public class Validation {
	
	private String currencyFrom, currencyTo;
    private String side;
    private int uid, t_id;
    private String type;
    private int lotSize;
    private double limitPrice;
    
    
    @Autowired
	private JdbcTemplate jdbcTemplate;
    
	public String validateOrder (Order order)
	{
		//Cheking userID from database pending
				
		uid = order.getU_id();
		
				
		lotSize = order.getLotSize();
		if (lotSize < 1)
			return "Lot size should be greater than 1";
		
		
		if (type == Type.LO.name())
		{
			limitPrice = order.getLimitPrice();
			if (limitPrice < 1)
				return "Please enter limit price";
		} else {
			
		}
			
		return "success";
		
	}

}
