package com.myCommunity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myCommunity.dao.UserDao;
import com.myCommunity.entity.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDao implements UserDao{

	public User checkLogin(String username, String password) {
		List<User> list = getHibernateTemplate().find("from User u where u.username=? and u.password=?", username, password);
		return list.size()==0?null:list.get(0);
	}
	
	public boolean checkUsernameAvailable(String username){
		Number num = (Number)getHibernateTemplate().find("select count(u.id) from User u where u.username=?", username).get(0);
		return num.intValue()==0;
	}

	public void add(User user) {
		getHibernateTemplate().save(user);
	}

	public User fetchById(int id) {
		return getHibernateTemplate().get(User.class, id);
	}

	public void update(User user) {
		getHibernateTemplate().update(user);
	}

	public List<Object[]> getAllUsersKeyValuePair() {
		return getHibernateTemplate().find("select id, username from User");
	}
}
