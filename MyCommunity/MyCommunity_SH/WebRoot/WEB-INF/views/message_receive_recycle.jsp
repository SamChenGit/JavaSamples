<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.myCommunity.com/tags" prefix="mc" %>
<ol class="breadcrumb">
	<li><a href="#">首页</a></li>
	<li><a>我的收件箱</a></li>
	<li><a>回收站</a></li>
</ol>
<div class="panel panel-default">
	<div class="panel-body">
		<c:import url="/message/message_menu" />
		<div id="message-panel" class="col-md-9 panel panel-default">
			<h5>回收站</h5>
			<table  class="table">
				<tr><th>标题</th><th>发件人</th><th>时间</th><th>操作</th></tr>
				<c:forEach var="m" items="${messages}">
				<tr>
					<td class="${m.readed?'readed':'unreaded'}">
						${m.messageSending.title}
					</td>
					<td>${m.messageSending.sender.username}</td>
					<td>${mc:dateTimeFormat(m.messageSending.sendTime)}</td>
					<td>
						<a href="message/receive_mark_normal?id=${m.id}">恢复</a>
						<a href="message/receive_mark_delete?id=${m.id}">清除</a>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
