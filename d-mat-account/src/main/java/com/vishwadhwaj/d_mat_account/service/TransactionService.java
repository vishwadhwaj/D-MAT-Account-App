package com.vishwadhwaj.d_mat_account.service;

import java.util.List;

import com.vishwadhwaj.d_mat_account.dao.ShareDao;
import com.vishwadhwaj.d_mat_account.dao.TransactionDao;
import com.vishwadhwaj.d_mat_account.dao.UserDao;
import com.vishwadhwaj.d_mat_account.dao.UserShareDao;
import com.vishwadhwaj.d_mat_account.entities.Account;
import com.vishwadhwaj.d_mat_account.entities.Share;
import com.vishwadhwaj.d_mat_account.entities.Transaction;
import com.vishwadhwaj.d_mat_account.entities.UserShare;

public class TransactionService {

	private static TransactionService transactionService=new TransactionService();
	TransactionDao transactionDao;
	UserDao userDao;
	UserShareDao userShareDao;
	private TransactionService() {
		transactionDao=new TransactionDao();
		userDao=new UserDao();
		userShareDao=new UserShareDao();
	}
	
	public static TransactionService getInstance() {
		return transactionService;
	}
	
	public boolean buyTransaction(int transactionAmount,UserShare userShare,Transaction transaction) {
		int transactionId=transactionDao.save(transaction);
		int userShareId=userShareDao.save(userShare);
		int remainingBalance=userShare.getAccount().getAmount()-transactionAmount;
		userShare.getAccount().setAmount(remainingBalance);
		int updatedRecord=userDao.update(userShare.getAccount());
		if(transactionId>0 && userShareId>0 && updatedRecord>0) {
			return true;
		}
		else {
			return false;
		}
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
	
	public int totalTransaction(int valueOfShare,int number,int amount) {
		int transactionCharges=(int)(((amount*0.5)/100)>100?((amount*0.5)/100):100);
		int securityTransferTax=(int)((amount*0.1)/100);
		amount-=(valueOfShare*number)+transactionCharges+securityTransferTax;
		return amount;
	}
}
