package com.myCommunity.dao;

import java.util.List;

import com.myCommunity.entity.Forum;

public interface ForumDao {
	public List<Forum> getAll();
	public List<Forum> getForumsWith2LevelRecursive();
	public Forum fetchById(int id);
}
