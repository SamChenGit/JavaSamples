package com.myCommunity.biz.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myCommunity.biz.TopicBiz;
import com.myCommunity.dao.TopicDao;
import com.myCommunity.entity.Topic;

@Service("topicBiz")
public class TopicBizImpl implements TopicBiz {
	@Resource
	private TopicDao topicDao;
	
	public List<Topic> getLatestUpdatedTopics(int topNum) {
		return topicDao.getLatestUpdatedTopics(topNum);
	}

	public List<Topic> getBestTopicsByForumId(int forumId){
		return topicDao.getBestTopicsByForumId(forumId);
	}

	public List<Topic> getNormalTopicsByForumId(int forumId, int pageSize, int pageNum) {
		return topicDao.getNormalTopicsByForumId(forumId, pageSize, pageNum);
	}

	public int fetchNormalTopicsRowsByForumId(int forumId) {
		return topicDao.fetchNormalTopicsRowsByForumId(forumId);
	}
	
	public Topic readTheTopic(int id){
		Topic topic = topicDao.fetchById(id);
		topic.setClicks(topic.getClicks()+1);
		topicDao.update(topic);
		return topic;
	}

	public void add(Topic topic) {
		topicDao.add(topic);
	}

	public List<Topic> getTopicsByTitle(String title, int pageSize, int pageNum) {
		if(title==null || title.isEmpty())
			return new ArrayList<Topic>();
		return topicDao.getTopicsByTitle(title, pageSize, pageNum);
	}

	public int fetchTopicsRowsByTitle(String title) {
		if(title==null || title.isEmpty())
			return 0;
		return topicDao.fetchTopicsRowsByTitle(title);
	}
}
