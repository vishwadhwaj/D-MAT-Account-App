package com.vishwadhwaj.d_mat_account.entities;

public class UserShare {
	 
	private int id;
	private Share share;
	private Integer numberOfShare;
	private Account account;
	public UserShare() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserShare [id=" + id + ", share=" + share + ", numberOfShare=" + numberOfShare + ", account=" + account
				+ "]";
	}
	public UserShare(int id, Share share, Integer numberOfShare, Account account) {
		super();
		this.id = id;
		this.share = share;
		this.numberOfShare = numberOfShare;
		this.account = account;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
}
