package com.vishwadhwaj.d_mat_account.entities;

import java.util.Date;

public class Transaction {
	private Integer id;
	private Date dateOfTransaction;
	private String timeOfTransaction;
	private Integer numberOfShare;
	private Integer price;
	private Type type;
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", dateOfTransaction=" + dateOfTransaction + ", timeOfTransaction="
				+ timeOfTransaction + ", numberOfShare=" + numberOfShare + ", price=" + price + "]";
	}
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Transaction(Integer id, Date dateOfTransaction, String timeOfTransaction, Integer numberOfShare,
			Integer price, Type type) {
		super();
		this.id = id;
		this.dateOfTransaction = dateOfTransaction;
		this.timeOfTransaction = timeOfTransaction;
		this.numberOfShare = numberOfShare;
		this.price = price;
		this.type = type;
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
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
}
