package com.musicStore.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.musicStore.dao.UserDao;
import com.musicStore.dao.impl.UserDaoImpl;

public class DeleteUserAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.valueOf(request.getParameter("id"));
		UserDao userDao = new UserDaoImpl();
		userDao.deleteUser(id);
		response.sendRedirect("admin/user_management.jsp");
	}

}
