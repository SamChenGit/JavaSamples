package com.musicStore.action;


import java.sql.Date;
import java.util.Calendar;

import com.musicStore.bean.Album;
import com.musicStore.bean.CartItem;
import com.musicStore.bean.Order;
import com.musicStore.bean.OrderDetail;
import com.musicStore.bean.ShoppingCart;
import com.musicStore.bean.User;
import com.musicStore.biz.AlbumBiz;
import com.musicStore.biz.OrderBiz;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OrderConfirmAction extends ActionSupport {
	
	private AlbumBiz albumBiz;
	private Order order;
	private OrderBiz orderBiz;
	public void setOrderBiz(OrderBiz orderBiz) {
		this.orderBiz = orderBiz;
	}
	public void setAlbumBiz(AlbumBiz albumBiz){
		this.albumBiz = albumBiz;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public ShoppingCart getShppingCart(){
		ShoppingCart cart = (ShoppingCart)ActionContext.getContext().getSession().get("shoppingCart");
		return cart;
	}
	public void setShoppingCart(ShoppingCart cart){
		ActionContext.getContext().getSession().put("shoppingCart", cart);
	}
	public User getLoginUser(){
		User user = (User)ActionContext.getContext().getSession().get("loginUser");
		return user;
	}
	
	public String orderConfirm(){
		return SUCCESS;
	}
	
	public String orderSave(){
		this.order.setUsername(this.getLoginUser().getUsername());
		this.order.setOrderDate(new Date(Calendar.getInstance().getTimeInMillis()));
		for(CartItem item : this.getShppingCart().getItems()){
			OrderDetail detail = new OrderDetail();
			Album album = new Album();
			album.setId(item.getAlbumId());
			detail.setAlbum(album);
			detail.setQuantity(item.getQuantity());
			detail.setUnitPrice(item.getPrice());
			detail.setOrder(order);
			this.order.getOrderDetails().add(detail);
			this.order.setTotalPrice(order.getTotalPrice()+detail.getSubTotal());
		}
		this.orderBiz.add(order);	//调用业务存保存
		this.setShoppingCart(null); //清空Session
		return SUCCESS;
	}
}
