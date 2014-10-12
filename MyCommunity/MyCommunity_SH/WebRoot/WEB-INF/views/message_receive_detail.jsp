<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.myCommunity.com/tags" prefix="mc" %>
<form id="toEditMsgForm" action="message/create" method="post">
	<input type="hidden" id="receiverName" name="receiver" />
	<input type="hidden" name="readonly" value="0" />
</form>
<script type="text/javascript">
	$(function(){
		$("#reply").click(function(){
			$("#receiverName").val($(this).attr("data-receiver"));
			$("#toEditMsgForm").submit();
			return false;
		});
	});
</script>
<ol class="breadcrumb">
	<li><a href="#">首页</a></li>
	<li><a>我的收件箱</a></li>
	<li><a href="#">邮件内容</a></li>
</ol>
<div class="panel panel-default">
	<div class="panel-body">
		<c:import url="/message/message_menu" />
		<div id="message-panel" class="col-md-9 panel panel-default" style="min-height: 313px;">
			<h5>邮件内容</h5>
			<div class="panel panel-default"">
				<div class="panel-heading">
					标题： ${msg.messageSending.title}
				</div>
				<table id="message-detail">
					<tr><th>发件人：</th><td>${msg.messageSending.sender.username}</td></tr>
					<tr><th>发送时间：</th><td>${mc:dateTimeFormat(msg.messageSending.sendTime)}</td></tr>
					<tr><th>正文：</th><td></td></tr>
					<tr><th></th><td>${msg.messageSending.content}</td></tr>
				</table>
				<div class="panel-footer">
					<a class="btn btn-default" href="message/receive_list">返回</a></li>
					<a class="btn btn-default" id="reply" data-receiver="${msg.messageSending.sender.username}">回复</a></li>
					<a class="btn btn-default" href="message/receive_mark_recycle?id=${msg.id}">删除</a></li>
				</div>
			</div>
		</div>
	</div>
</div>
