package com.bank.main;

import java.sql.SQLException;
import java.util.InputMismatchException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.bank.dao.BankingAccountPostgresDAO;
import com.bank.dao.TransactionPosgresDAO;
import com.bank.dao.UserPostgresDAO;
import com.bank.exceptions.InternalErrorException;
import com.bank.exceptions.UserNotFoundException;
import com.bank.model.User;
import com.bank.services.CustomerImplementation;
import com.bank.services.EmployeeImplementation;
import com.bank.ui.UserMenu;


public class BankMain {
	String url = "jdbc:postgresql://localhost:5432/postgres";
	String username = "postgres";
	String password = "guy321";
    
	public static Logger p0174Logger = LogManager.getLogger("com.revature.p0174");
	public static void main(String[] args) 
			throws UserNotFoundException, InternalErrorException, 
SQLException, InputMismatchException {
		
		UserPostgresDAO uid = new UserPostgresDAO();
		BankingAccountPostgresDAO bad = new BankingAccountPostgresDAO();
		TransactionPosgresDAO tpd = new TransactionPosgresDAO();
		CustomerImplementation csi = new CustomerImplementation(uid,bad,tpd);
		EmployeeImplementation esi = new EmployeeImplementation(uid);
		UserMenu userMenu = new UserMenu(csi,esi);

		p0174Logger.info("Server has started!");
		System.out.println("Welcome to the bank 101!\n");
		while(true) {
							
			userMenu.manageUserAccountInput();
			System.out.println("\n");
		}
		

	    
	}

}

