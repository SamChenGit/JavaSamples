package com.myCommunity.dao.test;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myCommunity.dao.MessageSendingDao;

public class MessageSendingDaoTest {
	private MessageSendingDao target;
	
	public MessageSendingDaoTest(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		target = (MessageSendingDao)ctx.getBean("messageSendingDao");
	}
	
	@Test
	public void getMessageSendingBySenderIdTest(){
		Assert.assertEquals(1, target.getMessageSendingsBySenderId(1, 0).size());
		Assert.assertEquals(1, target.getMessageSendingsBySenderId(1, 1).size());
		Assert.assertEquals(1, target.getMessageSendingsBySenderId(1, 2).size());
		Assert.assertEquals(1, target.getMessageSendingsBySenderId(1, 3).size());
	}
	
	@Test
	public void fetchMessageSendingByIdTest(){
		Assert.assertEquals("欢迎来到XDA社区，请大家准守社区规范，文明发言。", target.fetchById(1).getTitle());
		Assert.assertEquals(7, target.fetchById(1).getMessageReceivingList().size());
	}
}
