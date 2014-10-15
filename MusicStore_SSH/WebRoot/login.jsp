<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>欢迎光临 Music Store</title>
	<link type="text/css" rel="Stylesheet" href="/MusicStore/style/front.css"/>
	<script type="text/javascript" src="/MusicStore/script/jquery-1.5.1.js"></script>
  </head>
  
  <body>
    <div id="wrapper">
    	<%@ include file="shared/front_header.jsp" %>
    	<div id="content">
    		<s:action name="genreSubList" executeResult="true" namespace="/" />
    		<div id="main">
    			<h3 id="main-title">用户登录</h3>
    			<s:form id="loginForm" action="login" method="post" theme="simple">
    			<table>
    				<tr>
    					<td>用户名：</td>
    					<td><input class="txt" type="text" id="username" name="user.username" value="${user.username}" />
    						<s:fielderror cssStyle="display:inline;" fieldName="user.username" /></td>
    				</tr>
    				<tr>
    					<td>密码：</td>
    					<td><input class="txt" type="password" id="password" name="user.password" />
    						<s:fielderror cssStyle="display:inline;" fieldName="user.password" /></td>
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
    					<label class="error">${errorMsg}</label>
    					</td>
    				</tr>
    			</table>
    			</s:form>
    		</div>
    		<div class="clearBoth"></div>
    	</div>
    	<%@ include file="shared/front_footer.jsp" %>
    </div>
  </body>
</html>