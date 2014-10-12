<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.myCommunity.com/tags" prefix="mc" %>
<script type="text/javascript" src="Content/script/jquery.validate.js"></script>
<script type="text/javascript">
	$(function(){
		$("#messageSaveForm").validate({
			rules:{
				title:{required:true},
				receivers:{required:true, remote:"message/checkReceiversAvalable"},
				content:{required:true}
			},
			messages:{
				title:{required:"请填写邮件标题"},
				receivers:{required:"请填写收件人", remote:"收件人填写不正确"},
				content:{required:"请填写邮件内容"}
			}
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
			<h5>编写邮件</h5>
			<div class="panel panel-default"">
				<div class="panel-heading">注意：请在收件人处填写发送对象的用户名，若多个收件人则用半角逗号分隔</div>
				<form id="messageSaveForm" action="message/save" method="post">
				<input type="hidden" name="id" value="${message.id}"/>
				<input type="hidden" name="status" id="msg_status" />
				<table id="message-detail">
					<tr><th>标题：</th><td><input type="text" ${readonly==1?"readonly='readonly'":""} value="${message.title}" name="title" class="form-control" /></td></tr>
					<tr><th>收件人：</th><td><input type="text"  ${readonly==1?"readonly='readonly'":""} name="receivers" class="form-control" value="${message!=null?message.receivers:receiver}" /></td></tr>
					<tr><th>正文：</th><td></td></tr>
					<tr><th></th>
						<td><textarea name="content"  ${readonly==1?"readonly='readonly'":""} class="form-control" cols="" rows="">${message.content}</textarea>
						</td>
					</tr>
				</table>
				<div class="panel-footer">
					<c:if test="${readonly!=1}">
					<a class="btn btn-default" href="message/send_draft">返回</a></li>
					<input class="btn btn-default" type="submit" value="保存草稿" onclick="$('#msg_status').val('0');"/>
					<input class="btn btn-default" type="submit" value="发送" onclick="$('#msg_status').val('1');"/>
					</c:if>
					<c:if test="${readonly==1}">
					<a class="btn btn-default" href="message/send_list">返回</a></li>
					</c:if>
				</div>
				</form>
			</div>
		</div>
	</div>
</div>
