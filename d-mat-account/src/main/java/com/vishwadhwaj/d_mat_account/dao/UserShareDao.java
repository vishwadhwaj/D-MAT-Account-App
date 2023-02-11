package com.vishwadhwaj.d_mat_account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.vishwadhwaj.d_mat_account.db.Db;
import com.vishwadhwaj.d_mat_account.entities.Account;
import com.vishwadhwaj.d_mat_account.entities.Share;
import com.vishwadhwaj.d_mat_account.entities.UserShare;

public class UserShareDao {

	Db db;

	public UserShareDao() {
		db = Db.getInstance();
	}

	public UserShare getUserShare(int id) {
		Account accountFromDb = new Account();
		UserShare userShareFromDb = new UserShare();
		Connection connection = db.createConnection();
		try {
			String sqlForInsertedRecord = "select * from account where id=?";
			PreparedStatement preparedStatementForInsertedRecord = connection.prepareStatement(sqlForInsertedRecord);
			preparedStatementForInsertedRecord.setInt(1, id);
			ResultSet resultSetForInsertedRecord = preparedStatementForInsertedRecord.executeQuery();
			resultSetForInsertedRecord.next();
			accountFromDb.setId(resultSetForInsertedRecord.getInt("id"));
			accountFromDb.setName(resultSetForInsertedRecord.getString("name"));
			accountFromDb.setAmount(resultSetForInsertedRecord.getInt("amount"));
			accountFromDb.setAccountNumber(resultSetForInsertedRecord.getInt("number"));
			String sqlForNumberOfShares = "select * from user_share where user_id=?";
			PreparedStatement preparedStatementForNumberOfShares = connection.prepareStatement(sqlForNumberOfShares);
			preparedStatementForNumberOfShares.setInt(1, id);
			ResultSet resultSetForNumberOfShares = preparedStatementForNumberOfShares.executeQuery();
			resultSetForNumberOfShares.next();
			userShareFromDb.setNumberOfShare(resultSetForNumberOfShares.getInt("number_of_shares"));
			userShareFromDb.setAccount(accountFromDb);
			userShareFromDb.setId(resultSetForNumberOfShares.getInt("id"));
			int shareId = resultSetForNumberOfShares.getInt("share_id");
			String sqlForShares = "select * from share where id=?";
			PreparedStatement preparedStatementForShares = connection.prepareStatement(sqlForShares);
			preparedStatementForShares.setInt(1, shareId);
			ResultSet resultSetForShares = preparedStatementForShares.executeQuery();
			resultSetForShares.next();
			Share share = new Share();
			share.setId(resultSetForShares.getInt("id"));
			share.setName(resultSetForShares.getString("name"));
			share.setValue(resultSetForShares.getInt("value"));
			userShareFromDb.setShare(share);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userShareFromDb;
	}
}
