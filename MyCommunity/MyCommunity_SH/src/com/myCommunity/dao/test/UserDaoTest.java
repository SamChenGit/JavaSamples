package com.myCommunity.dao.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myCommunity.dao.ForumDao;
import com.myCommunity.dao.UserDao;
import com.myCommunity.entity.Forum;

public class UserDaoTest {
	private UserDao target;
	
	public UserDaoTest(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		target = (UserDao)ctx.getBean("userDao");
	}
	
	@Test
	public void checkLoginTest() {
		System.out.println(target.checkLogin("sam", "123").getNickname());
		System.out.println(target.checkLogin("samx","123"));
	}
	
	@Test
	public void checkUsernameAvailableTest(){
		System.out.println(target.checkUsernameAvailable("samx"));
	}

}
