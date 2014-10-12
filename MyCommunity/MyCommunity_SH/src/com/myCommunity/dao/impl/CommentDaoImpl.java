package com.myCommunity.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import com.myCommunity.dao.CommentDao;
import com.myCommunity.entity.Comment;

@SuppressWarnings("unchecked")
@Repository("commentDao")
public class CommentDaoImpl extends BaseDao implements CommentDao{

	
	public List<Comment> getCommentsByTopicId(final int topicId, final int startRowIndex, final int endRowIndex) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Comment>>() {

			public List<Comment> doInHibernate(Session sess)
					throws HibernateException, SQLException {
				String hql = "from Comment c where c.reference.id=? order by c.commentTime asc";
				return sess.createQuery(hql)
						.setInteger(0, topicId)
						.setFirstResult(startRowIndex)
						.setMaxResults(endRowIndex-startRowIndex)
						.list();
			}
		});
		
	}

	public int fetchCommentsRowsByTopicId(int topicId) {
		List<Object> list = getHibernateTemplate().find("select count(c.id) from Comment c where c.reference.id=?", topicId);
		Integer rows = ((Number)list.get(0)).intValue();
		return rows;
	}

	public void add(Comment comment) {
		getHibernateTemplate().save(comment);
	}

	public Comment fetchById(int id) {
		return getHibernateTemplate().get(Comment.class, id);
	}

	public void delete(int id) {
		Comment entity = getHibernateTemplate().get(Comment.class, id);
		getHibernateTemplate().delete(entity);
	}

}
