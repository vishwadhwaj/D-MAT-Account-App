package com.vishwadhwaj.d_mat_account;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import com.vishwadhwaj.d_mat_account.dao.UserShareDao;
import com.vishwadhwaj.d_mat_account.entities.Account;
import com.vishwadhwaj.d_mat_account.entities.Share;
import com.vishwadhwaj.d_mat_account.entities.Transaction;

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
	List<UserShare> userShareFromDb;
	int userId;

	private App() {
		scanner = new Scanner(System.in);
		authenticationService = AuthenticationService.getInstance();
		accountService = AccountService.getInstance();
		transactionService = TransactionService.getInstance();
		userShareFromDb = null;
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
		int id = 0;
		Account account = new Account();
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
			id = authenticationService.registerUser(account);
			userId = id;
		} catch (InvalidNameException e) {
			System.out.println("Bad Input");
		} catch (Exception e) {
			System.out.println("Bad Input");
			scanner.nextLine();
		}
		return id > 0 ? true : false;
	}

	boolean login() {
		System.out.println("Enter your account number");
		Integer accountNumber = scanner.nextInt();
		int id = authenticationService.loginUser(accountNumber);
		userId = id;
		return id > 0 ? true : false;
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
				if (sellTransaction() == true) {
					System.out.println("Transaction Succeded");
				} else {
					System.out.println("Transaction failed");
				}
				break;
			case 6:
				viewTransactionReport();
				break;
			default:
				System.out.println("Enter valid choice.");
			}
		}
	}

	boolean buyTransaction() {
		List<Share> shares = transactionService.findShares();
		UserShareDao userShareDao = new UserShareDao();
		userShareFromDb = userShareDao.getUserShare(userId);
		boolean transactionStatus = false;
		UserShare userShare = new UserShare();
		for (int i = 0; i < shares.size(); i++) {
			System.out.println(i + 1 + ". " + shares.get(i).getName() + " " + shares.get(i).getValue());
		}
		try {
			System.out.println("Select the share you want to buy:");
			int choice = scanner.nextInt();
			if (choice > shares.size() || choice < 1) {
				throw new Exception();
			}
			System.out.println("Enter the number of shares you want to buy:");
			int numberOfShare = scanner.nextInt();
			int valueOfShare = shares.get(choice - 1).getValue();
			Transaction transaction = new Transaction();
			transaction.setNumberOfShare(numberOfShare);
			transaction.setPrice(valueOfShare);
			transaction.setShare(shares.get(choice - 1));
			userShare.setShare(shares.get(choice - 1));
			userShare.setNumberOfShare(numberOfShare);
			transaction.setAccount(userShareFromDb.get(0).getAccount());
			userShare.setAccount(userShareFromDb.get(0).getAccount());
			int transactionAmount = transactionService.totalTransaction(valueOfShare, numberOfShare,
					userShare.getAccount().getAmount());
			if (transactionAmount < 0) {
				return false;
			} else {
				transactionStatus = transactionService.buyTransaction(transactionAmount, userShare, transaction);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transactionStatus;
	}

	boolean sellTransaction() {
		UserShareDao userShareDao = new UserShareDao();
		boolean transactionStatus = false;
		userShareFromDb = userShareDao.getUserShare(userId);
		UserShare userShare = new UserShare();
		if (userShareFromDb.get(0).getId() <= 0) {
			System.out.println("You have no share to sell");
		} else {
			try {
				for (int i = 0; i < userShareFromDb.size(); i++) {
					System.out.println(i + 1 + " " + userShareFromDb.get(i).getShare().getName() + " "
							+ userShareFromDb.get(i).getNumberOfShare() + " "
							+ userShareFromDb.get(i).getShare().getValue());
				}
				scanner.nextLine();
				System.out.println("Select the share you want to sell:");
				int choice = scanner.nextInt();
				if (choice > userShareFromDb.size() || choice < 1) {
					throw new Exception();
				}
				System.out.println("Enter the number of share you want to sell");
				int numberOfShare = scanner.nextInt();
				if (numberOfShare > userShareFromDb.get(choice - 1).getNumberOfShare()) {
					throw new Exception();
				}
				transactionStatus = transactionService.sellTransaction(userShareFromDb.get(choice - 1), numberOfShare);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return transactionStatus;
	}

	void viewTransactionReport() {
		Account account=authenticationService.getAccount(userId);
		scanner.nextLine();
		try {
			System.out.println("1.Filter by date");
			System.out.println("2.Filter by share");
			int choice = scanner.nextInt();
			if (choice == 1) {
				System.out.println("Enter Starting Date");
				String str1=scanner.nextLine();
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				Date startingDate=dateFormat.parse(str1);
				System.out.println("Enter Ending Date");
				String str2=scanner.nextLine();
				Date endingDate=dateFormat.parse(str2);
				transactionService.viewTransactionReportByDate(startingDate,endingDate,account);
			} else if (choice == 2) {
				transactionService.viewTransactionReportByShare();
			} else if (choice > 2 || choice < 1) {
				throw new Exception();
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
