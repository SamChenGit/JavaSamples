package com.musicStore.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.musicStore.bean.Order;
import com.musicStore.bean.OrderDetail;
import com.musicStore.dao.OrderDao;

public class OrderDaoImpl extends HibernateDaoSupport implements OrderDao {

	public void add(Order order) {
		getHibernateTemplate().save(order);
	}

	public void update(Order order) {
		getHibernateTemplate().update(order);
	}

	public Order fetchByKey(int id) {
		return getHibernateTemplate().get(Order.class, id);
	}

	public List<Order> getOrdersbyConditions(boolean isDeliveried, int orderId, Date begin, Date end){
		List parameters = new ArrayList();
		String hql = "from Order o where o.isDeliveried=?";
		parameters.add(isDeliveried);
		if(orderId>0){
			hql += " and o.id=?";
			parameters.add(orderId);
		}
		if(begin != null){
			hql += " and o.orderDate>=?";
			parameters.add(begin);
		}
		if(end != null){
			hql += " and o.orderDate<=?";
			parameters.add(end);
		}
		return getHibernateTemplate().find(hql, parameters.toArray());
	}
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		OrderDao dao = (OrderDao)ctx.getBean("orderDao");
		Order order = dao.fetchByKey(1);
		System.out.println("------------------------销售订单--------------------------");
		System.out.printf("订单号：%d\t\t订单日期：%s\n", order.getId(), order.getOrderDate());
		System.out.printf("用户名：%s\t电子邮件：%s\n", order.getUsername(), order.getEmail());
		System.out.printf("收货人：%s\t联系电话：%s\n", order.getConsignee(), order.getPhone());
		System.out.printf("送货地址：%s\n", order.getAddress());
		System.out.println("------------------------单据明细--------------------------");
		for(OrderDetail i : order.getOrderDetails()){
			System.out.printf("唱片：%s\t 单价：%f\t 数量：%d\t 小计：%f\n", 
					i.getAlbum().getTitle(), i.getUnitPrice(), i.getQuantity(), i.getSubTotal());
		}
	}
}
