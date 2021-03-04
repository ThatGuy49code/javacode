package com.bank.dao;

import java.sql.SQLException;

import com.bank.exceptions.UserNotFoundException;
import com.bank.model.CheckingAccount;
import com.bank.model.Customer;
import com.bank.model.SavingAccount;
import com.bank.model.User;

public class UserDAODebugger {
	public static void main(String[] args) throws UserNotFoundException, SQLException {
	// TODO Auto-generated method stub
	UserDAO ud = new UserPostgresDAO();
	User customer = new Customer();
	customer.setUserId(5);
	//customer.setInitialDeposit(174);
	  CheckingAccount newCheckingAccount = new CheckingAccount();
	  SavingAccount	newSavingAccount = new SavingAccount();
	  newCheckingAccount.setAccountNumber(generateAccountNumber());
	  newCheckingAccount.setBalance(customer.getInitialDeposit());
	  newSavingAccount.setAccountNumber(generateAccountNumber());
	  	  
	  //ud.acceptOne(customer,newChequingAccount,newSavingAccount);
	  System.out.println(ud.rejectOne(customer));
	//User customer = new Customer("Bob@gmail.com","12345","Bob","Frac");
	//System.out.println(ud.createCustomerAccount(customer,120));

}

public static String generateAccountNumber() {
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

}


