package com.musicStore.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.musicStore.bean.OrderDetail;
import com.musicStore.dao.OrderDetailDao;

public class OrderDetailDaoImpl extends HibernateDaoSupport implements
		OrderDetailDao {

	public void add(OrderDetail detail) {
		getHibernateTemplate().save(detail);
	}

	public List<OrderDetail> getOrderDetailsByOrderId(int orderId) {
		return null;
	}

	public List<Object[]> getGenresDetailsSummary() {
		return getHibernateTemplate().executeFind(new HibernateCallback<List<Object[]>>() {
			public List<Object[]> doInHibernate(Session sess)
					throws HibernateException, SQLException {
				return sess.createSQLQuery("select x.name, count(d.quantity) from Orderdetail d inner join (select a.*, g.name from Album a inner join Genre g on a.genreid=g.id) x on d.albumid=x.id group by x.name")
					.list();
			}
		});
	}
}
