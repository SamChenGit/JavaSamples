package com.musicStore.bean;

public class OrderDetail {
	public OrderDetail(){}
	public OrderDetail(int id, Order order, Album album, int quantity,
			double unitPrice) {
		super();
		this.id = id;
		this.order = order;
		this.album = album;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
	private int id;
	private Order order;
	private Album album;
	private int quantity;
	private double unitPrice;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getSubTotal(){
		return this.unitPrice * this.quantity;
	}
}
