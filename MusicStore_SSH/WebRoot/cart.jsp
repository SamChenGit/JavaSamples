<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="func"  %>
<%@ taglib uri="/struts-tags" prefix="s"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>购物车</title>
	<link type="text/css" rel="Stylesheet" href="/MusicStore/style/front.css"/>
	<script type="text/javascript" src="/MusicStore/script/jquery-1.5.1.js"></script>
  </head>
  
  <body>
    <div id="wrapper">
   	 	<%@ include file="shared/front_header.jsp" %>
    	<div id="content">
    		<s:action name="genreSubList" executeResult="true" />
    		<div id="main">
    			<h3 id="main-title">购物车</h3>
    			<c:if test="${func:length(shoppingCart.items)!=0}">
				<table id="shoppingCart">
					<tr>
						<th>唱片</th><th>单价</th><th>数量</th><th>小计</th><th>删除</th>
					</tr>
					<c:forEach var="item" items="${shoppingCart.items}">
					<tr>
						<td>${item.title}</td>
						<td><fmt:formatNumber type="currency" value="${item.price}" currencySymbol="￥" /></td>
						<td>${item.quantity}</td>
						<td><fmt:formatNumber type="currency" value="${item.subTotal}" currencySymbol="￥" /></td>
						<td>
							<s:form method="post" action="removeAlbumFromShoppingCart">
							<input type="hidden" name="albumId" value="${item.albumId}"/>
							<input type="submit" value="删除" />
							</s:form>
						</td>
					</tr>
					</c:forEach>
					<tr>
						<td></td><td></td><td>合计</td><td><fmt:formatNumber type="currency" value="${shoppingCart.total}"  currencySymbol="￥" /></td><td></td>
					</tr>
				</table>
				<s:form action="orderConfirm">
				<input id="orderSubmit" type="submit" value="订单确认" />
				</s:form>
				</c:if>
				<c:if test="${func:length(shoppingCart.items)==0}">
				<label id="nullCartTips">您的购物车是空的。</label>
				</c:if>
    		</div>
    		<div class="clearBoth"></div>
    	</div>
    	<%@ include file="shared/front_footer.jsp" %>
    </div>
  </body>
</html>
