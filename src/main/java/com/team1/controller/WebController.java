package com.team1.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.team1.data.Currency;
import com.team1.data.HistoryTable;
import com.team1.data.Order;
import com.team1.data.User;
import com.team1.services.CancelOrder;
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
	public List<Order>  detailsOfAllUsers (){
		return repo.getAllTransactions();
	}
	
	@RequestMapping("/user/{userId}/all")
	public List<Order> transactionsOfUser(@PathVariable int userId){
		//convert to ToString
		return repo.getUserTransactions(userId);
	}
	
	@RequestMapping("/user/{userId}/open")		
	public List<Order> openOrderOfUser(@PathVariable int userId){		
		//convert to Tostring		
		return repo.getUserOpenOrdersTransactions(userId);		
	}		
			
	@RequestMapping("/user/{userId}/close")		
	public List<Order> closedOrderOfUser(@PathVariable int userId){		
		//convert to Tostring		
		return repo.getUserClosedOrdersTransactions(userId);		
	}
	
	public User detailsOfUser(int userId){
		return repo.getUserDetails(userId);
	}
	
	@RequestMapping(value = "/order", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public String placeOrder ( @RequestBody Order order){
		Validation a = new Validation();
		String result = a.validateOrder(order, jdbcTemplate);
		if(result.equals("success"))
				return ios.insertOrder(order);
		else
			return result;
	}
	
	@Autowired
	CancelOrder co;

	@RequestMapping("/cancelOrder/{userId}/{orderId}")
	public String cancelOrder ( @PathVariable int userId, @PathVariable String orderId)
	{
		return co.cancelOrder(orderId, jdbcTemplate, userId);
	}
	
	@RequestMapping("/user/completed")		
	public List<Order> allCompletedTransactions(){		
		//convert to Tostring		
		return repo.getAllUserCompletedTransactions(jdbcTemplate);		
	}
	
	@RequestMapping(value = "/updateHistory", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public String updateHistoryTable (@RequestBody HistoryTable historyData)
	{
		 Order o = new Order();
		 
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		 Date date = new Date();
			
		 historyData.setDateOfTransaction(dateFormat.format(date));
		 o.addOrderInHistoryTable(jdbcTemplate, historyData.getCurrencyFrom(), historyData.getCurrencyTo(), historyData.getPrice(), historyData.getLotSize(), dateFormat.format(date));
		return "Updated in history table"; 
	}
}