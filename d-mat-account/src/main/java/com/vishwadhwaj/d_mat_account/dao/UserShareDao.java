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

public class UserShareDao implements Dao<UserShare> {

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
			if (resultSetForInsertedRecord.next()) {
				accountFromDb.setId(resultSetForInsertedRecord.getInt("id"));
				accountFromDb.setName(resultSetForInsertedRecord.getString("name"));
				accountFromDb.setAmount(resultSetForInsertedRecord.getInt("amount"));
				accountFromDb.setAccountNumber(resultSetForInsertedRecord.getInt("number"));
			}
			userShareFromDb.setAccount(accountFromDb);
			String sqlForNumberOfShares = "select * from user_share where user_id=?";
			PreparedStatement preparedStatementForNumberOfShares = connection.prepareStatement(sqlForNumberOfShares);
			preparedStatementForNumberOfShares.setInt(1, id);
			ResultSet resultSetForNumberOfShares = preparedStatementForNumberOfShares.executeQuery();
			if (resultSetForNumberOfShares.next()) {
				userShareFromDb.setNumberOfShare(resultSetForNumberOfShares.getInt("number_of_shares"));
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
			}
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

	@Override
	public Integer save(UserShare userShare) {
		Connection connection = db.createConnection();
		String sql = "insert into user_share (share_id,number_of_shares,user_id) values (?,?,?)";
		int id = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, userShare.getShare().getId());
			preparedStatement.setInt(2, userShare.getNumberOfShare());
			preparedStatement.setInt(3, userShare.getAccount().getId());
			preparedStatement.executeUpdate();
			ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
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
	public Integer findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserShare> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(UserShare userShare) {
		Connection connection=db.createConnection();
		String sql="update user_share set number_of_shares=number_of_shares+? where id=?";
		int i=0;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, userShare.getNumberOfShare());
			preparedStatement.setInt(2, userShare.getId());
			i=preparedStatement.executeUpdate();
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
	public Integer findByObject(UserShare userShare) {
		Connection connection=db.createConnection();
		String sql="select * from user_share where user_id=? and share_id=?";
		int id=0;
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setInt(1, userShare.getAccount().getId());
			preparedStatement.setInt(2, userShare.getShare().getId());
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				id=resultSet.getInt("id");
			}
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
		return id;
	}

}
