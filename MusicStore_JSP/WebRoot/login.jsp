<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.musicStore.entity.*"  %>
<%@ page import="com.musicStore.dao.*"  %>
<%@ page import="com.musicStore.dao.impl.*"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>欢迎光临 Music Store</title>
	<link type="text/css" rel="Stylesheet" href="/MusicStore/style/front.css"/>
	<script type="text/javascript" src="/MusicStore/script/jquery-1.4.1.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#loginForm").submit(checkInput);
		});
		function checkInput(){
			$("label.error").text("");
			var pass = true;
			if($("#username").val() == ""){
				$("#username").next(".error").text("请填写用户名。");
				pass = false;
			}
			if($("#password").val() == ""){
				$("#password").next(".error").text("请填写密码。");
				pass = false;
			}
			return pass;
		}
	</script>
  </head>
  
  <body>
    <div id="wrapper">
   	 	<%@ include file="shared/front_header.jsp" %>
    	<div id="content">
    		<%@ include file="shared/front_sidebar.jsp" %>	
    		<div id="main">
    			<h3 id="main-title">用户登录</h3>
    			<form id="loginForm" action="/MusicStore/LoginAction" method="post">
    			<table>
    				<tr>
    					<td>用户名：</td>
    					<td><input class="txt" type="text" id="username" name="username" /> <label class="error"></label></td>
    				</tr>
    				<tr>
    					<td>密码：</td>
    					<td><input class="txt" type="password" id="password" name="password" /> <label class="error"></label></td>
    				</tr>
    				<tr>
    					<td></td>
    					<td>
    						<input id="btnSubmit" type="submit" name="btnSubmit" value="登录" />
    						<a href="register.jsp">注册</a>
    					</td>
    				</tr>
    				<tr>
    					<td colspan="2" align="center">
    					<label class="error"><%=request.getAttribute("error")==null?"":request.getAttribute("error")%></label>
    					</td>
    				</tr>
    			</table>
    			</form>
    		</div>
    		<div class="clearBoth"></div>
    	</div>
    	<%@ include file="shared/front_footer.jsp" %>
    </div>
  </body>
</html>
