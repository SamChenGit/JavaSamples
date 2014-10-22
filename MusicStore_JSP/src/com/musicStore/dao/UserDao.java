package com.musicStore.dao;

import java.util.List;

import com.musicStore.entity.User;

public interface UserDao {
	boolean addUser(User user);
	User checkLogin(String username, String password);
	boolean updateUser(User user);
	List<User> getAllUsers();
	boolean deleteUser(int id);
}
