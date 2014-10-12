package com.myCommunity.dao.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myCommunity.dao.CommentDao;
import com.myCommunity.dao.TopicDao;

public class CommentDaoTest {
	private CommentDao target;
	
	public CommentDaoTest(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		target = (CommentDao)ctx.getBean("commentDao");
	}
	
	@Test
	public void getCommentsByTopicIdTest() {
		System.out.println(target.getCommentsByTopicId(1,0,6).size());
	}

}
