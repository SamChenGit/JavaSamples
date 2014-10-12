package com.myCommunity.biz;

import java.util.List;

import com.myCommunity.entity.Forum;

public interface ForumBiz {
	public List<Forum> getForumsWith2LevelRecursive();
	public Forum fetchById(int id);
}
