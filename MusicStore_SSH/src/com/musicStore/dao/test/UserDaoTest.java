package com.musicStore.dao.test;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musicStore.bean.User;
import com.musicStore.bean.UserRole;
import com.musicStore.dao.UserDao;

public class UserDaoTest {
	private UserDao target = null;
	
	@Before
	public void before(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		target = (UserDao)ctx.getBean("userDao");
	}
	
	@Test
	public void checkLoginTest(){
		Assert.assertNotNull(target.checkLogin("sam", "123"));
		Assert.assertNull(target.checkLogin("samx", "123"));
		Assert.assertNull(target.checkLogin("sam", "000"));
	}
	
	@Test
	public void getAllTest(){
		Assert.assertEquals(3, target.getAll().size());
	}
	
	@Test
	public void addUserTest(){
		User user = new User(0,"test","123","testName","address","phone","email@tom.com",true);
		user.setUserRole(new UserRole(1,null));
		target.addUser(user);
		Assert.assertEquals(4, target.getAll().size());
	}
	
	@Test
	public void updateUserTest(){
		User user = target.fetchById(1);
		user.setName("test");
		target.updateUser(user);
	}

}
