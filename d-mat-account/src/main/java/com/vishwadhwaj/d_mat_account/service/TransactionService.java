package com.vishwadhwaj.d_mat_account.service;

import java.util.List;

import com.vishwadhwaj.d_mat_account.dao.ShareDao;
import com.vishwadhwaj.d_mat_account.entities.Share;

public class TransactionService {

	private static TransactionService transactionService=new TransactionService();
	
	private TransactionService() {
		
	}
	
	public static TransactionService getInstance() {
		return transactionService;
	}
	
	public void buyTransaction() {
		
	}
	
	public void sellTransaction() {
		
	}
	
	public void viewTransactionReport() {
		
	}
	
	public List<Share> findShares(){
		ShareDao shareDao=new ShareDao();
		List<Share> shares=shareDao.findAll();
		return shares;
	}
}
