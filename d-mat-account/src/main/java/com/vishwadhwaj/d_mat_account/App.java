package com.vishwadhwaj.d_mat_account;

import java.util.*;

import com.vishwadhwaj.d_mat_account.entities.Account;
import com.vishwadhwaj.d_mat_account.service.AuthenticationService;

public class App {

	
	Scanner scanner;
	AuthenticationService authenticationService;
	private App() {
		scanner = new Scanner(System.in);
		authenticationService=AuthenticationService.getInstance();
	}

	void MainMenu() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Welcome to the D-Mat Account");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		System.out.println();
		boolean flag = true;
		while (flag) {
			System.out.println("1.Create Account");
			System.out.println("2.Login");
			System.out.println("3.quit");

			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				register();
				break;
			case 2:
				login();
				break;
			case 3:
				flag = false;
				break;
			default:
				System.out.println("Enter Valid Choice");
			}
		}

	}

	boolean register() {
		Account account=new Account();
		try {
			System.out.println("Enter your name:");
			String name = scanner.nextLine();
			System.out.println("Enter your account number:");
			Integer accountNumber = scanner.nextInt();
			System.out.println("Enter your amount:");
			Integer amount = scanner.nextInt();
			account.setName(name);
			account.setAccountNumber(accountNumber);
			account.setAmount(amount);
		} catch (Exception e) {
			System.out.println("Bad Credentials");
		}
		return authenticationService.registerUser(account);
	}

	boolean login() {

	}

	public static void main(String[] args) {

		App app = new App();
		app.MainMenu();
	}
}
