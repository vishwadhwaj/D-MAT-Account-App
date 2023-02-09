package com.vishwadhwaj.d_mat_account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.vishwadhwaj.d_mat_account.db.Db;
import com.vishwadhwaj.d_mat_account.entities.Account;

public class UserDao implements Dao<Account> {

	private Db db;
	public UserDao() {
		db=Db.getInstance();
	}
	@Override
	public boolean create(Account account) {
		Connection connection=db.createConnection();
		String sql="insert into account (name,number,amount) values (?,?,?)";
		int i=0;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, account.getName());
			preparedStatement.setInt(2, account.getAccountNumber());
			preparedStatement.setInt(3, account.getAmount());
			i=preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i>0?true:false;
	}

	@Override
	public boolean update(Account account) {
		return true;
	}

	@Override
	public boolean delete(Account object) {
		return true;
	}

	@Override
	public List<Account> read() {
		// TODO Auto-generated method stub
		return null;
	}

}
