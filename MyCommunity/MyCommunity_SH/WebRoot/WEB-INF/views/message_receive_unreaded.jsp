<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.myCommunity.com/tags" prefix="mc" %>
<ol class="breadcrumb">
	<li><a href="#">首页</a></li>
	<li><a>我的收件箱</a></li>
	<li><a>未读邮件</a></li>
</ol>
<div class="panel panel-default">
	<div class="panel-body">
		<c:import url="/message/message_menu" />
		<div id="message-panel" class="col-md-9 panel panel-default">
			<h5>未读邮件</h5>
			<table  class="table">
				<tr><th>标题</th><th>发件人</th><th>时间</th></tr>
				<c:forEach var="m" items="${messages}">
				<tr>
					<td class="${m.readed?'readed':'unreaded'}">
						<a href="message/receive_detail?id=${m.id}">${m.messageSending.title}</a>
					</td>
					<td>${m.messageSending.sender.username}</td>
					<td>${mc:dateTimeFormat(m.messageSending.sendTime)}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
