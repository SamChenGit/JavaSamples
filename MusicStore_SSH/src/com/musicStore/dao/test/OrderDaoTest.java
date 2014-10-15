package com.musicStore.dao.test;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musicStore.dao.OrderDao;

public class OrderDaoTest {

	private OrderDao target;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		target = (OrderDao)ctx.getBean("orderDao");
	}

	@Test
	public void testFetchByKey() {
		Assert.assertNotNull(target.fetchByKey(1));
	}

	@Test
	public void testAdd() {
	
	}

	@Test
	public void testUpdateOrderSetDeliveried() {
	
	}

}
