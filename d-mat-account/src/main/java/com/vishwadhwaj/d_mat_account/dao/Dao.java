package com.vishwadhwaj.d_mat_account.dao;

import java.util.List;

public interface Dao<T> {
	public boolean create(T object);
	public boolean search(Integer object);
}
