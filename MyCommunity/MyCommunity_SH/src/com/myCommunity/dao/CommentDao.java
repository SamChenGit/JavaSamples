package com.myCommunity.dao;

import java.util.List;

import com.myCommunity.entity.Comment;

public interface CommentDao {
	public List<Comment> getCommentsByTopicId(int topicId, int startRowIndex, int endRowIndex);
	public int fetchCommentsRowsByTopicId(int topicId);
	public void add(Comment comment);
	public Comment fetchById(int id);
	public void delete(int id);
}
