<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>欢迎光临 Music Store</title>
	<link type="text/css" rel="Stylesheet" href="/MusicStore/style/front.css"/>
	<script type="text/javascript" src="script/jquery-1.5.1.js"></script>
    <script type="text/javascript" src="script/jquery.validate.js"></script>
    <script type="text/javascript">
        //扩展验证方式：正则验证
        $.validator.addMethod(
            "regExp",
            function(value, element, expression) {
                var rexp = new RegExp(expression);
                return rexp.test(value);
            },
            "所填写资料不符合格式。"
        );
        $(function () {
            $('#loginForm').validate({
	            rules: {
	                'user.username': { required: true , remote: 'checkUsernameNotDuplicated'},
	                'user.password': { required: true },
	                passwordAgain: { equalTo: "#password" },
	                'user.email': { email: true }
	            },
	            messages: {
	                'user.username': { required: "请填写用户名", remote: '用户名已重复，请重新指定' },
	                'user.password': { required: "请填写密码" },
	                passwordAgain: { equalTo: "两次输入的密码应一致" },
	                'user.email': { email: "email格式应符合规范" }
	            }
            });
        });
    </script>
  </head>
  
  <body>
    <div id="wrapper">
    	<%@ include file="shared/front_header.jsp" %>
    	<div id="content">
    		<s:action name="genreSubList" executeResult="true" namespace="/"  />
    		<div id="main">
   			<h3 id="main-title">用户注册</h3>
   				<s:form id="loginForm" action="register" method="post">
    			<table>
    				<tr>
    					<td>用户名：</td>
    					<td><input class="txt" type="text" id="username" name="user.username" value="${user.username}" /> 
    						<s:fielderror fieldName="user.username" /></td>
    				</tr>
    				<tr>
    					<td>密码：</td>
    					<td><input class="txt" type="password" id="password" name="user.password" />
    					<s:fielderror fieldName="user.password" /></td>
    				</tr>
    				<tr>
    					<td>密码确认：</td>
    					<td><input class="txt" type="password" id="passwordAgain" name="passwordAgain" />
    						<s:fielderror fieldName="passwordAgain" /></td>
    				</tr>
    				<tr>
    					<td>姓名：</td>
    					<td><input class="txt" type="text" id="name" name="user.name"  value="${user.name}" /></td>
    				</tr>
    				<tr>
    					<td>地址：</td>
    					<td><input class="txt" type="text" id="address" name="user.address"  value="${user.address}" /></td>
    				</tr>
    				<tr>
    					<td>电话：</td>
    					<td><input class="txt" type="text" id="phone" name="user.phone"  value="${user.phone}"/></td>
    				</tr>
    				<tr>
    					<td>Email：</td>
    					<td><input class="txt" type="text" id="email" name="user.email"  value="${user.email}" />
    						<s:fielderror fieldName="user.email" /></td>
    				</tr>
    				<tr>
    					<td></td>
    					<td>
    						<input id="btnSubmit" type="submit" name="btnSubmit" value="注册" />
    					</td>
    				</tr>
    				<tr>
    					<td colspan="2">${error}</td>
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