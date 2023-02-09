package com.vishwadhwaj.d_mat_account;

import java.util.*;
import java.util.regex.Pattern;

import com.vishwadhwaj.d_mat_account.entities.Account;
import com.vishwadhwaj.d_mat_account.exceptions.InvalidNameException;
import com.vishwadhwaj.d_mat_account.service.AuthenticationService;

public class App {

	Scanner scanner;
	AuthenticationService authenticationService;

	private App() {
		scanner = new Scanner(System.in);
		authenticationService = AuthenticationService.getInstance();
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
			scanner.nextLine();
			switch (choice) {
			case 1:
				if (register() == true) {
					System.out.println("register succeeded");
				} else {
					System.out.println("register failed");
				}
				break;
			case 2:
//				login();
				break;
			case 3:
				System.out.println("Thank you for using app");
				flag = false;
				break;
			default:
				System.out.println("Enter Valid Choice");
			}
		}

	}

	boolean register() {
		Account account = new Account();
		boolean registrationStatus = false;
		try {
			System.out.println("Enter your name:");
			String name = scanner.nextLine();
			if (name.isEmpty() || Pattern.matches("[a-zA-Z\s]+", name) == false) {
				throw new InvalidNameException();
			}
			System.out.println("Enter your account number:");
			Integer accountNUmber = scanner.nextInt();
			System.out.println("Enter your amount:");
			Integer amount = scanner.nextInt();
			account.setName(name);
			account.setAccountNumber(accountNUmber);
			account.setAmount(amount);
			registrationStatus = authenticationService.registerUser(account);
		} catch (InvalidNameException e) {
			System.out.println("Invalid Name");
		} catch (Exception e) {
			System.out.println("Invalid Account number or amount");
			scanner.nextLine();
		}
		return registrationStatus;
	}

//	boolean login() {
//
//	}

	public static void main(String[] args) {

		App app = new App();
		app.MainMenu();
	}
}
