package com.musicStore.biz;

import com.musicStore.bean.CartItem;
import com.musicStore.bean.ShoppingCart;


public interface ShoppingCartBiz {

	void addAlbumToCart(ShoppingCart cart, int albumId);

	void removeAlbumFromCart(ShoppingCart cart, int albumId);

	CartItem findAlbumInCart(ShoppingCart cart, int albumId);
}