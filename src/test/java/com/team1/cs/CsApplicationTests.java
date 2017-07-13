package com.team1.cs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.team1.data.Order;
import com.team1.data.User;
import com.team1.services.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CsApplicationTests {
	@Autowired
	UserRepository repo;
	
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
		 assertEquals(2, repo.getAllUsers().size());
	     User newUser = repo.getUserDetails(id); 
		 assertEquals("Surabhi", newUser.getName());
		 assertEquals("surabhi@gmail.com", newUser.getEmail());
		 return;
	 }
	 
	 /*@Test
	    public void TestGetUserTransactionsByID()
	    {

		List<Order> orders =  repo.getUserTransactions(5);
		 	
	        return;
		
	    }*/
	 
	 
	/* @Test
	 public void testGetAllUsers(){
		 List<User> users = new ArrayList<User>();
		User user = new User();
		user.setEmail("user1@gmail.com");
		 user.setName("User1"); 
		 user.setId(1);
		 users.add(user);
		 assertEquals(users.get(0),repo.getAllUsers());
		 return;
	 }*/
	 
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
	 }
	 
	 @Test
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
		 }*/
	 /*
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
	 
