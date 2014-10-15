package com.musicStore.dao.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.musicStore.bean.Genre;
import com.musicStore.dao.GenreDao;

@SuppressWarnings("unchecked")
public class GenreDaoImpl extends HibernateDaoSupport implements GenreDao {

	public List<Genre> getAll() {
		return getHibernateTemplate().find("from Genre");
	}
}
