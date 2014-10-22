package com.codeLibrary.biz.impl;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.codeLibrary.biz.ResourceBiz;
import com.codeLibrary.dao.ResourceDao;
import com.codeLibrary.entity.Resource;

public class ResourceBizImpl implements ResourceBiz {
	
	private ResourceDao resourceDao;
	public ResourceDao getResourceDao() {
		return resourceDao;
	}
	public void setResourceDao(ResourceDao resourceDao) {
		this.resourceDao = resourceDao;
	}

	@Override
	public List<Resource> getResourcesByCodeInfoId(int codeInfoId) {
		return resourceDao.getResourcesByCodeInfoId(codeInfoId);
	}

	@Override
	public void delete(int resourceId) {
		resourceDao.delete(resourceId);
	}

	@Override
	public void add(Resource resource) {
		resourceDao.add(resource);
	}
	
	@Override
	public void update(Resource resource) {
		resourceDao.update(resource);
	}
	
	@Override
	public Resource fetchByKey(int resourceId) {
		return resourceDao.fetchByKey(resourceId);
	}

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		ResourceBiz target = (ResourceBiz)ctx.getBean("resourceBiz");
		System.out.println(target.getResourcesByCodeInfoId(1).size());
	}

}
