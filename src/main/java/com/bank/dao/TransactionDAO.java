package com.bank.dao;

import java.util.List;

import com.bank.model.Transaction;

public interface TransactionDAO {
public Transaction saveOne(Transaction transaction);	
	
	public boolean updateOne(Transaction transaction);
	
	public List<Transaction> findRepicient(int userId);
}


