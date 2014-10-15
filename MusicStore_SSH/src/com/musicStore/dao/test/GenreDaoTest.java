package com.musicStore.dao.test;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.musicStore.dao.GenreDao;

public class GenreDaoTest {
	
	@Test
	public void getAllTest(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		GenreDao target = (GenreDao)ctx.getBean("genreDao");
		Assert.assertEquals(8, target.getAll().size());
	}
}
