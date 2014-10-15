package com.musicStore.dao;

import java.util.List;

import com.musicStore.bean.User;

public interface UserDao {
	void addUser(User user);
	User checkLogin(String username, String password);
	void updateUser(User user);
	List<User> getAll();
	User fetchById(int id);
	User fetchUserByUsername(String username);
}
