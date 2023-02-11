package com.vishwadhwaj.d_mat_account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.vishwadhwaj.d_mat_account.db.Db;
import com.vishwadhwaj.d_mat_account.entities.Account;
import com.vishwadhwaj.d_mat_account.entities.Share;
import com.vishwadhwaj.d_mat_account.entities.UserShare;
import com.vishwadhwaj.d_mat_account.exceptions.DuplicateEntryException;

public class UserDao implements Dao<Account> {

	private Db db;

	public UserDao() {
		db = Db.getInstance();
	}

	@Override
	public Account save(Account account) {
		Connection connection = db.createConnection();
		boolean registrationStatus=false;
		try {
			String sqlForDuplicate = "select * from account where number=?";
			PreparedStatement preparedStatementForDuplicate = connection.prepareStatement(sqlForDuplicate);
			preparedStatementForDuplicate.setInt(1, account.getAccountNumber());
			ResultSet resultSet = preparedStatementForDuplicate.executeQuery();
			if (resultSet.isBeforeFirst()) {
				throw new DuplicateEntryException();
			}
			String sql = "insert into account (name,number,amount) values (?,?,?)";

			
			
		} catch (DuplicateEntryException e) {
			System.out.println("Account number already exists");
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
		return accountFromDb;
	}

	@Override
	public boolean findById(Integer accountNumber) {
		Connection connection = db.createConnection();
		boolean loginStatus=false;
		String sql = "select * from account where number=?";
		Account accountFromDb=null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			
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
		return accountFromDb;
	}

	

	@Override
	public List<Account> findAll() {
		return null;
		// TODO Auto-generated method stub

	}

}
