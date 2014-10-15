package com.musicStore.action;

import com.musicStore.bean.ShoppingCart;
import com.musicStore.biz.ShoppingCartBiz;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ShoppingCartAction extends ActionSupport {
		
	private ShoppingCartBiz shoppingCartBiz;
	public void setShoppingCartBiz(ShoppingCartBiz shoppingCartBiz){
		this.shoppingCartBiz = shoppingCartBiz;
	}
	
	private int albumId;
	public void setAlbumId(int albumId){
		this.albumId = albumId;
	}
	
	public ShoppingCart getShoppingCart(){
		ShoppingCart cart = (ShoppingCart)ActionContext.getContext().getSession().get("shoppingCart");
		if(cart == null){
			cart = new ShoppingCart();
			ActionContext.getContext().getSession().put("shoppingCart", cart);
		}
		return cart;
	}
	
	public String shoppingCart(){
		return SUCCESS;
	}
	
	public String addAlbumToShoppingCart(){
		shoppingCartBiz.addAlbumToCart(this.getShoppingCart(), this.albumId);
		return SUCCESS;
	}
	
	public String removeAlbumFromShoppingCart(){
		shoppingCartBiz.removeAlbumFromCart(this.getShoppingCart(), this.albumId);
		return SUCCESS;
	}
}
