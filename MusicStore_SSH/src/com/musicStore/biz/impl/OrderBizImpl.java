package com.musicStore.biz.impl;

import java.sql.Date;
import java.util.List;

import com.musicStore.bean.Order;
import com.musicStore.bean.OrderDetail;
import com.musicStore.biz.AlbumBiz;
import com.musicStore.biz.OrderBiz;
import com.musicStore.dao.OrderDao;
import com.musicStore.dao.OrderDetailDao;

public class OrderBizImpl implements OrderBiz {
	
	private OrderDao orderDao;
	private OrderDetailDao orderDetailDao;
	private AlbumBiz albumBiz;
	public void setAlbumBiz(AlbumBiz albumBiz) {
		this.albumBiz = albumBiz;
	}
	public void setOrderDao(OrderDao orderDao){
		this.orderDao = orderDao;
	}	
	public void setOrderDetailDao(OrderDetailDao orderDetailDao){
		this.orderDetailDao = orderDetailDao;
	}	
	
	public void add(Order order) {
		orderDao.add(order);
		for(OrderDetail item : order.getOrderDetails()){
			albumBiz.updateStock(item.getAlbum().getId(), -item.getQuantity());
			orderDetailDao.add(item);
		}
	}

	public void updateOrderSetDeliveried(int orderId) {
		Order order = orderDao.fetchByKey(orderId);
		order.setIsDeliveried(true);
		orderDao.update(order);
	}

	public Order fetchByKey(int id) {
		return orderDao.fetchByKey(id);
	}

	public List<Order> getOrdersbyConditions(boolean isDeliveried, int orderID, Date begin, Date end){
		 return orderDao.getOrdersbyConditions(isDeliveried, orderID, begin, end);
	}
}
