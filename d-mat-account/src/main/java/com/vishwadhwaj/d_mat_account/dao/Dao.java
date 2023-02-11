package com.vishwadhwaj.d_mat_account.dao;

import java.util.List;

public interface Dao<T> {
	public T save(T object);
	public T findById(Integer id);
	public List<T> findAll();
	
}
