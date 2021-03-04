package com.bank.dao;

import java.sql.SQLException;
import java.util.List;

import com.bank.exceptions.InternalErrorException;
import com.bank.exceptions.UserNotFoundException;
import com.bank.model.CheckingAccount;
import com.bank.model.SavingAccount;
import com.bank.model.User;

public interface UserDAO {
	public User createCustomerAccount(User user, double balance);
	   public User findOne(String email, String password, boolean isCustomer) 
			   throws UserNotFoundException, InternalErrorException, SQLException;
    
	   public List<User> findPendingCustomer()
	   throws InternalErrorException, SQLException;
	   public List<User> findAll();
    public boolean acceptOne(User user,CheckingAccount ca,SavingAccount sa);
    public boolean rejectOne(User user); 
    List<Object> findCustomerInfoByEmail(User user) throws InternalErrorException;
    
}



