<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page import="com.musicStore.entity.*" %>
<% User currentUser = (User)request.getSession().getAttribute("currentUser"); %>
   	 	<div id="header">
	  		<div id="header-logo" >
	  			<a href="index.jsp"><img src="style/images/logo.png" alt=""></img></a>
	  		</div>
	  		<div id="header-login"><a href="login.jsp">��¼</a> | <a href="register.jsp">ע��</a></div>
	  		<div class="clearBoth"></div>
	  		<div id="header-search">
	  			<form id="searchForm" action="album_list.jsp" method="get">
	  			<input id="keyword" name="title" type="text"  /><input id="btnSearch" type="submit" value="����" />
	  			</form>
	  		</div>
	  		<div id="header-nav">
	  			<ul>
	  				<li><a href="index.jsp">��ҳ</a></li>
	  				<li><a href="album_list.jsp">�̵�</a></li>
	  				<li><a href="cart.jsp">���ﳵ</a></li>
	  				<li><a href="edit_user.jsp">������Ϣ</a></li>
	  			</ul>
	  			<label id="welcome">
	  				<% if(currentUser != null) { %>
	  				���ã�<%=currentUser.getUsername() %>��
	  				<% } %>
	  			</label>
	  		</div>
	  	</div>