package com.myCommunity.dao.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myCommunity.dao.TopicDao;

public class TopicDaoTest {
	private TopicDao target;
	
	public TopicDaoTest(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		target = (TopicDao)ctx.getBean("topicDao");
	}
	
	@Test
	public void checkLoginTest() {
		System.out.println(target.fetchById(1));
	}

}
