package com.musicStore.biz.test;


import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musicStore.biz.AlbumBiz;

public class MyTest {
	
	private AlbumBiz albumBiz;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("测试类开始");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("测试类结束");
	}

	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		albumBiz = (AlbumBiz)ctx.getBean("albumBiz");
		System.out.println("测试方法开始");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("测试方法结束");
	}
	
	@Test
	public void testGetAlbumsByConditions(){
		int n = albumBiz.getAlbumsByConditions(1, "", 4, 5).size();
		Assert.assertEquals(2, n);
	}
}
