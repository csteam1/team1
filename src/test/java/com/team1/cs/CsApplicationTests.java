package com.team1.cs;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.team1.data.Currency;
import com.team1.data.Order;
import com.team1.data.Side;
import com.team1.data.Status;
import com.team1.data.Type;
import com.team1.data.User;
import com.team1.services.InsertOrderService;
import com.team1.services.UserRepository;
import com.team1.validation.Validation;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CsApplicationTests extends TestCase{
	@Autowired
	UserRepository repo;
	
	@Autowired
	InsertOrderService ios;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	 @Test
	    public void testFindById()
	    {
		 User user = repo.getUserDetails(1);
		 	assertEquals("User1", user.getName());
	        assertEquals("user1@gmail.com", user.getEmail());
	        return;
	    }
	 
	 @Test
	 public void testRegister(){
		 User user = new User();
		 user.setEmail("surabhi@gmail.com");
		 user.setName("Surabhi"); 
		 repo.register(user);
		 int id = user.getId();
		 assertNotNull(id);
		 assertEquals(3, repo.getAllUsers().size());
	     User newUser = repo.getUserDetails(id); 
		 assertEquals("Surabhi", newUser.getName());
		 assertEquals("surabhi@gmail.com", newUser.getEmail());
		 return;
	 }
	 
	 @Test
	    public void TestGetUserTransactionsByID()
	    {
			
		 assertEquals(2, repo.getUserTransactions(1).size());
		 return;
				 		
	    }
	 
	 @Test
	 public void testInsertOrder(){
		 
		 Order order = new Order();
		 order.setCurrencyFrom(Currency.EUR);
		 order.setCurrencyTo(Currency.GBP);
		 order.setDateOfTransaction("10");
		 order.setLimitPrice(3.24);
		 order.setLotSize(100);
		 order.setPrice(3.1);
		 order.setSide(Side.BUY);
		 order.setStatus(Status.NOT_COMPLETED);
		 order.setT_id(10);
		 order.setU_id(2);
		 order.setTypeOrder(Type.LO);
		
		 ios.insertOrder(order);
		
		 assertEquals(4, repo.getAllTransactions().size());
		 List<Order> order_list = repo.getUserTransactions(2); 
		 assertEquals(100, order_list.get(1).getLotSize());
		 return;
		
	 }
	 
@Test
public void testValidateOrder()
{
	Order order = new Order();
	order.setCurrencyFrom(Currency.EUR);
	 order.setCurrencyTo(Currency.GBP);
	 order.setDateOfTransaction("10");
	 order.setLimitPrice(3.24);
	 order.setLotSize(400);
	 order.setPrice(3.1);
	 order.setSide(Side.BUY);
	 order.setStatus(Status.NOT_COMPLETED);
	 order.setT_id(10);
	 order.setU_id(2);
	 order.setTypeOrder(Type.LO);
	 
	Validation a = new Validation();
	assertEquals("success", a.validateOrder(order, jdbcTemplate)); 
	return;
}
 
	/* @Test
	 public void checkLotSize(){
		 List<Map<String, Object>> user_tran = repo.getAllTransactions();
		 for(Map<String, Object> child : user_tran){
			 assertThat((int)child.get("lotSize"), Matchers.greaterThan(0));
				if (((int)child.get("lotSize")) < 0){
					 System.out.println(((int)child.get("lotSize")));
			 }
		 }
		 return;
	 }*/
	 
	 /*@Test
	 public void checkPrice(){
		 List<Map<String, Object>> user_tran = repo.getAllTransactions();
		 for(Map<String, Object> child : user_tran){
		 if(child.get("STATUS")=="Not Executed"){
			 assertEquals(0, (int)child.get("LIMITPRICE"));
		 }
		 else{
			 assertThat((double)child.get("LIMITPRICE"), Matchers.greaterThan(0.0));
		 }
	 }
	 }
		 @Test
		 public void checkCurrencyPair(){
			 List<Map<String, Object>> user_tran = repo.getAllTransactions();
			 for(Map<String, Object> child : user_tran){
				 assertEquals(child.get("CURRENCYFROM"), "USD");
		 }
	 
	 @Test
	 public void checkLimitOrderPrice(){
			 assertThat(3, Matchers.greaterThan(0));
			 return;
	 }
	 
	 @Test
	 public void checkMarketOrderPrice(){
		 assertEquals(0, newUser.getEmail());
			 return;
	 }*/
}
	 
