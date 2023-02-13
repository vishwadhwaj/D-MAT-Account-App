package com.vishwadhwaj.d_mat_account.dao;

import java.util.List;

public interface Dao<T> {
	public Integer save(T object);
	public Integer findById(Integer id);
	public List<T> findAll();
	public int update(T object);
	public Integer findByObject(T object);
}
