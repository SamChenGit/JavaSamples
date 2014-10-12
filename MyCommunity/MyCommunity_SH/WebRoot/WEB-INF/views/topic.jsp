<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.myCommunity.com/tags" prefix="ct" %>

<form id="submitForm" method="post">
</form>
<script type="text/javascript">
	$(function(){
		$("a.mail").click(function(){
			$("#submitForm")
				.empty()
				.append($("<input type='hidden' name='receiver'/>").val($(this).attr("data-receiver")))
				.attr("action","message/create").submit();
			return false;
		});
		$("a.deleteComment").click(function(){
			if(confirm('确定删除该评论？')){
				$("#submitForm")
					.empty()
					.append($("<input type='hidden' name='id'/>").val($(this).attr("data-comment")))
					.attr("action","deleteComment").submit();
			}
			return false;
		});
	});
</script>

<c:set var="pageNum" value="${param.page!=null?param.page:1}" />
<c:set var="previousLastFloor" value="${pageNum==1?0:((pageNum-1)*pageSize-1)}" />

<ol class="breadcrumb">
    <li><a href="#">首页</a></li>
    <li><a href="forum/${topic.forum.id}">${topic.forum.name}</a></li>
    <li>${topic.title }</li>
</ol>

<div id="topic-detail">
	<c:if test="${param.page==null || param.page==1 }">
    <table class="table table-bordered">
        <tbody>
            <tr>
                <td class="col-md-2 topic-title">
                	昵称：${topic.author.nickname}
                </td>
                <td>
                	标题：${topic.title}
                	<span class="pull-right"><a class="mail" href="#" data-receiver="${topic.author.username}">发邮件</a></span>
                </td>
            </tr>
            <tr>
                <td class="topic-title">
                    <img src="Content/pictures/user/${topic.author.id}.jpg" alt="" class="user" />
                </td>
                <td>
                   	 ${topic.content}
                   	 <br/><br/><br/>
                   	 <span class="pull-right">——${topic.author.signature}</span>
                </td>
            </tr>
            <tr>
                <td  class="topic-title">
                	积分：${topic.author.grade}
                </td>
                <td>
                                        发表时间：${ct:dateTimeFormat(topic.createTime)}
                    <span class="pull-right">IP: ${topic.ip}</span>
                </td>
            </tr>
        </tbody>
    </table>
    </c:if>
    
	<c:forEach var="c" items="${comments}" varStatus="vs">
    <table class="table table-bordered">
        <tbody>
            <tr>
                <td class="col-md-2 topic-title">
                	昵称：${c.commenter.nickname}
                </td>
                <td>
                	<span class="floor">${previousLastFloor+vs.count+1}楼</span>
                	<c:if test="${c.commenter.id==loginUser.id}">
                	<a href="#" data-comment="${c.id}" class="deleteComment">[删除]</a>
                	</c:if>
                	<span class="pull-right"><a class="mail" href="#" data-receiver="${c.commenter.username}">发邮件</a></span>
                </td>
            </tr>
            <tr>
                <td class="topic-title">
                    <img src="Content/pictures/user/${c.commenter.id}.jpg" alt="" class="user"  />
                </td>
                <td>
                   	${c.content}
                   	<br/><br/><br/>
                   	<span class="pull-right">——${c.commenter.signature}</span>
                </td>
            </tr>
            <tr>
                <td class="topic-title">
                	积分：${c.commenter.grade}
                </td>
                <td>
                   	发表时间：${ct:dateTimeFormat(c.commentTime)}
                    <span class="pull-right">IP: ${c.ip}</span>
                </td>
            </tr>
        </tbody>
    </table>
	</c:forEach>
	
    <div class="pager-container">
		<ct:pager urlFormat="topic/${topic.id}?page=%d" totalRows="${totalRows}" curPage="${param.page}" pageSize="${pageSize}"/>
    </div>
</div>

<c:if test="${loginUser!=null}">
<div class="panel panel-default">
	<div class="panel-heading">回复</div>
	<div class="panel-body">
		<form action="createComment" method="post" class="form-horizontal" role="form">
			<input type="hidden" name="reference.id" value="${topic.id}" />
			<div class="form-group">
				<label for="comment-content" class="col-sm-1 control-label">内容</label>
				<div class="col-sm-11">
					<textarea class="form-control" id="comment-content" name="content"
						placeholder="回复内容"></textarea>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-1 col-sm-11">
					<button type="submit" class="btn btn-default">发表</button>
				</div>
			</div>
		</form>
	</div>
</div>
</c:if>