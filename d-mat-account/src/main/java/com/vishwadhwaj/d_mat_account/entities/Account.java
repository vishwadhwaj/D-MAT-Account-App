package com.vishwadhwaj.d_mat_account.entities;

public class Account {

	public String name;
	public Integer accountNumber;
	public Integer amount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Account(String name, Integer accountNumber, Integer amount) {
		super();
		this.name = name;
		this.accountNumber = accountNumber;
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Account [name=" + name + ", accountNumber=" + accountNumber + ", amount=" + amount + "]";
	}
	
}
