<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.musicStore.entity.*"  %>
<%@ page import="com.musicStore.dao.*"  %>
<%@ page import="com.musicStore.dao.impl.*"  %>
<%
	UserDao userDao = new UserDaoImpl();
	List<User> list = userDao.getAllUsers();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>Music Store ��̨��Ʒ�༭</title>
	<link type="text/css" rel="Stylesheet" href="../style/admin.css"/>
  </head>
  <body>
    <div id="wrapper">
		<%@ include file="../shared/admin_header.jsp" %>
    	<div id="content">
    		<div id="content-title">
				<h3>�û�����</h3>
			</div>
			<div id="content-main">
				<table id="user-list">
					<tr><th>�û���</th><th>����</th><th>��ַ</th><th>�绰</th><th>Email</th><th>ɾ��</th></tr>
					<% for(User u : list) { %>
					<tr>
						<td><%=u.getUsername()%></td>
						<td><%=u.getName()%></td>
						<td><%=u.getAddress()%></td>
						<td><%=u.getPhone()%></td>
						<td><%=u.getEmail()%></td>
						<td><a href="../DeleteUserAction?id=<%=u.getId()%>">ɾ��</a></td>
					</tr>
					<% } %>
				</table>
			</div>
    	</div>
    	<%@ include file="../shared/admin_footer.jsp" %>
    </div>
  </body>
</html>