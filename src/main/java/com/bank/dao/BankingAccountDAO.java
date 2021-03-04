package com.bank.dao;

import com.bank.model.BankingAccount;
import com.bank.model.User;

public interface BankingAccountDAO { 
	public BankingAccount saveOne(BankingAccount newAccount);
public BankingAccount findOne(User userId);
public boolean updateBalance(int bankId,BankingAccount existingAccount);
public void updateAccount(BankingAccount account);


}


