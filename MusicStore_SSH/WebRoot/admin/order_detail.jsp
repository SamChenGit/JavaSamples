<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="func"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>Music Store 后台商品管理</title>
	<link type="text/css" rel="Stylesheet" href="/MusicStore/style/admin.css"/>
	<link type="text/css" rel="stylesheet" href="../style/jquery-ui/jquery-ui-1.8.24.custom.css" />
	<script type="text/javascript" src="../script/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="../script/jquery-ui-1.8.24.custom.min.js"></script>
    <script type="text/javascript" src="../script/jquery.ui.datepicker-zh-CN.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$("#begin").datepicker({dateFormat:"yy-mm-dd"});
    		$("#end").datepicker({dateFormat:"yy-mm-dd"});
    	});
    </script>
  </head>
  
  <body>
    <div id="wrapper">&nbsp; 
    	<%@ include file="../shared/admin_header.jsp" %>
    	<div id="content">
    		<div id="content-title">
				<h3>订单详情</h3>
			</div>
			<div id="content-main">
				<a href="orderManagement">返回</a>
				<table id="order-list">
					<caption>送货信息</caption>
					<tr><td>订单编号</td><td>${order.id}</td><td>订单日期</td><td>${order.orderDate}</td></tr>
					<tr><td>用户</td><td>${order.username}</td><td>收货人</td><td>${order.consignee}</td></tr>
					<tr><td>联系电话</td><td>${order.phone}</td><td>email</td><td>${order.email}</td></tr>
					<tr><td>送货地址</td><td colspan="3">${order.address}</td></tr>
				</table>
				<hr/>
				<table id="album-list">
					<caption>明细信息</caption>
					<tr>
						<th>唱片</th><th>单价</th><th>数量</th><th>小计</th>
					</tr>
					<c:forEach var="item" items="${order.orderDetails}">
					<tr>
						<td>${item.album.title}</td>
						<td>${item.quantity}</td>
						<td><fmt:formatNumber type="currency" value="${item.unitPrice}"  currencySymbol="￥" /></td>
						<td>${item.subTotal}</td>
					</tr>
					</c:forEach>
					<tr>
						<td></td><td></td><td>合计</td><td><fmt:formatNumber type="currency" value="${order.totalPrice}"  currencySymbol="￥" /></td>
					</tr>
				</table>
			</div>
    	</div>
    	<%@ include file="../shared/admin_footer.jsp" %>
    </div>
  </body>
</html>
