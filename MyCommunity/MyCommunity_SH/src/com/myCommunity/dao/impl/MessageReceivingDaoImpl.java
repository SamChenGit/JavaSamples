package com.myCommunity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myCommunity.dao.MessageReceivingDao;
import com.myCommunity.entity.MessageReceiving;
@Repository("messageReceivingDao")
public class MessageReceivingDaoImpl extends BaseDao implements
		MessageReceivingDao {

	public MessageReceiving fetchById(int id) {
		return getHibernateTemplate().get(MessageReceiving.class, id);
	}

	//注意排除草稿（尚未发出的邮件）即MessageReceiving.messageSending.status=0
	public List<MessageReceiving> getMessageReceivingsByReceiverId(
			int receiverId, int status, Boolean readed) {
		if(readed == null){
			return getHibernateTemplate().find("from MessageReceiving mr where mr.receiver.id=? and mr.status=? and mr.messageSending.status!=0 order by mr.messageSending.sendTime desc", receiverId, status);
		}else{
			return getHibernateTemplate().find("from MessageReceiving mr where mr.receiver.id=? and mr.status=? and mr.readed=? and mr.messageSending.status!=0 order by mr.messageSending.sendTime desc", receiverId, status, readed);
		}
	}

	public void add(MessageReceiving entity) {
		getHibernateTemplate().save(entity);
	}

	public void update(MessageReceiving entity) {
		getHibernateTemplate().update(entity);
	}

}
