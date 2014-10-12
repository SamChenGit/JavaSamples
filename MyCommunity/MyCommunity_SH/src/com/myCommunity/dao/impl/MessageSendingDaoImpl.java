package com.myCommunity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myCommunity.dao.MessageSendingDao;
import com.myCommunity.entity.MessageReceiving;
import com.myCommunity.entity.MessageSending;

@Repository("messageSendingDao")
public class MessageSendingDaoImpl extends BaseDao implements MessageSendingDao {

	public List<MessageSending> getMessageSendingsBySenderId(int senderId,
			int status) {
		return getHibernateTemplate().find("from MessageSending ms where ms.sender.id=? and ms.status=? order by ms.sendTime desc", senderId, status);
	}

	public void add(MessageSending message) {
		getHibernateTemplate().save(message);
	}

	public void update(MessageSending message) {
		getHibernateTemplate().update(message);
	}

	public MessageSending fetchById(int id) {
		return getHibernateTemplate().get(MessageSending.class, id);
	}

	public void delete(int id) {
		MessageSending ms = getHibernateTemplate().get(MessageSending.class, id);
		getHibernateTemplate().delete(ms);
	}

}
