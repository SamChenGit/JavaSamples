package com.musicStore.dao.impl;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.musicStore.bean.Album;
import com.musicStore.dao.AlbumDao;

@SuppressWarnings("unchecked")
public class AlbumDaoImpl extends HibernateDaoSupport implements AlbumDao {

	public void addAlbum(Album album) {
		getHibernateTemplate().save(album);
	} 
	
	public void updateAlbum(Album album) {
		getHibernateTemplate().update(album);
	}

	public Album fetchAlbumById(int id) {
		return getHibernateTemplate().get(Album.class, id);
	}

	public List<Album> getAlbumsByConditions(final int genreId, final String title, final int page,
			final int pageSize) {
		return getHibernateTemplate().executeFind(
				new HibernateCallback<List<Album>>() {
					public List<Album> doInHibernate(Session sess)
							throws HibernateException, SQLException {
						
						Map<String, Object> params = new HashMap<String,Object>();	//参数 
						String hql = "from Album a where 1=1";						//基本语句
						if(genreId>0){												
							hql += " and a.genre.id=:genreId";
							params.put("genreId", genreId);
						}
						if(title != null && !title.equals("")){
							hql += " and a.title like '%'||:title||'%'";
							params.put("title", title);
						}
						return sess.createQuery(hql)
						.setProperties(params)
						.setFirstResult((page-1)*pageSize)
						.setMaxResults(pageSize)
						.list();
					}
				}
			);
	}

	public List<Album> getHotAlbums(final int count) {

		return getHibernateTemplate().executeFind(new HibernateCallback<List<Album>>() {
			public List<Album> doInHibernate(Session sess)
					throws HibernateException, SQLException {
				String hql = "from Album order by DateReleased Desc";
				return sess.createQuery(hql).setMaxResults(count).list();
			}
		});
	}

	public int getRowCountByConditions(final int genreId, final String title) {
		return getHibernateTemplate().execute(new HibernateCallback<Integer>() {

			public Integer doInHibernate(Session sess)
					throws HibernateException, SQLException {
				long rows = 0;
				Map<String, Object> params = new HashMap<String,Object>(); 
				String hql = "select Count(a.id) from Album a where 1=1";
				if(genreId>0){
					hql += " and a.genre.id=:genreId";
					params.put("genreId", genreId);
				}
				if(title != null && !title.equals("")){
					hql += " and a.title like '%'||:title||'%'";
					params.put("title", title);
				}
					Query q = sess.createQuery(hql);
					q.setProperties(params);
					rows = (Long)q.uniqueResult();
				return (int)rows;
			}
		});
	}

	public List<String> getAlbumTitlesByPrefix(final String prefix, final int count) {
		List<String> list = getHibernateTemplate().executeFind(new HibernateCallback<List>() {
			public List doInHibernate(Session sess){
				String hql = "select a.title from Album a where a.title like :title";
				return sess.createQuery(hql).setString("title", prefix+"%")
				.setMaxResults(count).list();
			}		
		});
		return list;
	}
	
	public List<Object[]> getGenreAlbumsNum(){
		List<Object[]> list = getHibernateTemplate().find("select a.genre.name, count(a.id) from Album a group by a.genre.name");
		return list;
	}

	public List<Album> getAlbumsUnderStcokLine(int stockLine) {
		return getHibernateTemplate().find("from Album a where a.stock<=?", stockLine);
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		AlbumDao albumDao = (AlbumDao)ctx.getBean("albumDao");
		Calendar cal = Calendar.getInstance();
		Date now = cal.getTime();
		cal.add(Calendar.DATE, 1);
		Date then = cal.getTime();
		Date today = new Date(now.getYear(),now.getMonth(),now.getDate());
		Date tomorrow = new Date(then.getYear(),then.getMonth(),then.getDate());
		List<Object[]> list = albumDao.getAlbumsSalesQty(today, tomorrow);
		for(Object[] arr : list){
			System.out.printf("%s, %d\n", arr[0], ((Number)arr[1]).intValue());
		}
	}

	public List<Object[]> getAlbumsSalesQty(final Date begin, final Date end) {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Object[]>>() {
			public List<Object[]> doInHibernate(Session sess)
					throws HibernateException, SQLException {
				String sql = 
						"select a.title, sum(x.quantity) from Album a inner join " +
							"(select * from OrderDetail d inner join \"Order\" o on d.orderid=o.id " +
								"where o.orderdate>=? and  o.orderdate<?" +
							") x on a.id=x.albumid group by a.title";
				SQLQuery query = sess.createSQLQuery(sql);
				query.setDate(0, begin).setDate(1, end);
				return query.list();
			}
		});
	}
}
