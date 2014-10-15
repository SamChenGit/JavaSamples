package com.musicStore.bean;

import java.util.ArrayList;
import java.util.List;
 
import com.musicStore.bean.CartItem;;

public class ShoppingCart {
	
	private List<CartItem> items = new ArrayList<CartItem>();

	public List<CartItem> getItems() {
		return items;
	}

	public double getTotal(){
		double sum = 0;
		for(CartItem item : items){
			sum += item.getSubTotal();
		}
		return sum;
	}
}
