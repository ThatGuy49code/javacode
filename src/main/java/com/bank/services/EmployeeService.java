package com.bank.services;

import java.sql.SQLException;
import java.util.List;

import com.bank.exceptions.InternalErrorException;
import com.bank.model.User;

public interface EmployeeService {
	public List<User> viewListPendingUser() throws InternalErrorException, SQLException;
	public boolean setCustomerStatus();
	public void viewCustomerAccount(User customer);
}

