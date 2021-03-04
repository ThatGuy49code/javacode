package com.bank.services;

import com.bank.model.Transaction;
import com.bank.model.User;

public interface CustomerService {
	public void applyNewAccountWithBalance(User customer, double balance);

	//public boolean transferMoney(String email, double amount);
	public boolean acceptMoneyTransfer(Transaction transaction);

	}


