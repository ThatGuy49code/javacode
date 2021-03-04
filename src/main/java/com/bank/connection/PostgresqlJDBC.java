package com.bank.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.logging.Logger;
import org.postgresql.Driver;

public class PostgresqlJDBC {
	String url = "jdbc:postgresql://localhost:5432/postgres";
	String username = "postgres";
	String password = "guy321";
	//private static Logger log = Logger.getLogger(ConnectionUtil.class);

	public static Connection getConnection() throws SQLException{
		Driver postgresDriver = new Driver();
		
		DriverManager.registerDriver(postgresDriver);
		
		String url = System.getenv("db_url");
		String username = System.getenv("db_username");
		String password = System.getenv("db_password");
		
		Connection con = DriverManager.getConnection(url,username,password);
		System.out.println(con +"Connected!");
		
		return con;
	}// catch (SQLException e) {
		//log.error("Application unable to establish connection! Exception message is: " + e.getMessage());
		//throw new RuntimeException("Connection unable to be established");
}
	

