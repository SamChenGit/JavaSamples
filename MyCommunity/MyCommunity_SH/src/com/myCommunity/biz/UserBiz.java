package com.myCommunity.biz;

import java.util.Map;

import com.myCommunity.entity.User;

public interface UserBiz {
	public User checkLogin(String username, String password);
	public void register(User user);
	public User fetchById(int id);
	public void update(User user);
	public boolean checkUsernameAvailable(String username);
	public Map<String,Integer> getAllUsersKeyValuePair();
}
