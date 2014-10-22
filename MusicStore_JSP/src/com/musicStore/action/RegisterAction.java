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

public class RegisterAction extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("gbk");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");

		User user = new User(0,username,password,name,address,phone,email,1,true);
		UserDao userDao = new UserDaoImpl();
		boolean success = userDao.addUser(user);
		if(success){
			response.sendRedirect("login.jsp");
		}
		else{
			request.setAttribute("error", "Œ¥◊¢≤·≥…π¶£¨«Î…‘∫Û‘Ÿ≥¢ ‘°£");
			RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
			dispatcher.forward(request,response);
		}
	}

}
