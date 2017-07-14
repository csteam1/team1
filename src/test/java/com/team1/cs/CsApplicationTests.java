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
import com.team1.services.CancelOrder;
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
	
	@Autowired
	CancelOrder co;
	
	
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
		 assertEquals(4, repo.getAllUsers().size());
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
	    public void TestgetUserOpenOrdersTransactions()
	    {	
		 assertEquals(1,repo.getUserOpenOrdersTransactions(2).size());
		 return;		 		
	    }
	 
	 @Test
	    public void TestgetUserClosedOrdersTransactions()
	    {	
		 assertEquals(2,repo.getUserClosedOrdersTransactions(1).size());
		 return;		 		
	    }
	 
	 @Test
	    public void cancelOrder ()
		{
		 assertEquals("Order cannot be canceled. It is already executed ",co.cancelOrder("5",jdbcTemplate,1));
		 assertEquals("Order is already cancelled",co.cancelOrder("8",jdbcTemplate,3));
		 assertEquals("Order cancelled",co.cancelOrder("7",jdbcTemplate,2));
		 return;
			
		}
	 
	 @Test
	    public void testGetAllUserCompletedTransactions()
	    {	
		 assertEquals(2,repo.getAllUserCompletedTransactions(jdbcTemplate).size());
		 return;		 		
	    }
	 
	
	 @Test
	 public void testInsertOrder(){
		 
		 Order order = new Order();
		 order.setCurrencyFrom(Currency.EUR);
		 order.setCurrencyTo(Currency.GBP);
		 order.setLimitPrice(3.24);
		 order.setLotSize(100);
		 order.setSide(Side.BUY);
		 order.setU_id(2);
		 order.setTypeOrder(Type.LO);
		
		 ios.insertOrder(order);
		
		 assertEquals(5, repo.getAllTransactions().size());
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
	
}
	 
