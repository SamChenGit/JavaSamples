package com.musicStore.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.musicStore.biz.ShoppingCart;

public class RemoveCartItemAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
		int albumId = Integer.valueOf(request.getParameter("albumId"));
		cart.removeAlbumFromCart(albumId);
		response.sendRedirect("cart.jsp");
	}

}
