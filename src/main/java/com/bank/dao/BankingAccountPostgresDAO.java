package com.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.bank.model.BankingAccount;
import com.bank.model.CheckingAccount;
import com.bank.model.SavingAccount;
import com.bank.model.User;
import com.bank.ui.ConnectionFactory;

public class BankingAccountPostgresDAO implements BankingAccountDAO {
	
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();
	
	
	public BankingAccount saveOne(BankingAccount newAccount) {
		// TODO Auto-generated method stub
		return null;
	}

	public BankingAccount findOne(User userId) {
		
		return null;
	}
	public boolean updateBalance(int bankId,BankingAccount existingAccount) {
		Connection conn = cf.getConnection();
		String ca_account_number = null,sa_account_number=null;
		String sql =null;
		double newBalance = 0;

		
		try {
			if(existingAccount instanceof CheckingAccount) {
				sql = "update chequing_account set ca_balance = ? "
						+ "where ca_account_number = ? and bank_id = ?;";
				ca_account_number = ((CheckingAccount) existingAccount).getAccountNumber();
				newBalance = ((CheckingAccount) existingAccount).getBalance();
			} else if (existingAccount instanceof SavingAccount){
				sql = "update saving_account set sa_balance = ? "
						+ "where sa_account_number = ? "
						+ "and bank_id = ?;";
				sa_account_number = ((SavingAccount) existingAccount).getAccountNumber();
				newBalance = ((SavingAccount) existingAccount).getBalance();
			}
			if(ca_account_number != null ) {
				PreparedStatement updateBalance = conn.prepareStatement(sql);
				updateBalance.setDouble(1, newBalance);
				updateBalance.setString(2, ca_account_number);
				updateBalance.setInt(3, bankId);
								
				updateBalance.execute();
				return true;
			} else if(sa_account_number != null) {
				PreparedStatement updateBalance = conn.prepareStatement(sql);
				updateBalance.setDouble(1, newBalance);
				updateBalance.setString(2, sa_account_number);
				updateBalance.setInt(3, bankId);
								
				updateBalance.execute();
				return true;
			}

		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			 try {
					cf.releaseConnection(conn);
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
		}
		
		return false;
	}

	public void updateAccount(BankingAccount account) {
		// TODO Auto-generated method stub

	}

}
