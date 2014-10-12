package com.myCommunity.biz;

import java.util.List;

import com.myCommunity.entity.Topic;

public interface TopicBiz {
	public Topic readTheTopic(int id);
	public List<Topic> getLatestUpdatedTopics(int topNum);
	public List<Topic> getBestTopicsByForumId(int forumId);
	public List<Topic> getNormalTopicsByForumId(int forumId, final int pageSize, final int pageNum);
	public int fetchNormalTopicsRowsByForumId(int forumId);
	public void add(Topic topic);
	public List<Topic> getTopicsByTitle(String title, int pageSize, int pageNum);
	public int fetchTopicsRowsByTitle(String title);
}
