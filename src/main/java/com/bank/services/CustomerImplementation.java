package com.bank.services;

import java.sql.SQLException;
import java.util.List;

import com.bank.dao.BankingAccountDAO;
import com.bank.dao.TransactionPosgresDAO;
import com.bank.dao.UserPostgresDAO;
import com.bank.exceptions.InternalErrorException;
import com.bank.exceptions.UserNotFoundException;
import com.bank.main.BankMain;
import com.bank.model.User;
import com.bank.model.BankingAccount;
import com.bank.model.CheckingAccount;
import com.bank.model.Transaction;
import com.bank.model.SavingAccount;


public class CustomerImplementation implements CustomerService,UserService{
    
    //private UserImplementationDAO uid;
    private UserPostgresDAO upd;
    private BankingAccountDAO bad;
    private TransactionPosgresDAO tpd;
	    
	public CustomerImplementation(UserPostgresDAO upd,BankingAccountDAO bad,TransactionPosgresDAO tpd) {
		super();
		this.upd = upd;
		this.bad = bad;
		this.tpd = tpd;
	}

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


	public void applyNewAccountWithBalance(User customer, double balance) {
		  User user = upd.createCustomerAccount(customer, balance);
		  if(user!= null) {
			   System.out.println("Welcome, "+ user.getFirstName()+" "+user.getLastName());
			   System.out.println("Thank you for registering new account! Your account is reviewed!");
			}
			else {
				System.out.println("Creation is not successful!");
			}
	}
	
	public boolean deposit(int bankId,BankingAccount existingAccount,double amount) {
		if(existingAccount instanceof CheckingAccount) {
			((CheckingAccount)existingAccount).setBalance(amount);
		} else if (existingAccount instanceof SavingAccount){
			((SavingAccount)existingAccount).setBalance(amount);
		}
		BankMain.p0174Logger.info("Banking Account with Id: "+bankId+" just got deposit: "+amount+"$!");
		return bad.updateBalance(bankId ,existingAccount);
		
	}


	public boolean withdraw(int bankId,BankingAccount existingAccount,double amount) {
		if(existingAccount instanceof CheckingAccount) {
			((CheckingAccount)existingAccount).withDraw(amount);
		} else if (existingAccount instanceof SavingAccount){
			((SavingAccount)existingAccount).withDraw(amount);
		}
		return bad.updateBalance(bankId ,existingAccount);
	}
	
	
	public boolean transferMoney(String email, int userId,BankingAccount existingAccount,double amount) {
		Transaction t = new Transaction();
		t.setRepicientEmail(email);
		
		if(existingAccount instanceof CheckingAccount) {
			t.setSenderAccountNumber(((CheckingAccount)existingAccount).getAccountNumber());
		} else if (existingAccount instanceof SavingAccount){
			t.setSenderAccountNumber(((SavingAccount)existingAccount).getAccountNumber());			
		}
		t.setSenderId(userId);
		t.setTransactionAmount(amount);
		Transaction newTransaction = tpd.saveOne(t);
		BankMain.p0174Logger.info("User with Id: "+userId+"just send "+amount+"$!");
		return newTransaction != null ? true : false;
	}

	public boolean acceptMoneyTransfer(Transaction transaction) {
		
		return tpd.updateOne(transaction);
	}
	
	public List<Transaction> findRepicient(int repicientId) {
		
		return tpd.findRepicient(repicientId);

	}
    
	//Customer Login
	public User userLogIn(String email, String password, boolean isCustomer) throws UserNotFoundException, InternalErrorException, SQLException {
		User user = upd.findOne(email, password, isCustomer);	
		BankMain.p0174Logger.info(user.getFirstName()+" "+user.getLastName()+" logged in!");
		if(user!= null) {
		   //System.out.println("Welcome, "+ user.getFirstName()+" "+user.getLastName());
		   return user;
		}

		return null;
	}
   
}

