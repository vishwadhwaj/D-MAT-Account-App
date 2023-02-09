package com.vishwadhwaj.d_mat_account.service;

import com.vishwadhwaj.d_mat_account.entities.Account;

public class AuthenticationService {

	private static AuthenticationService authenticationService=new AuthenticationService();
	private AuthenticationService() {
		
	}
	public static AuthenticationService getInstance() {
		return authenticationService;
	}
	public boolean registerUser(Account account) {
		return UserDao.createUser();
	}
}
