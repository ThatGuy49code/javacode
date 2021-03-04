package com.bank.dao;

import com.bank.exceptions.InternalErrorException;
import com.bank.model.BankingAccount;
import com.bank.model.CheckingAccount;
import com.bank.model.Transaction;

public class TransactionPostGresDAODebugger {
	public static void main(String[] args) throws InternalErrorException {
		// TODO Auto-generated method stub
		TransactionPosgresDAO tpd = new TransactionPosgresDAO();
		BankingAccount ba = new CheckingAccount();
		Transaction tsc = new Transaction();
//		tsc.setRepicientEmail("quang@gmail.com");
//		tsc.setSenderAccountNumber("654456");
//		tsc.setSenderId(2);
//		tsc.setTransactionAmount(250);
//	    tpd.saveOne(tsc);
		// tpd.findRepicient(4);
		System.out.println(tpd.findRepicient(4));
	}

}
