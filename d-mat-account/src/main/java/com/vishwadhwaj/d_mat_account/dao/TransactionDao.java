package com.vishwadhwaj.d_mat_account.dao;

import java.util.List;

import com.vishwadhwaj.d_mat_account.db.Db;
import com.vishwadhwaj.d_mat_account.entities.Share;
import com.vishwadhwaj.d_mat_account.entities.Transaction;

public class TransactionDao implements Dao<Transaction>{

	Db db;
	public TransactionDao() {
		db=Db.getInstance();
	}
	@Override
	public boolean save(Transaction object) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean findById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public List<Transaction> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
