package com.codeLibrary.dao.sqlite;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.codeLibrary.dao.ResourceDao;
import com.codeLibrary.entity.Resource;

public class ResourceDaoImpl extends HibernateDaoSupport implements ResourceDao {

	@Override
	public List<Resource> getResourcesByCodeInfoId(int codeInfoId) {
		return getHibernateTemplate().find("from Resource where CodeInfoId=?",codeInfoId);
	}

	@Override
	public void delete(int resourceId) {
		Resource r = getHibernateTemplate().get(Resource.class, resourceId);
		getHibernateTemplate().delete(r);
	}

	@Override
	public void add(Resource resource) {
		getHibernateTemplate().save(resource);
	}

	@Override
	public void update(Resource resource) {
		getHibernateTemplate().update(resource);
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ResourceDao target = (ResourceDao)ctx.getBean("resourceDao");
		System.out.println(target.getResourcesByCodeInfoId(1).size());
	}

	@Override
	public Resource fetchByKey(int resourceId) {
		return getHibernateTemplate().get(Resource.class, resourceId);
	}
}
