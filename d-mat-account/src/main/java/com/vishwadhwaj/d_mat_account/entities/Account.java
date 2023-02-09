package com.vishwadhwaj.d_mat_account.entities;

public class Account {

	private Integer id;
	private String name;
	private Integer accountNumber;
	private Integer amount;

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

	public Account(String name, Integer accountNumber, Integer amount, Integer id) {
		super();
		this.name = name;
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.id = id;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", accountNumber=" + accountNumber + ", amount=" + amount + "]";
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
