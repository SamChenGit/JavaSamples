<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page import="com.musicStore.entity.*" %>
<% User currentUser = (User)request.getSession().getAttribute("currentUser"); %>
   	 	<div id="header">
	  		<div id="header-logo" >
	  			<a href="index.jsp"><img src="style/images/logo.png" alt=""></img></a>
	  		</div>
	  		<div id="header-login"><a href="login.jsp">登录</a> | <a href="register.jsp">注册</a></div>
	  		<div class="clearBoth"></div>
	  		<div id="header-search">
	  			<form id="searchForm" action="album_list.jsp" method="get">
	  			<input id="keyword" name="title" type="text"  /><input id="btnSearch" type="submit" value="搜索" />
	  			</form>
	  		</div>
	  		<div id="header-nav">
	  			<ul>
	  				<li><a href="index.jsp">首页</a></li>
	  				<li><a href="album_list.jsp">商店</a></li>
	  				<li><a href="cart.jsp">购物车</a></li>
	  				<li><a href="edit_user.jsp">个人信息</a></li>
	  			</ul>
	  			<label id="welcome">
	  				<% if(currentUser != null) { %>
	  				您好，<%=currentUser.getUsername() %>。
	  				<% } %>
	  			</label>
	  		</div>
	  	</div>