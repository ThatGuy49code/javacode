package com.bank.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.bank.exceptions.InternalErrorException;
import com.bank.exceptions.UserNotFoundException;
import com.bank.model.Customer;
import com.bank.model.User;

class UserDAOTest {
	
	private UserDAO cd;

	private UserDAO ud;
		
	@Before
	public void UserDAOImplementation() {
		ud = new UserPostgresDAO();
	}
	
	
	@Test
	public void logIn() throws UserNotFoundException, InternalErrorException, SQLException {
		//fail("Not yet implemented");
		
		User user = ud.findOne("charlie@gmail.com", "12345", true);
		User userTest = new Customer("charlie@gmail.com", "12345","Charlie","Strider");
		assertEquals(userTest.getEmail(), user.getEmail());
		assertEquals(userTest.getFirstName(),user.getFirstName());
		assertEquals(userTest.getLastName(),user.getLastName());
	
	}
	
}
