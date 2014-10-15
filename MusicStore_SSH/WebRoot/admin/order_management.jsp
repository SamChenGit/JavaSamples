<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
 
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
				<h3>订单管理</h3>
			</div>
			<div id="content-main">
				<form id="searchForm" action="orderManagement" method="get">
				发货状态：
				<select id="isDeliveried" name="isDeliveried">
					<option value="false" ${param.isDeliveried?"":"selected='selected'"}>未发货</option>
					<option value="true" ${param.isDeliveried?"selected='selected'":""}>已发货</option>
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;订单编号：
				<input type="text" id="orderId" name="orderId" value="${param.orderId}" />
				&nbsp;&nbsp;&nbsp;&nbsp;开始日期：<input type="text" id="begin" name="begin" value="${param.begin}" />
				&nbsp;&nbsp;&nbsp;&nbsp;结束日期：<input type="text" id="end" name="end" value="${param.end}" />
				&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" id="btnSearch" value="查询" />
				</form>
				 
				<hr/>
				<h3>订单列表：</h3>
				<table id="album-list">
					<tr><th>订单编号</th><th>订单日期</th><th>用户</th><th>总金额</th><th>状态</th><th>操作</th><th>详细</th></tr>
					<c:forEach var="o" items="${orderList}">
					<tr>
						<td>${o.id}</td><td>${o.orderDate}</td><td>${o.username}</td><td>${o.totalPrice}</td>
						<td>${o.isDeliveried?"已发货":"未发货"}</td>
						<td>
							<c:if test="${o.isDeliveried==false}">
							<form action="setOrderDeliveried" method="post">
								<input type="hidden" name="orderId" value="${o.id}"/>
								<button type="submit" onclick="return confirm('确定已发货？');">发货</button>
							</form>
							</c:if>
						</td>
						<td><a href="orderDetail?orderId=${o.id}">详细</a></td>
					</tr>
					</c:forEach>
				</table>
			</div>
    	</div>
    	<%@ include file="../shared/admin_footer.jsp" %>
    </div>
  </body>
</html>
