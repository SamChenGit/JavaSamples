package com.musicStore.biz.test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musicStore.bean.User;
import com.musicStore.biz.UserBiz;

public class UserBizTest {

	private UserBiz target = null;

	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		target = (UserBiz)ctx.getBean("userBiz");
	}

	@Test
	public void testRegister() {
		User user = new User(0, "username..", "password..", "name..", "address..", "phone..", "email..", true);
		target.register(user);
		Assert.assertTrue(user.getId()>0);
	}
	
	@Test
	public void testUpdateUser() {
		User user = target.fetchById(1);
		user.setUsername("Test..");
		target.updateUser(user);
	}

	@Test
	public void testCheckLogin() {
		Assert.assertNotNull(target.checkLogin("sam", "123"));
		Assert.assertNull(target.checkLogin("samx", "123"));
		Assert.assertNull(target.checkLogin("sam", "000"));
	}

	@Test
	public void testFetchById() {
		Assert.assertEquals("sam", target.fetchById(2).getUsername());
	}
	
	@Test
	public void testFetchUserByUsername(){
		Assert.assertNotNull(target.fetchUserByUsername("sam"));
		Assert.assertNull(target.fetchUserByUsername("abc"));
	}

}
