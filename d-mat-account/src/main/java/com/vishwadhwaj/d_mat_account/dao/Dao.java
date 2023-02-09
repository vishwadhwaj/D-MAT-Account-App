package com.vishwadhwaj.d_mat_account.dao;

import java.util.List;

public interface Dao<T> {
	public T create(T object);
	public T update(T object);
	public T delete(T object);
	public List<T> read();
}
