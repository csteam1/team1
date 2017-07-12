package com.team1.cs;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.runner.RunWith;
import junitparams.Parameters;
import junitparams.JUnitParamsRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.team1.data.User;
import com.team1.services.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CsApplicationTests {
	@Autowired
	CsApplicationTests csat;
	UserRepository repo;

	@Before
	public void checkConnection(){
		
	}
	
	 @Test
	 @Parameters({"1"})
	    public void testFindById(int id)
	    {
		 User user = new User();
		 	//assertEquals("User1", user.getUser());
	        //assertEquals("user1@gmail.com", user.getMail());
	        return;
	    }
	 

}
