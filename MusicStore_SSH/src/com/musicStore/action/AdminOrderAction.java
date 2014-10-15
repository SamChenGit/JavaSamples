package com.musicStore.action;

import java.sql.Date;
import java.util.List;

import com.musicStore.bean.Order;
import com.musicStore.biz.OrderBiz;
import com.opensymphony.xwork2.ActionSupport;

public class AdminOrderAction extends ActionSupport {
	
	private OrderBiz orderBiz;
	public void setOrderBiz(OrderBiz orderBiz){
		this.orderBiz = orderBiz;
	}
	
	private List<Order> orderList;
	public List<Order> getOrderList() {
		return orderList;
	}
	private int orderId;
	private boolean isDeliveried;
	private Date begin;
	private Date end;
	private Order order;
	public void setIsDeliveried(boolean isDeliveried) {
		this.isDeliveried = isDeliveried;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public void setOrderId(int orderId){
		this.orderId = orderId;
	}
	public Order getOrder(){
		return this.order;
	}

	public String orderManagement(){
		this.orderList = orderBiz.getOrdersbyConditions(isDeliveried, orderId, begin, end);
		return SUCCESS;
	}
	public String setOrderDeliveried(){
		orderBiz.updateOrderSetDeliveried(this.orderId);
		this.orderList = orderBiz.getOrdersbyConditions(isDeliveried, orderId, begin, end);
		return SUCCESS;
	}
	public String orderDetail(){
		this.order = this.orderBiz.fetchByKey(this.orderId);
		return SUCCESS;
	}
}
