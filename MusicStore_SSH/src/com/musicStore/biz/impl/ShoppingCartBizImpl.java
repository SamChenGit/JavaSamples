package com.musicStore.biz.impl;

import com.musicStore.bean.Album;
import com.musicStore.bean.CartItem;
import com.musicStore.bean.ShoppingCart;
import com.musicStore.biz.ShoppingCartBiz;
import com.musicStore.dao.AlbumDao;

public class ShoppingCartBizImpl implements ShoppingCartBiz {
	
	private AlbumDao albumDao;
	public void setAlbumDao(AlbumDao albumDao){
		this.albumDao = albumDao; 
	}
	
	public void addAlbumToCart(ShoppingCart cart, int albumId){
		CartItem item = findAlbumInCart(cart, albumId);
		if(item == null){
			Album album = albumDao.fetchAlbumById(albumId);
			item = new CartItem();
			item.setAlbumId(album.getId());
			item.setTitle(album.getTitle());
			item.setPrice(album.getPrice());
			item.setQuantity(1);
			cart.getItems().add(item);
		}
		else{
			item.setQuantity(item.getQuantity()+1);
		}
	}

	public void removeAlbumFromCart(ShoppingCart cart, int albumId){
		CartItem item = findAlbumInCart(cart, albumId);
		cart.getItems().remove(item);
	}

	public CartItem findAlbumInCart(ShoppingCart cart, int albumId){
		for(CartItem item : cart.getItems()){
			if(item.getAlbumId() == albumId)
				return item;
		}
		return null;
	}
}
