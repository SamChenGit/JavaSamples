<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.myCommunity.com/tags" prefix="mc" %>
<ol class="breadcrumb">
	<li><a href="#">首页</a></li>
	<li><a>我的发件箱</a></li>
	<li><a href="#">回收站</a></li>
</ol>
<div class="panel panel-default">
	<div class="panel-body">
		<c:import url="/message/message_menu" />
		<div id="message-panel" class="col-md-9 panel panel-default">
			<h5>发件箱-回收站</h5>
			<table  class="table">
				<tr><th>标题</th><th>收件人</th><th>时间</th><th>操作</th></tr>
				<c:forEach var="m" items="${messages}">
				<tr>
					<td>${m.title}</td>
					<td>${mc:lenLimit(m.receivers,10)}</td>
					<td>${mc:dateTimeFormat(m.sendTime)}</td>
					<td>
						<a href="message/send_mark_sended?id=${m.id}">恢复</a>
						<a href="message/send_mark_delete?id=${m.id}">清除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
