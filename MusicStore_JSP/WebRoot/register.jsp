<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.musicStore.entity.*"  %>
<%@ page import="com.musicStore.dao.*"  %>
<%@ page import="com.musicStore.dao.impl.*"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>欢迎光临 Music Store</title>
	<link type="text/css" rel="Stylesheet" href="style/front.css"/>
	<script type="text/javascript" src="script/jquery-1.4.1.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#registerForm").submit(checkInput);
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
			if($("#passwordAgain").val() != $("#password").val() ){
				$("#passwordAgain").next(".error").text("两次输入的密码需一致。");
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
    			<h3 id="main-title">用户注册</h3>
    			<form id="registerForm" action="RegisterAction" method="post">
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
    					<td>密码确认：</td>
    					<td><input class="txt" type="password" id="passwordAgain" name="passwordAgain" /> <label class="error"></label></td>
    				</tr>
    				<tr>
    					<td>姓名：</td>
    					<td><input class="txt" type="text" id="name" name="name" /> <label class="error"></label></td>
    				</tr>
    				<tr>
    					<td>地址：</td>
    					<td><input class="txt" type="text" id="address" name="address" /> <label class="error"></label></td>
    				</tr>
    				<tr>
    					<td>电话：</td>
    					<td><input class="txt" type="text" id="phone" name="phone" /> <label class="error"></label></td>
    				</tr>
    				<tr>
    					<td>Email：</td>
    					<td><input class="txt" type="text" id="email" name="email" /> <label class="error"></label></td>
    				</tr>
    				<tr>
    					<td></td>
    					<td>
    						<input id="btnSubmit" type="submit" name="btnSubmit" value="注册" />
    					</td>
    				</tr>
    				<tr>
    					<td colspan="2"><%=request.getAttribute("error")==null?"":request.getAttribute("error") %></td>
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