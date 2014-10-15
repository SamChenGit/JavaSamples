package com.musicStore.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.musicStore.bean.User;
import com.musicStore.bean.UserRole;
import com.musicStore.dao.UserRoleDao;

public class UserRoleDaoImpl extends HibernateDaoSupport implements UserRoleDao {

	public UserRole fetchById(int id) {
		return getHibernateTemplate().get(UserRole.class, id);
	}
	
	public List<UserRole> getAll() {
		return getHibernateTemplate().find("from UserRole");
	}
}
