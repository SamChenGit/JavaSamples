package com.musicStore.dao.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musicStore.dao.AlbumDao;


public class AlbumDaoTest {
	
	private AlbumDao target = null;
	
	@Before
	public void before(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		target = (AlbumDao)ctx.getBean("albumDao");
	}
	
	@Test
	public void getHotAlbumsTest(){
		Assert.assertEquals(9, target.getHotAlbums(9).size());
	}
	
	@Test
	public void getAlbumsByConditionsTest(){
		Assert.assertEquals(2, target.getAlbumsByConditions(1, "", 4, 5).size());
		Assert.assertEquals(2, target.getAlbumsByConditions(0, "真", 1, 5).size());
	}
	
	@Test
	public void getRowCountByConditionsTest(){
		Assert.assertEquals(17, target.getRowCountByConditions(1, ""));
		Assert.assertEquals(2, target.getRowCountByConditions(0, "真"));
		Assert.assertEquals(27, target.getRowCountByConditions(0, ""));
	}
	
	@Test
	public void fetchAlbumByIdTest(){
		Assert.assertEquals("华丽的冒险", target.fetchAlbumById(5).getTitle());
	}
	
	@Test
	public void getAlbumTitlesByPrefixTest(){
		Assert.assertEquals(2, target.getAlbumTitlesByPrefix("真", 10).size());
		Assert.assertEquals(2, target.getAlbumTitlesByPrefix("太", 10).size());
		Assert.assertEquals(1, target.getAlbumTitlesByPrefix("爱", 10).size());
	}
}
