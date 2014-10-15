package com.musicStore.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.musicStore.bean.User;
import com.musicStore.dao.UserDao;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	public void addUser(User user) {
		getHibernateTemplate().save(user);
	}

	public User checkLogin(String username, String password) {
		List<User> list = getHibernateTemplate()
			.find("from User u where u.username=? and u.password=?", username, password);
		return list.size()>0? list.get(0):null;
	}

	public List<User> getAll() {
		return getHibernateTemplate().find("from User");
	}

	public void updateUser(User user) {
		getHibernateTemplate().update(user);
	}

	public User fetchById(int id) {
		return getHibernateTemplate().get(User.class, id);
	}
	
	public User fetchUserByUsername(String username){
		List<User> list = getHibernateTemplate()
			.find("from User u where u.username=?", username);
		if(list.size()==0)
			return null;
		else
			return list.get(0);
	}
}
