package com.bank.ui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	  private static ConnectionFactory cf = new ConnectionFactory(1);
	  
	  public static ConnectionFactory getConnectionFactory() {		  
		  return cf;
	  }
	  
	  
	  private Connection[] conn;
	  
	  private ConnectionFactory(int numsOfConnections) {
		  String url = System.getenv("db_url");
		  String user = System.getenv("db_username");
		  String password = System.getenv("db_password");
		  
		  try {
			    this.conn = new Connection[numsOfConnections];
			    for(int i=0 ; i< this.conn.length;i++) {
			    	Connection conn = DriverManager.getConnection(url,user,password);
			    	this.conn[i] = conn;
			    }
			    
			    
		  } catch (SQLException e) {
			  e.printStackTrace();
		  }
	  }
	  
	  
	  public Connection getConnection() {
		    return this.conn[0];
	  }
	  
	  public void releaseConnection(Connection conn) throws SQLException {
		  //conn.close();
	  }
	  
}