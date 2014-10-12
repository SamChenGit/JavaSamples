package com.myCommunity.dao;

import java.util.List;

import com.myCommunity.entity.Topic;

public interface TopicDao {
	public Topic fetchById(int id);
	public List<Topic> getLatestUpdatedTopics(int topNum);
	public List<Topic> getBestTopicsByForumId(int forumId);
	public List<Topic> getNormalTopicsByForumId(int forumId, int pageSize, int pageNum);
	public int fetchNormalTopicsRowsByForumId(int forumId);
	public void update(Topic topic);
	public void add(Topic topic);
	public List<Topic> getTopicsByTitle(String title, int pageSize, int pageNum);
	public int fetchTopicsRowsByTitle(String title);
}
