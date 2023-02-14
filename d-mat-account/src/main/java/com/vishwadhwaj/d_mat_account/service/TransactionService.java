package com.vishwadhwaj.d_mat_account.service;


import java.sql.Date;
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
		int userShareId=0;
		int i=0;
		if(userShareDao.findByObject(userShare)==0)
			userShareId=userShareDao.save(userShare);
		else {
			int Id=userShareDao.findByObject(userShare);
			userShare.setId(Id);
			i=userShareDao.update(userShare);
		}
		userShare.getAccount().setAmount(transactionAmount);
		int updatedRecord=userDao.update(userShare.getAccount());
		if(transactionId>0 && (userShareId>0 || i>0) && updatedRecord>0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean sellTransaction(UserShare userShare,int numberOfShare) {
		int transactionCharge=(int)(((userShare.getShare().getValue()*numberOfShare)*0.5)/100);
		transactionCharge=transactionCharge>100?transactionCharge:100;
		int securityTransferTax=(int)(((userShare.getShare().getValue()*numberOfShare)*0.1)/100);
		if(transactionCharge+securityTransferTax>userShare.getAccount().getAmount()) {
			return false;
		}
		int transactionAmount=(userShare.getShare().getValue()*numberOfShare)-transactionCharge-securityTransferTax;
		userShare.getAccount().setAmount(userShare.getAccount().getAmount()+transactionAmount);
		userShare.setNumberOfShare(userShare.getNumberOfShare()-numberOfShare);
		UserShareDao userShareDao=new UserShareDao();
		return userShareDao.updateForSell(userShare,numberOfShare);
	}
	
	public void viewTransactionReportByDate(Date StartingDate,Date EndingDate,Account account) {
		List<Transaction> transactions=transactionDao.getTransactionsByDate(StartingDate,EndingDate,account);
		for(int i=0;i<transactions.size();i++) {
			System.out.print("Date of Transaction:\t");
			System.out.println(transactions.get(i).getDateOfTransaction());
			System.out.print("Time Of Transaction:\t");
			System.out.println(transactions.get(i).getTimeOfTransaction());
			System.out.print("Share name:\t");
			System.out.println(transactions.get(i).getShare().getName());
			System.out.print("Share price:\t");
			System.out.println(transactions.get(i).getPrice());
			System.out.print("Number Of Share:\t");
			System.out.println(transactions.get(i).getNumberOfShare());
			System.out.print("transaction type:\t");
			System.out.println(transactions.get(i).getType());
			System.out.println();
			
		}
	}
	public void viewTransactionReportByShare() {
		
	}
	
	public List<Share> findShares(){
		ShareDao shareDao=new ShareDao();
		List<Share> shares=shareDao.findAll();
		return shares;
	}
	
	public int totalTransaction(int valueOfShare,int number,int amount) {
		int transactionAmount=valueOfShare*number;
		int transactionCharges=(int)(((transactionAmount*0.5)/100)>100?((transactionAmount*0.5)/100):100);
		int securityTransferTax=(int)((transactionAmount*0.1)/100);
		amount-=transactionAmount+transactionCharges+securityTransferTax;
		return amount;
	}
}
