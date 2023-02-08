package com.vishwadhwaj.d_mat_account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.vishwadhwaj.d_mat_account.db.Db;

public class App {
	public static void main(String[] args) {
		Db db = Db.getInstance();
		Connection connection = db.createConnection();
		PreparedStatement statement;
		try {
			statement = connection.prepareStatement("insert into share (name,value) values ('xyz','2000')");
			statement.executeUpdate();
			System.out.println("command executed succesfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
