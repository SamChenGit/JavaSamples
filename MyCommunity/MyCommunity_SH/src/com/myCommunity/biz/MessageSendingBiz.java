package com.myCommunity.biz;

import java.util.List;

import com.myCommunity.entity.MessageSending;

public interface MessageSendingBiz {
	public List<MessageSending> getMessageSendingsBySenderId(int senderId, int status);
	public MessageSending fetchById(int id);
	public void add(MessageSending message);
	public void update(MessageSending message);
	public void markRecycle(int id);
	public void markDelete(int id);
	public void markSended(int id);
	public void delete(int id);
}
