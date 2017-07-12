package com.team1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.team1.data.User;
import com.team1.services.UserRepository;


@RestController
public class WebController {

	@Autowired
	UserRepository repo;
	@Autowired
	
	
	@RequestMapping(value = "/register", method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public String registerUser(@RequestBody User user){
		return repo.register(user);
		
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