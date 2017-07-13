package com.team1.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.team1.data.Order;
import com.team1.data.User;
import com.team1.services.InsertOrderService;
import com.team1.services.UserRepository;
import com.team1.validation.Validation;


@RestController
public class WebController {

	@Autowired
	UserRepository repo;
	
	@Autowired
	InsertOrderService ios;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "/register", method=RequestMethod.POST, consumes= MediaType.APPLICATION_JSON_VALUE)
	public String registerUser(@RequestBody User user){
//		new User(user.getUser(), user.getMail());
//		System.out.println(user.getEmail() + user.getName());
		return repo.register(user);
		
	}
	
	@RequestMapping("/user/all")
	public List<Map<String, Object>>  detailsOfAllUsers ()
	{
		return repo.getAllTransactions();
	}
	
	@RequestMapping("/user/{userId}")
	public List<Map<String, Object>> transactionsOfUser(@PathVariable int userId)
	{
		return repo.getUserTransactions(userId);
	}
	
	public User detailsOfUser(int userId){
		return repo.getUserDetails(userId);
	}
	
	
	@RequestMapping(value = "/order", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public String placeOrder ( @RequestBody Order order)
	{
		Validation a = new Validation();
		String result = a.validateOrder(order, jdbcTemplate);
		if(result.equals("success"))
				return ios.insertOrder(order);
		else
			return result;
	}
	
	@RequestMapping("/cancelOrder")
	public String cancelOrder ()
	{
		return "Cancel order requested";
	}
	
	
}
