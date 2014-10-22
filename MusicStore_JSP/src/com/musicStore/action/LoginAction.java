package com.musicStore.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.musicStore.dao.UserDao;
import com.musicStore.dao.impl.UserDaoImpl;
import com.musicStore.entity.User;

public class LoginAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("gbk");
		UserDao userDao = new UserDaoImpl();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.checkLogin(username, password);
		if(user != null){
			request.getSession().setAttribute("currentUser", user);
			if(user.getUserRoleId() == 2){
				response.sendRedirect("admin/admin_default.jsp");
			}
			else{
				response.sendRedirect("index.jsp");
			}
		}
		else{
			request.setAttribute("error","用户名或密码有误！");
			RequestDispatcher dispacher = request.getRequestDispatcher("../login.jsp");
			dispacher.forward(request, response);
		}
	}

}
