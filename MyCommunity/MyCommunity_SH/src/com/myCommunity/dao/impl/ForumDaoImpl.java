package com.myCommunity.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.myCommunity.dao.ForumDao;
import com.myCommunity.entity.Forum;

@Repository("forumDao")
public class ForumDaoImpl extends BaseDao implements ForumDao{

	public List<Forum> getAll() {
		return getHibernateTemplate().find("from Forum");
	}

	public List<Forum> getForumsWith2LevelRecursive() {
		return getHibernateTemplate().find("select distinct f from Forum f left join fetch f.children where f.parent is null");
	}

	public Forum fetchById(int id) {
		return getHibernateTemplate().get(Forum.class, id);
	}

}
