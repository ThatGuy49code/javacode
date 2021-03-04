package com.bank.services;

import java.sql.SQLException;
import java.util.List;

import com.bank.exceptions.InternalErrorException;
import com.bank.exceptions.UserNotFoundException;
import com.bank.model.User;

public interface UserService {
	public User userLogIn(String email, String password, boolean isCustomer)
			throws UserNotFoundException, InternalErrorException, SQLException;

	public List<Object> viewCustomerInfo(User customer);
}
