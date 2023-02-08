package com.vishwadhwaj.d_mat_account.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {

	private static Db db=new Db();
	
	private Db() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Db getInstance() {
		return db;
	}
	
	public Connection createConnection() {
		String url="jdbc:postgresql://localhost:5432/d_mat_account";
		String userName="postgres";
		String password="compaq";
		Connection connection=null;
		try {
			connection=DriverManager.getConnection(url,userName,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	
	
}
