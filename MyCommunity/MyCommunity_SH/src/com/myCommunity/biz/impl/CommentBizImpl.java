package com.myCommunity.biz.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myCommunity.biz.CommentBiz;
import com.myCommunity.dao.CommentDao;
import com.myCommunity.dao.TopicDao;
import com.myCommunity.entity.Comment;
import com.myCommunity.entity.Topic;

@Service("commentBiz")
public class CommentBizImpl implements CommentBiz {

	@Resource
	private CommentDao commentDao;
	
	@Resource
	private TopicDao topicDao;
	
	public List<Comment> getCommentsByTopicId(int topicId, int pageSize, int pageNum) {
		int startRowIndex;
		int endRowIndex = pageNum*pageSize-1;
		if(pageNum==1){
			startRowIndex = 0;
		}else{
			startRowIndex = (pageNum-1)*pageSize-1;
		}
		return commentDao.getCommentsByTopicId(topicId, startRowIndex, endRowIndex);
	}

	public int fetchCommentsRowsByTopicId(int topicId) {
		return commentDao.fetchCommentsRowsByTopicId(topicId);
	}

	public void add(Comment comment) {
		Topic topic = topicDao.fetchById(comment.getReference().getId());
		topic.setCommentTimes(topic.getCommentTimes()+1);
		topic.setUpdateTime(new Timestamp(new Date().getTime()));
		
		topicDao.update(topic);
		commentDao.add(comment);
	}

	public Comment fetchById(int id) {
		return commentDao.fetchById(id);
	}

	public void delete(int id) {
		commentDao.delete(id);
	}

}
