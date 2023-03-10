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
		int id = 0;
		try {
			String sqlForDuplicate = "select * from account where number=?";
			PreparedStatement preparedStatementForDuplicate = connection.prepareStatement(sqlForDuplicate);
			preparedStatementForDuplicate.setInt(1, account.getAccountNumber());
			ResultSet resultSet = preparedStatementForDuplicate.executeQuery();
			if (resultSet.isBeforeFirst()) {
				throw new DuplicateEntryException();
			}
			String sql = "insert into account (name,number,amount) values (?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, account.getName());
			preparedStatement.setInt(2, account.getAccountNumber());
			preparedStatement.setInt(3, account.getAmount());
			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			}

		} catch (DuplicateEntryException e) {
			System.out.println("Account number already exists");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public Account findById(Integer accountNumber) {
		Connection connection = db.createConnection();
		int id = 0;
		String sql = "select * from account where number=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, accountNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt("id");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public List<Account> findAll() {
		return null;
		// TODO Auto-generated method stub

	}

	@Override
	public int update(Account account) {
		Connection connection = db.createConnection();
		String sql = "update account set amount=? where id=?";
		int i = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, account.getAmount());
			preparedStatement.setInt(2, account.getId());
			i = preparedStatement.executeUpdate();
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
		return i;
	}

	@Override
	public Integer findByObject(Account object) {
		// TODO Auto-generated method stub
		return null;
	}

}
