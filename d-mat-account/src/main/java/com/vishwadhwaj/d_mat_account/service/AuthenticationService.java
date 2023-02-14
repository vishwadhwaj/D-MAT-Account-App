package com.vishwadhwaj.d_mat_account.service;

import com.vishwadhwaj.d_mat_account.dao.UserDao;
import com.vishwadhwaj.d_mat_account.entities.Account;

public class AuthenticationService {

	private static AuthenticationService authenticationService=new AuthenticationService();
	private UserDao userDao;
	private AuthenticationService() {
		 userDao=new UserDao();
	}
	public static AuthenticationService getInstance() {
		return authenticationService;
	}
	public int registerUser(Account account) {
		return userDao.save(account);
	}
	public int loginUser(Integer accountNumber) {
		return userDao.findById(accountNumber);
	}
	
	public Account getAccount(Integer id) {
		Account account=userDao.findByUserId(id);
		return account;
	}
}
