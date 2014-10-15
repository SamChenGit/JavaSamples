package com.musicStore.biz.impl;

import java.util.List;

import com.musicStore.bean.User;
import com.musicStore.biz.UserBiz;
import com.musicStore.dao.UserDao;
import com.musicStore.dao.UserRoleDao;

public class UserBizImpl implements UserBiz {

	private UserDao userDao;
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	private UserRoleDao userRoleDao;
	
	public UserRoleDao getUserRoleDao() {
		return userRoleDao;
	}

	public void setUserRoleDao(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}
	
	//--以下为业务方法

	public void register(User user) {
		user.setIsInUse(true);
		user.setUserRole(userRoleDao.fetchById(1));
		userDao.addUser(user);
	}

	public User checkLogin(String username, String password) {
		return userDao.checkLogin(username, password);
	}

	public List<User> getAll() {
		return userDao.getAll();
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public User fetchById(int id) {
		return userDao.fetchById(id);
	}
	
	public User fetchUserByUsername(String username){
		return userDao.fetchUserByUsername(username);
	}

}
