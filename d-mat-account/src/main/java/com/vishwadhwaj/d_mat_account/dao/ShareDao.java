package com.vishwadhwaj.d_mat_account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vishwadhwaj.d_mat_account.db.Db;
import com.vishwadhwaj.d_mat_account.entities.Share;

public class ShareDao implements Dao<Share> {

	private Db db;
	public ShareDao() {
		db=Db.getInstance();
	}
	@Override
	public List<Share> findAll() {
		// TODO Auto-generated method stub
		List<Share> shares=new ArrayList<>();
		Connection connection=db.createConnection();
		String sql="select * from share";
		try {
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next()) {
				Share share=new Share();
				share.setId(resultSet.getInt("id"));
				share.setName(resultSet.getString("name"));
				share.setValue(resultSet.getInt("Value"));
				shares.add(share);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shares;
	}
	@Override
	public Integer save(Share object) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int update(Share object) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public Integer findByObject(Share object) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean updateForSell(Share object) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

	
}
