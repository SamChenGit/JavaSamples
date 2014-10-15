package com.musicStore.biz.test;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musicStore.biz.GenreBiz;

public class GenreBizTest {

	private GenreBiz target = null;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		target = (GenreBiz)ctx.getBean("genreBiz");
	}

	@Test
	public void testGetAll() {
		Assert.assertEquals(8, target.getAll().size());
	}

}
