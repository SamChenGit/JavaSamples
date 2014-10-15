package com.musicStore.biz.test;

import java.sql.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.musicStore.bean.Album;
import com.musicStore.bean.Genre;
import com.musicStore.biz.AlbumBiz;

public class AlbumBizTest {

	private AlbumBiz target = null;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		target = (AlbumBiz)ctx.getBean("albumBiz");
	}

	@Test
	public void testGetHotAlbums() {
		Assert.assertEquals(9, target.getHotAlbums(9).size());
	}

	@Test
	public void testGetRowCountByConditions() {
		Assert.assertEquals(2, target.getRowCountByConditions(0, "真"));
	}

	@Test
	public void testGetAlbumsByConditions() {
		Assert.assertEquals(2, target.getAlbumsByConditions(1, "", 4, 5).size());
	}

	@Test
	public void testFetchAlbumById() {
		Assert.assertEquals("恋曲2000",target.fetchAlbumById(1).getTitle());
	}

	@Test
	public void testAddAlbum() {
		Album album = new Album(0,"artist..","title..",30,new Date(112,0,1),"desc...");
		album.setGenre(new Genre(2,null,null));
		target.addAlbum(album);
	}

	@Test
	public void testUpdateAlbum() {
		Album album = target.fetchAlbumById(2);
		album.setTitle("title..");
		target.updateAlbum(album);
	}

}
