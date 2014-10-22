<%@ page language="java" contentType="text/html; charset=gbk"pageEncoding="gbk"%>
<%@ page import="com.musicStore.entity.*" %>
<%
	if(session.getAttribute("currentUser") == null || ((User)session.getAttribute("currentUser")).getUserRoleId()!=2){
		response.sendRedirect("../login.jsp");
	}
%>
		<div id="header">
			<div id="header-logo" >
				<a href="../index.jsp"><img src="../style/images/logo.png" alt=""></img></a>
			</div>
			<div id="header-nav">
				<h3>后台管理</h3>
				<ul>
					<li><a href="album_management.jsp">商品管理</a></li>
					<li><a href="user_management.jsp">用户管理</a></li>
				</ul>
			</div>
		</div>