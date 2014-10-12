package com.myCommunity.dao.test;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myCommunity.dao.MessageReceivingDao;

public class MessageReceivingDaoTest {
	private MessageReceivingDao target;
	
	public MessageReceivingDaoTest(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		target = (MessageReceivingDao)ctx.getBean("messageReceivingDao");
	}
	@Test
	public void testGetMessageReceivingsByReceiverId() {
		Assert.assertEquals(4, target.getMessageReceivingsByReceiverId(1, 1, null).size());
		Assert.assertEquals(1, target.getMessageReceivingsByReceiverId(1, 2, null).size());
		Assert.assertEquals(1, target.getMessageReceivingsByReceiverId(1, 3, null).size());
		Assert.assertEquals(3, target.getMessageReceivingsByReceiverId(1, 1, false).size());
		
	}

}
