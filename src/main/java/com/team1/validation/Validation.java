package com.team1.validation;

import com.team1.data.Currency;
import com.team1.data.Order;
import com.team1.data.Side;
import com.team1.data.Status;
import com.team1.data.Type;

public class Validation {
	
	private Currency currencyFrom, currencyTo;
    private String side;
    private int u_id, t_id;
    private String date;
    private double price;
    private Type type;
    private int lotSize;
    private Status status;
	
	public String validateOrder (Order order)
	{
		//Cheking userID from database pending
		
		currencyFrom = order.getCurrencyFrom();
		currencyTo = order.getCurrencyTo();
		side = order.getSide().name();
		
	    
		
		return "false";
		
	}

}
