<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib uri="/struts-tags" prefix="s"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>收货信息</title>
	<link type="text/css" rel="Stylesheet" href="/MusicStore/style/front.css"/>
	<script type="text/javascript" src="/MusicStore/script/jquery-1.4.1.js"></script>
	<style type="text/css">
		.order_txt{width:500px;}
	</style>
  </head>
  
  <body>
    <div id="wrapper">
   	 	<%@ include file="../shared/front_header.jsp" %>
    	<div id="content">
    		<s:action name="genreSubList" executeResult="true" namespace="/" />
    		<div id="main">
    			<h3 id="main-title">订单明细</h3>
				<table id="shoppingCart">
					<tr>
						<th>唱片</th><th>单价</th><th>数量</th><th>小计</th>
					</tr>
					<c:forEach var="item" items="${shoppingCart.items}">
					<tr>
						<td>${item.title}</td>
						<td><fmt:formatNumber type="currency" value="${item.price}" currencySymbol="￥" /></td>
						<td>${item.quantity}</td>
						<td><fmt:formatNumber type="currency" value="${item.subTotal}" currencySymbol="￥" /></td>
					</tr>
					</c:forEach>
					<tr>
						<td></td><td></td><td>合计</td><td><fmt:formatNumber type="currency" value="${shoppingCart.total}" currencySymbol="￥" /></td><td></td>
					</tr>
				</table>
				<h3 id="main-title">收货人信息</h3>
				<s:form id="loginForm" action="orderSave" method="post">
				<table>
					<tr><td>收货人：</td><td><input type="text" name="order.consignee" value="${loginUser.name}" class="order_txt" /></td></tr>
					<tr><td>联系地址：</td><td><input type="text" name="order.address" value="${loginUser.address}" class="order_txt" /></td></tr>
					<tr><td>联系电话：</td><td><input type="text" name="order.phone" value="${loginUser.phone}" class="order_txt" /></td></tr>
					<tr><td>Email：</td><td><input type="text" name="order.email" value="${loginUser.email}" class="order_txt" /></td></tr>
					<tr><td></td><td><input type="submit" value="确定购买" /></td></tr>
				</table>
				</s:form>
    		</div>
    		<div class="clearBoth"></div>
    	</div>
    	<%@ include file="../shared/front_footer.jsp" %>
    </div>
  </body>
</html>