package com.musicStore.biz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.musicStore.dao.AlbumDao;
import com.musicStore.dao.impl.AlbumDaoImpl;
import com.musicStore.entity.Album;

public class ShoppingCart {
	
	private AlbumDao albumDao = new AlbumDaoImpl();
	
	private List<CartItem> items = new ArrayList<CartItem>();

	public List<CartItem> getItems() {
		return items;
	}
	
	public BigDecimal getTotal(){
		BigDecimal sum = BigDecimal.valueOf(0);
		for(CartItem item : items){
			sum = sum.add(item.getSubTotal());
		}
		return sum;
	}
	
	public void addAlbumToCart(int albumId){
		CartItem item = findAlbumInCart(albumId);
		if(item == null){
			Album album = albumDao.fetchAlbumByKey(albumId);
			item = new CartItem();
			item.setAlbumId(album.getId());
			item.setTitle(album.getTitle());
			item.setPrice(album.getPrice());
			item.setQuantity(1);
			this.items.add(item);
		}
		else{
			item.setQuantity(item.getQuantity()+1);
		}
	}
	
	public void removeAlbumFromCart(int albumId){
		CartItem item = findAlbumInCart(albumId);
		this.items.remove(item);
	}
	
	public CartItem findAlbumInCart(int albumId){
		for(CartItem item : items){
			if(item.getAlbumId() == albumId)
				return item;
		}
		return null;
	}
	
}
