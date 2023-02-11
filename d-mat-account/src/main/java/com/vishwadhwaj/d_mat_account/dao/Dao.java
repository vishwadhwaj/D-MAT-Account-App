package com.vishwadhwaj.d_mat_account.dao;

import java.util.List;

public interface Dao<T> {
	public boolean save(T object);
	public boolean findById(Integer id);
	public List<T> findAll();
	
}
