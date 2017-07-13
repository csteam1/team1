package com.team1.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.team1.data.Order;
import com.team1.data.User;
import com.team1.services.InsertOrderService;
import com.team1.services.UserRepository;


@RestController
public class WebController {

	@Autowired
	UserRepository repo;
	
	@Autowired
	InsertOrderService ios;
	
	
	@RequestMapping(value = "/register", method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public String registerUser(@RequestBody User user){
//		new User(user.getUser(), user.getMail());
//		System.out.println(user.getEmail() + user.getName());
		return repo.register(user);
		
	}
	
	@RequestMapping("/user/all")
	public List<Order>  detailsOfAllUsers ()
	{
		return repo.getAllTransactions();
	}
	
	@RequestMapping("/user/{userId}")
	public List<Order> transactionsOfUser(@PathVariable int userId)
	{
		return repo.getUserTransactions(userId);
	}
	
	
	public User detailsOfUser(int userId){
		return repo.getUserDetails(userId);
	}
	
	
	@RequestMapping(value = "/order", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public String placeOrder ( @RequestBody Order order)
	{
		return ios.insertOrder(order);
	}
	
	@RequestMapping("/cancelOrder")
	public String cancelOrder ()
	{
		return "Cancel order requested";
	}
	
	
}
