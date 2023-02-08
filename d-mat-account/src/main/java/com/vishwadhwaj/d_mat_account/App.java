package com.vishwadhwaj.d_mat_account;
import java.util.*;


public class App {
	
	private App() {
		
	}
	
	public static void showMainMenu() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Welcome to the D-Mat Account");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println();
		System.out.println();
		boolean flag=true;
		while(flag) {
			System.out.println("1.Create Account");
			System.out.println("2.Login");
			System.out.println("3.quit");
			try (Scanner scanner = new Scanner(System.in)) {
				int choice=scanner.nextInt();
				switch(choice){
					case 1:
						showRegistrationForm();
						break;
					case 2:
						showLoginForm();
						break;
					case 3:
						flag=false;
						break;
					default:
						System.out.println("Enter Valid Choice");
				}
			}
			
		}
		
	}
	public static void showRegistrationForm() {
		
	}
	public static void showLoginForm() {
		
	}
	public static void main(String[] args) {
		
		showMainMenu();
	}
}
