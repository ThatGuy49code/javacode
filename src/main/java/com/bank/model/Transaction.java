package com.bank.model;

public class Transaction {
	private int transactionId;	
	private int repicientId;
	private int senderId;
	private String senderFirstName;
	private String senderLatName;
	private String senderEmail;
	private String repicientEmail;
	private String senderAccountNumber;
	private double transactionAmount;
	private TransactionStatus transactionStatus;
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public int getRepicientId() {
		return repicientId;
	}
	public void setRepicientId(int repicientId) {
		this.repicientId = repicientId;
	}

	public int getSenderId() {
		return senderId;
	}
	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}
	public String getSenderAccountNumber() {
		return senderAccountNumber;
	}
	public void setSenderAccountNumber(String senderAccountNumber) {
		this.senderAccountNumber = senderAccountNumber;
	}
		
	public String getRepicientEmail() {
		return repicientEmail;
	}
	public void setRepicientEmail(String repicientEmail) {
		this.repicientEmail = repicientEmail;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getTransactionStatus() {
		return transactionStatus.toString();
	}
	public void setTransactionStatus(String transactionStatus) {		
		for(TransactionStatus ts : TransactionStatus.values()) {
			if(ts.toString().equals(transactionStatus)) {
				this.transactionStatus = ts;
			}
		}
	}
	public String getSenderFirstName() {
		return senderFirstName;
	}
	public void setSenderFirstName(String senderFirstName) {
		this.senderFirstName = senderFirstName;
	}
	public String getSenderLatName() {
		return senderLatName;
	}
	public void setSenderLatName(String senderLatName) {
		this.senderLatName = senderLatName;
	}
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", senderFirstName=" + senderFirstName
				+ ", senderLastName=" + senderLatName + ", senderEmail=" + senderEmail + ", transactionAmount="
				+ transactionAmount + ", transactionStatus=" + transactionStatus + "]\n";
	}

}


