package com.musicStore.biz;

import java.sql.Date;
import java.util.List;
import com.musicStore.bean.Order;

public interface OrderBiz {
	Order fetchByKey(int id);  	//通过Id获取订单信息
	void add(Order order);		//新增订单
	void updateOrderSetDeliveried(int orderId);	//把订单设置为已发货状态
	//根据条件获取订单信息（isDeliveried是否已发货、orderID订单编号、begin开始日期、end结束日期）
	List<Order> getOrdersbyConditions(boolean isDeliveried, int orderID, Date begin, Date end);
}

