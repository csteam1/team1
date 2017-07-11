package com.team1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	@Autowired
	
	
	@RequestMapping("/register")
	public String registerUser ()
	{
		return "User Created";
	}
	
	@RequestMapping("/user/all")
	public String detailsOfAllUsers ()
	{
		return "Detail of users";
	}
	
	@RequestMapping("/user/{userId}")
	public String detailOfSingleUser(@PathVariable int userId)
	{
		return "User Created userId = " + userId;
	}
	
	@RequestMapping("/order")
	public String placeOrder ()
	{
		return "Order placed";
	}
	
	@RequestMapping("/cancelOrder")
	public String cancelOrder ()
	{
		return "Cancel order requested";
	}
	
	
}
