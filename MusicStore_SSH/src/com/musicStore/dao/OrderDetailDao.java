package com.musicStore.dao;

import java.util.List;

import com.musicStore.bean.OrderDetail;

public interface OrderDetailDao {
	void add(OrderDetail detail);
	List<OrderDetail> getOrderDetailsByOrderId(int orderId);
	List<Object[]> getGenresDetailsSummary();
}
