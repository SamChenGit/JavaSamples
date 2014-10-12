package com.myCommunity.biz.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.myCommunity.biz.MessageSendingBiz;
import com.myCommunity.dao.MessageSendingDao;
import com.myCommunity.entity.MessageSending;

@Service("messageSendingBiz")
public class MessageSendingBizImpl implements MessageSendingBiz {
	
	@Resource
	private MessageSendingDao messageSendingDao;

	public List<MessageSending> getMessageSendingsBySenderId(int senderId,
			int status) {
		return messageSendingDao.getMessageSendingsBySenderId(senderId, status);
	}

	public MessageSending fetchById(int id) {
		return messageSendingDao.fetchById(id);
	}

	public void add(MessageSending message) {
		messageSendingDao.add(message);
	}

	public void update(MessageSending message) {
		messageSendingDao.update(message);
	}
	
	public void markRecycle(int id){
		MessageSending msg = messageSendingDao.fetchById(id);
		msg.setStatus(MessageSending.STATUS_RECYCLE);
		messageSendingDao.update(msg);
	}
	
	public void markDelete(int id){
		MessageSending msg = messageSendingDao.fetchById(id);
		msg.setStatus(MessageSending.STATUS_DELETED);
		messageSendingDao.update(msg);
	}

	public void markSended(int id) {
		MessageSending msg = messageSendingDao.fetchById(id);
		msg.setStatus(MessageSending.STATUS_SENDED);
		messageSendingDao.update(msg);
	}

	public void delete(int id) {
		messageSendingDao.delete(id);
	}
}
