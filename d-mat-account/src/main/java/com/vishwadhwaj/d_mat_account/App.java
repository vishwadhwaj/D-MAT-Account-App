package com.vishwadhwaj.d_mat_account;

import java.util.*;
import java.util.regex.Pattern;

import com.vishwadhwaj.d_mat_account.entities.Account;
import com.vishwadhwaj.d_mat_account.entities.Share;
import com.vishwadhwaj.d_mat_account.entities.UserShare;
import com.vishwadhwaj.d_mat_account.exceptions.InvalidNameException;
import com.vishwadhwaj.d_mat_account.service.AccountService;
import com.vishwadhwaj.d_mat_account.service.AuthenticationService;
import com.vishwadhwaj.d_mat_account.service.TransactionService;

public class App {

	Scanner scanner;
	AuthenticationService authenticationService;
	AccountService accountService;
	TransactionService transactionService;
	UserShare userShare;

	private App() {
		scanner = new Scanner(System.in);
		authenticationService = AuthenticationService.getInstance();
		accountService = AccountService.getInstance();
		transactionService = TransactionService.getInstance();
		userShare=null;
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
					showUserMenu();
				} else {
					System.out.println("register failed");
				}
				break;
			case 2:
				if (login() == true) {
					showUserMenu();
				} else {
					System.out.println("login failed");
				}
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
		boolean registrationStatus=false;
		Account account=new Account();
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
			System.out.println("Bad Input");
		} catch (Exception e) {
			System.out.println("Bad Input");
			scanner.nextLine();
		}
		return registrationStatus;
	}

	boolean login() {
		System.out.println("Enter your account number");
		Integer accountNumber = scanner.nextInt();
		boolean loginStatus=authenticationService.loginUser(accountNumber);
		return loginStatus;
	}

	void showUserMenu() {
		boolean flag = true;
		while (flag) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Welcome to main menu.");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println();
			System.out.println();
			System.out.println("0.Quit");
			System.out.println("1.Display D-Mat account details");
			System.out.println("2.Deposit Money");
			System.out.println("3.Withdraw Money");
			System.out.println("4.Buy Transaction");
			System.out.println("5.Sell Transaction");
			System.out.println("6.View Transaction Report");
			scanner.nextLine();
			System.out.println("Enter your choice:");
			int choice = scanner.nextInt();
			switch (choice) {
			case 0:
				flag = false;
				break;
			case 1:
				accountService.showAccountDetails();
				break;
			case 2:
				accountService.depositMoney();
				break;
			case 3:
				accountService.withdrawMoney();
				break;
			case 4:
				if (buyTransaction() == true) {
					System.out.println("Transaction Succeded");
				} else {
					System.out.println("Transaction Failed");
				}
				break;
			case 5:
				transactionService.sellTransaction();
				break;
			case 6:
				transactionService.viewTransactionReport();
				break;
			default:
				System.out.println("Enter valid choice.");
			}
		}
	}

	boolean buyTransaction() {
		List<Share> shares=transactionService.findShares();
		for(int i=0;i<shares.size();i++) {
			System.out.println(i+1+". "+shares.get(i).getName()+" "+shares.get(i).getValue());
		}
		try {
			System.out.println("Select the share you want to buy:");
			int choice = scanner.nextInt();
			if(choice>shares.size() || choice<1) {
				throw new Exception();
			}
			System.out.println("Enter the number of shares you want to buy:");
			int number = scanner.nextInt();
			int valueOfShare=shares.get(choice-1).getValue();
			int transactionAmount=transactionService.totalTransaction(valueOfShare,number,account.getAmount());
			if(transactionAmount<0) {
				return false;
			}
			else {
				transactionService.buyTransaction(transactionAmount,account,valueOfShare,number);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		App app = new App();
		app.MainMenu();
	}
}
