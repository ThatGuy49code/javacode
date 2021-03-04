package com.bank.services;

import java.sql.SQLException;
import java.util.List;

import com.bank.dao.UserPostgresDAO;
import com.bank.exceptions.InternalErrorException;
import com.bank.exceptions.UserNotFoundException;
import com.bank.model.CheckingAccount;
import com.bank.model.SavingAccount;
import com.bank.model.User;

public class EmployeeImplementation implements EmployeeService, UserService {
	
	
	//private UserImplementationDAO uid;
	private UserPostgresDAO upd;
	
	public EmployeeImplementation(UserPostgresDAO upd) {
		   this.upd = upd;
	}
	//
	public User userLogIn(String email, String password, boolean isCustomer) throws UserNotFoundException, InternalErrorException, SQLException {
		User user = upd.findOne(email, password, isCustomer);	
		if(user!= null) {
		   System.out.println("Welcome, "+ user.getFirstName()+" "+user.getLastName());
		   return user;
		}
		else {
			System.out.println(" User is not found");
		}
		return null;

	}

	public boolean setCustomerStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	public void viewCustomerAccount(User customer) {
		// TODO Auto-generated method stub

	}

	public List<User> viewListPendingUser() throws InternalErrorException, SQLException {
		List<User> list = upd.findPendingCustomer();
		if(list.size() != 0) {
			return list;
		}
		
		return null;
	}
	
	public boolean approveCustomer(User user) {
		
		  CheckingAccount newChequingAccount = new CheckingAccount();
		  SavingAccount	newSavingAccount = new SavingAccount();
		  newChequingAccount.setAccountNumber(generateAccountNumber());
		  newChequingAccount.setBalance(user.getInitialDeposit());
		  newSavingAccount.setAccountNumber(generateAccountNumber());
		  	  
		  return upd.acceptOne(user,newChequingAccount,newSavingAccount);
	}
	
	public boolean rejectCustomer(User user) {
		  return upd.rejectOne(user);
	}
	
	public String generateAccountNumber() {
		int i =1;
		StringBuffer accountNumber = new StringBuffer("");
		while (true) {
			if(i==7) {
				break;
			}		
			int a = (int) (Math.random() * 10);
			
			accountNumber.append(a);
			i++;
		}
		return accountNumber.toString();
	}


    //Employee can view customer account using their email
	public List<Object> viewCustomerInfo(User customer) {
		List<Object> listInfo;
		try {
			listInfo = upd.findCustomerInfoByEmail(customer);	
			if(listInfo.size() != 0) {
				return listInfo;
			}
		} catch (InternalErrorException e) {
			e.printStackTrace();
			System.out.println(e);
		} 

		return null;		
	}

}