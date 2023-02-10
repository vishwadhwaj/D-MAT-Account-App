package com.vishwadhwaj.d_mat_account.entities;

public class Share {
	
	private Integer id;
	private String name;
	private Integer value;
	@Override
	public String toString() {
		return "Share [id=" + id + ", name=" + name + ", value=" + value + "]";
	}
	public Share() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Share(Integer id, String name, Integer value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
}
