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
	public Account registerUser(Account account) {
		return userDao.save(account);
	}
	public Account loginUser(Integer accountNumber) {
		return userDao.findById(accountNumber);
	}
}
