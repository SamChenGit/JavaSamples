<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.musicStore.entity.*"  %>
<%@ page import="com.musicStore.dao.*"  %>
<%@ page import="com.musicStore.dao.impl.*"  %>
<%@ page import="com.musicStore.biz.*"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%
	int cartItemsSize = 0;
	ShoppingCart cart = (ShoppingCart)request.getSession().getAttribute("cart");
	if(cart != null)
		cartItemsSize = cart.getItems().size();
	request.setAttribute("cartItemsSize", cartItemsSize);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>购物车</title>
	<link type="text/css" rel="Stylesheet" href="style/front.css"/>
	<script type="text/javascript" src="script/jquery-1.4.1.js"></script>
  </head>
  
  <body>
    <div id="wrapper">
   	 	<%@ include file="shared/front_header.jsp" %>
    	<div id="content">
    		<%@ include file="shared/front_sidebar.jsp" %>
    		<div id="main">
    			<h3 id="main-title">购物车</h3>
    			<c:if test="${cartItemsSize != 0}">
				<table id="shoppingCart">
					<tr>
						<th>唱片</th><th>单价</th><th>数量</th><th>小计</th><th>删除</th>
					</tr>
					<c:forEach var="item" items="${sessionScope.cart.items}">
					<tr>
						<td>${item.title}</td>
						<td>${item.price}</td>
						<td>${item.quantity}</td>
						<td>${item.subTotal}</td>
						<td>
							<form method="post" action="RemoveCartItemAction">
							<input type="hidden" name="albumId" value="${item.albumId}"/>
							<input type="submit" value="删除" />
							</form>
						</td>
					</tr>
					</c:forEach>
					<tr>
						<td></td><td></td><td>合计</td><td>${sessionScope.cart.total}</td><td></td>
					</tr>
				</table>
				<input id="orderSubmit" type="submit" value="订单确认" />
				</c:if>
				<c:if test="${cartItemsSize == 0}">
				<label id="nullCartTips">您的购物车是空的。</label>
				</c:if>
    		</div>
    		<div class="clearBoth"></div>
    	</div>
    	<%@ include file="shared/front_footer.jsp" %>
    </div>
  </body>
</html>
