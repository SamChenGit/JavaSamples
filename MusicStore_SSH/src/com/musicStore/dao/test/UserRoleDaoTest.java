package com.musicStore.dao.test;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musicStore.dao.UserDao;
import com.musicStore.dao.UserRoleDao;

public class UserRoleDaoTest {
	
	private UserRoleDao target = null;
	
	@Before
	public void before(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		target = (UserRoleDao)ctx.getBean("userRoleDao");
	}

	@Test
	public void testFetchById() {
		Assert.assertEquals("普通用户", target.fetchById(1).getName());
	}

	@Test
	public void testGetAll() {
		Assert.assertEquals(2, target.getAll().size());
	}

}
