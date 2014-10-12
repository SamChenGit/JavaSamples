package com.myCommunity.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.myCommunity.dao.TopicDao;
import com.myCommunity.entity.Topic;

@SuppressWarnings("unchecked")
@Repository("topicDao")
public class TopicDaoImpl extends BaseDao implements TopicDao{

	public Topic fetchById(int id) {
		return getHibernateTemplate().get(Topic.class, id);
	}

	public List<Topic> getLatestUpdatedTopics(final int topNum) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Topic>>() {

			public List<Topic> doInHibernate(Session sess)
					throws HibernateException, SQLException {
				return sess.createQuery("from Topic t order by t.updateTime desc")
					.setMaxResults(topNum)
					.list();
			}
		});
	}

	public List<Topic> getBestTopicsByForumId(int forumId) {
		return getHibernateTemplate().find("from Topic t where t.isBest=1 and t.forum.id=? order by t.updateTime desc", forumId);
	}


	public List<Topic> getNormalTopicsByForumId(final int forumId, final int pageSize, final int pageNum) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Topic>>() {
			public List<Topic> doInHibernate(Session sess)
					throws HibernateException, SQLException {
				return sess.createQuery("from Topic t where t.isBest=0 and t.forum.id=? order by t.updateTime desc")
					.setInteger(0, forumId)
					.setFirstResult(pageSize*(pageNum-1))
					.setMaxResults(pageSize)
					.list();
			}
		});
	}

	public int fetchNormalTopicsRowsByForumId(int forumId) {
		List<Object> list = getHibernateTemplate().find("select count(t.id) from Topic t where t.isBest=0 and t.forum.id=?", forumId);
		Integer rows = ((Number)list.get(0)).intValue();
		return rows;
	}

	public void update(Topic topic) {
		getHibernateTemplate().update(topic);
	}

	public void add(Topic topic) {
		getHibernateTemplate().save(topic);
	}

	public List<Topic> getTopicsByTitle(final String title, final int pageSize, final int pageNum) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Topic>>() {
			public List<Topic> doInHibernate(Session sess)
					throws HibernateException, SQLException {
				return sess.createQuery("from Topic t where t.title like ? order by t.updateTime desc")
					.setString(0, "%"+title+"%")
					.setFirstResult(pageSize*(pageNum-1))
					.setMaxResults(pageSize)
					.list();
			}
		});
	}
	
	public int fetchTopicsRowsByTitle(String title) {
		List<Object> list = getHibernateTemplate().find("select count(t.id) from Topic t where t.title like ?", "%"+title+"%");
		Integer rows = ((Number)list.get(0)).intValue();
		return rows;
	}
}
