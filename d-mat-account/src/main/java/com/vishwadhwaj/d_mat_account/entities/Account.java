package com.vishwadhwaj.d_mat_account.entities;

public class Account {

	private Integer id;
	private String name;
	private Integer accountNumber;
	private Integer amount;
	private Share share;
	private Integer numberOfShare;

	public Account(Integer id, String name, Integer accountNumber, Integer amount, Share share, Integer numberOfShare) {
		super();
		this.id = id;
		this.name = name;
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.share = share;
		this.numberOfShare = numberOfShare;
	}

	public Share getShare() {
		return share;
	}

	public void setShare(Share share) {
		this.share = share;
	}

	public Integer getNumberOfShare() {
		return numberOfShare;
	}

	public void setNumberOfShare(Integer numberOfShare) {
		this.numberOfShare = numberOfShare;
	}

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

	

	@Override
	public String toString() {
		return "Account [id=" + id + ", name=" + name + ", accountNumber=" + accountNumber + ", amount=" + amount
				+ ", share=" + share + ", numberOfShare=" + numberOfShare + "]";
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
