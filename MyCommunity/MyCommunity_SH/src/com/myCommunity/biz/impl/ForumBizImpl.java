package com.myCommunity.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myCommunity.biz.ForumBiz;
import com.myCommunity.dao.ForumDao;
import com.myCommunity.entity.Forum;

@Service("forumBiz")
public class ForumBizImpl implements ForumBiz {
	
	@Resource
	private ForumDao forumDao;
	
	public List<Forum> getForumsWith2LevelRecursive() {
		return forumDao.getForumsWith2LevelRecursive();
	}

	public Forum fetchById(int id) {
		return forumDao.fetchById(id);
	}
}
