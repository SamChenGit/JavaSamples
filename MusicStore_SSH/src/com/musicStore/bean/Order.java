package com.musicStore.bean;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {
	public Order(){}
	public Order(int id, Date orderDate, String username, String consignee,
			String address, String phone, String email, double totalPrice,
			boolean isDeliveried) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.username = username;
		this.consignee = consignee;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.totalPrice = totalPrice;
		this.isDeliveried = isDeliveried;
	}
	private int id;
	private Date orderDate;
	private String username;
	private String consignee;
	private String address;
	private String phone;
	private String email;
	private double totalPrice;
	private boolean isDeliveried;
	private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getConsignee() {
		return consignee;
	}
	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public boolean getIsDeliveried() {
		return isDeliveried;
	}
	public void setIsDeliveried(boolean isDeliveried) {
		this.isDeliveried = isDeliveried;
	}
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
