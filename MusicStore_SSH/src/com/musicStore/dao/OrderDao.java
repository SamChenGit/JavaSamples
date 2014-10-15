package com.musicStore.dao;
import java.sql.Date;
import java.util.List;

import com.musicStore.bean.Order;

public interface OrderDao {
	Order fetchByKey(int id);
	void add(Order order);
	void update(Order order);
	List<Order> getOrdersbyConditions(boolean isDeliveried, int orderId, Date begin, Date end);
}

