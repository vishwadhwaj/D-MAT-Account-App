package com.vishwadhwaj.d_mat_account.entities;

import java.util.Date;

public class Transaction {
	private Integer id;
	private Date dateOfTransaction;
	private String timeOfTransaction;
	private Integer numberOfShare;
	private Integer price;
	private String type;
	private Share share;
	private Account account;
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", dateOfTransaction=" + dateOfTransaction + ", timeOfTransaction="
				+ timeOfTransaction + ", numberOfShare=" + numberOfShare + ", price=" + price + ", type=" + type
				+ ", share=" + share + ", account=" + account + "]";
	}

	public Share getShare() {
		return share;
	}

	public void setShare(Share share) {
		this.share = share;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}
	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}
	public String getTimeOfTransaction() {
		return timeOfTransaction;
	}
	public void setTimeOfTransaction(String timeOfTransaction) {
		this.timeOfTransaction = timeOfTransaction;
	}
	public Integer getNumberOfShare() {
		return numberOfShare;
	}
	public void setNumberOfShare(Integer numberOfShare) {
		this.numberOfShare = numberOfShare;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
