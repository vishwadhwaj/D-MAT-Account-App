package com.vishwadhwaj.d_mat_account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vishwadhwaj.d_mat_account.db.Db;
import com.vishwadhwaj.d_mat_account.entities.Account;
import com.vishwadhwaj.d_mat_account.entities.Share;
import com.vishwadhwaj.d_mat_account.entities.Transaction;

public class TransactionDao implements Dao<Transaction> {

	Db db;

	public TransactionDao() {
		db = Db.getInstance();
	}

	@Override
	public Integer save(Transaction transaction) {
		Connection connection = db.createConnection();
		String sql = "insert into transaction (number_of_share,price,share_id,user_id,type_id) values(?,?,?,?,?)";
		int id = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(1, transaction.getNumberOfShare());
			preparedStatement.setInt(2, transaction.getPrice());
			preparedStatement.setInt(3, transaction.getShare().getId());
			preparedStatement.setInt(4, transaction.getAccount().getId());
			preparedStatement.setInt(5, 1);
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
	public List<Transaction> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Transaction object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Integer findByObject(Transaction object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateForSell(Transaction object, Integer number) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Transaction> getTransactionsByDate(Date StartingDate, Date EndingDate, Account account) {
		Connection connection = db.createConnection();
		List<Transaction> transactions = new ArrayList<>();
		String sql = "select * from transaction where date_of_transaction between ? and ? and user_id=?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, StartingDate.toString());
			preparedStatement.setString(2, EndingDate.toString());
			preparedStatement.setInt(3, account.getId());
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Transaction transaction = new Transaction();
				transaction.setId(resultSet.getInt("id"));
				transaction.setDateOfTransaction(resultSet.getDate("date_of_transaction"));
				transaction.setTimeOfTransaction(resultSet.getString("time_of_transaction"));
				transaction.setNumberOfShare(resultSet.getInt("number_of_share"));
				transaction.setPrice(resultSet.getInt("price"));
				Share share = new Share();
				String sqlForShare = "select * from share where id=?";
				PreparedStatement preparedStatementForShare = connection.prepareStatement(sqlForShare);
				preparedStatementForShare.setInt(1, resultSet.getInt("share_id"));
				ResultSet resultSetForShare = preparedStatementForShare.executeQuery();
				while (resultSet.next()) {
					share.setId(resultSetForShare.getInt("id"));
					share.setName(resultSetForShare.getString("name"));
					share.setValue(resultSetForShare.getInt("value"));
				}
				transaction.setShare(share);
				transactions.add(transaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactions;
	}

}
