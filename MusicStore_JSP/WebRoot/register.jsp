<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.musicStore.entity.*"  %>
<%@ page import="com.musicStore.dao.*"  %>
<%@ page import="com.musicStore.dao.impl.*"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>��ӭ���� Music Store</title>
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
				$("#username").next(".error").text("����д�û�����");
				pass = false;
			}
			if($("#password").val() == ""){
				$("#password").next(".error").text("����д���롣");
				pass = false;
			}
			if($("#passwordAgain").val() != $("#password").val() ){
				$("#passwordAgain").next(".error").text("���������������һ�¡�");
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
    			<h3 id="main-title">�û�ע��</h3>
    			<form id="registerForm" action="RegisterAction" method="post">
    			<table>
    				<tr>
    					<td>�û�����</td>
    					<td><input class="txt" type="text" id="username" name="username" /> <label class="error"></label></td>
    				</tr>
    				<tr>
    					<td>���룺</td>
    					<td><input class="txt" type="password" id="password" name="password" /> <label class="error"></label></td>
    				</tr>
    				<tr>
    					<td>����ȷ�ϣ�</td>
    					<td><input class="txt" type="password" id="passwordAgain" name="passwordAgain" /> <label class="error"></label></td>
    				</tr>
    				<tr>
    					<td>������</td>
    					<td><input class="txt" type="text" id="name" name="name" /> <label class="error"></label></td>
    				</tr>
    				<tr>
    					<td>��ַ��</td>
    					<td><input class="txt" type="text" id="address" name="address" /> <label class="error"></label></td>
    				</tr>
    				<tr>
    					<td>�绰��</td>
    					<td><input class="txt" type="text" id="phone" name="phone" /> <label class="error"></label></td>
    				</tr>
    				<tr>
    					<td>Email��</td>
    					<td><input class="txt" type="text" id="email" name="email" /> <label class="error"></label></td>
    				</tr>
    				<tr>
    					<td></td>
    					<td>
    						<input id="btnSubmit" type="submit" name="btnSubmit" value="ע��" />
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