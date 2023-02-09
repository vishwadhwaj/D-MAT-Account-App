package com.vishwadhwaj.d_mat_account.dao;

import java.util.List;

public interface Dao<T> {
	public boolean create(T object);
	public boolean update(T object);
	public boolean delete(T object);
	public List<T> read();
}
