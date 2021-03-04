package com.bank.exceptions;

public class InvalidTransaction extends Exception{
	private static final long serialVerison = 1L;
	
	public InvalidTransaction() {
		super("This transaction is invalid");
	}
	

}
