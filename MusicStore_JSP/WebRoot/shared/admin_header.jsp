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
				<h3>��̨����</h3>
				<ul>
					<li><a href="album_management.jsp">��Ʒ����</a></li>
					<li><a href="user_management.jsp">�û�����</a></li>
				</ul>
			</div>
		</div>