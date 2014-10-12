package com.myCommunity.dao;

import java.util.List;

import com.myCommunity.entity.MessageReceiving;

public interface MessageReceivingDao {
	public MessageReceiving fetchById(int id);
	public List<MessageReceiving> getMessageReceivingsByReceiverId(int receiverId, int status, Boolean readed);
	public void add(MessageReceiving entity);
	public void update(MessageReceiving entity);
}
