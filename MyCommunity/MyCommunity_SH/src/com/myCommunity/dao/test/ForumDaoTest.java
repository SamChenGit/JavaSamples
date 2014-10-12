package com.myCommunity.dao.test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.myCommunity.dao.ForumDao;
import com.myCommunity.entity.Forum;

public class ForumDaoTest {
	private ForumDao target;
	
	public ForumDaoTest(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		target = (ForumDao)ctx.getBean("forumDao");
	}
	
	@Test
	public void getAllTest() {
		Assert.assertEquals(target.getAll().size(), 10);
	}
	
	@Test
	public void getForumsWith2LevelRecursiveTest(){
		List<Forum> forums = target.getForumsWith2LevelRecursive();
		for(Forum f : forums){
			System.out.println(f.getName());
			for(Forum sf : f.getChildren()){
				System.out.println("\t"+sf.getName()+","+sf.getDescription());
			}
			System.out.println("----------");
		}
		Assert.assertEquals(forums.size(), 2);
	}

}
