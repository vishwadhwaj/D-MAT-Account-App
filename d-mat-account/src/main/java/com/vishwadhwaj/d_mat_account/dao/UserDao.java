package com.vishwadhwaj.d_mat_account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.vishwadhwaj.d_mat_account.db.Db;
import com.vishwadhwaj.d_mat_account.entities.Account;
import com.vishwadhwaj.d_mat_account.exceptions.DuplicateEntryException;

public class UserDao implements Dao<Account> {

	private Db db;

	public UserDao() {
		db = Db.getInstance();
	}

	@Override
	public boolean create(Account account) {
		Connection connection = db.createConnection();
		int i=0;
		try {
			String sqlForDuplicate = "select * from account where number=?";
			PreparedStatement preparedStatementForDuplicate = connection.prepareStatement(sqlForDuplicate);
			preparedStatementForDuplicate.setInt(1, account.getAccountNumber());
			ResultSet resultSet = preparedStatementForDuplicate.executeQuery();
			if (resultSet.isBeforeFirst()) {
				throw new DuplicateEntryException();
			}
			String sql = "insert into account (name,number,amount) values (?,?,?)";
		

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, account.getName());
			preparedStatement.setInt(2, account.getAccountNumber());
			preparedStatement.setInt(3, account.getAmount());
			i = preparedStatement.executeUpdate();

		} catch (DuplicateEntryException e) {
			System.out.println("Account number already exists");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i > 0 ? true : false;
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
