package com.myCommunity.dao;

import java.util.List;

import com.myCommunity.entity.MessageSending;

public interface MessageSendingDao {
	public List<MessageSending> getMessageSendingsBySenderId(int senderId, int status);
	public MessageSending fetchById(int id);
	public void add(MessageSending message);
	public void update(MessageSending message);
	public void delete(int id);
}
