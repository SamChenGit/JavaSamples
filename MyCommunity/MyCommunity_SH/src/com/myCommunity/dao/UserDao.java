package com.myCommunity.dao;

import java.util.List;

import com.myCommunity.entity.User;

public interface UserDao {
	public  User checkLogin(String username, String password);
	public void add(User user);
	public boolean checkUsernameAvailable(String username);
	public User fetchById(int id);
	public void update(User user);
	public List<Object[]> getAllUsersKeyValuePair();
}
