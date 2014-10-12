package com.myCommunity.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myCommunity.biz.MessageReceivingBiz;
import com.myCommunity.dao.MessageReceivingDao;
import com.myCommunity.entity.MessageReceiving;

@Service("messageReceivingBiz")
public class MessageReceivingBizImpl implements MessageReceivingBiz {

	@Resource
	private MessageReceivingDao messageReceivingDao;
	
	public MessageReceiving read(int id) {
		MessageReceiving msg = messageReceivingDao.fetchById(id);
		msg.setReaded(true);
		messageReceivingDao.update(msg);
		return msg;
	}

	public List<MessageReceiving> getMessageReceivingsByReceiverId(
			int receiverId, int status) {
		return messageReceivingDao.getMessageReceivingsByReceiverId(receiverId, status, null);
	}
	
	public List<MessageReceiving> getUnReadedMessageReceivingsByReceiverId(int receiverId) {
		return messageReceivingDao.getMessageReceivingsByReceiverId(receiverId, MessageReceiving.STATUS_NORMAL, false);
	}

	public void add(MessageReceiving entity) {
		messageReceivingDao.add(entity);
	}

	public void markRecycle(int id) {
		MessageReceiving entity = messageReceivingDao.fetchById(id);
		entity.setStatus(MessageReceiving.STATUS_RECYCLE);
		messageReceivingDao.update(entity);
	}

	public void markDelete(int id) {
		MessageReceiving entity = messageReceivingDao.fetchById(id);
		entity.setStatus(MessageReceiving.STATUS_DELETED);
		messageReceivingDao.update(entity);
	}

	public void markNormal(int id) {
		MessageReceiving entity = messageReceivingDao.fetchById(id);
		entity.setStatus(MessageReceiving.STATUS_NORMAL);
		messageReceivingDao.update(entity);
	}

}
