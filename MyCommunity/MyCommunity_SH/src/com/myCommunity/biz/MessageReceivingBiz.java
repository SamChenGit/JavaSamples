package com.myCommunity.biz;

import java.util.List;

import com.myCommunity.entity.MessageReceiving;

public interface MessageReceivingBiz {
	public MessageReceiving read(int id);
	public List<MessageReceiving> getMessageReceivingsByReceiverId(int receiverId, int status);
	public List<MessageReceiving> getUnReadedMessageReceivingsByReceiverId(int receiverId);
	public void add(MessageReceiving entity);
	public void markRecycle(int id);
	public void markDelete(int id);
	public void markNormal(int id);
}
