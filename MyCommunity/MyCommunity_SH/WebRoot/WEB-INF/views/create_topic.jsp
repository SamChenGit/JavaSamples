<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.myCommunity.com/tags" prefix="ct" %>
    <script type="text/javascript" src="Content/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="Content/ckeditor/adapters/jquery.js"></script>
    <script type="text/javascript" src="Content/script/jquery.validate.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#content").ckeditor();
		});
	
		$(function(){
			$("#topicForm").validate({
				rules:{
					title:{required:true},
					"forum.id":{min:1},
					content:{required: true}
				},
				messages:{
					title:{required:"请填写标题"},
					"forum.id":{min:"请选择版块"},
					content:{required:"请填写内容"}
				},
				errorPlacement: function(error, element) {	//重写错误的定位，把错误定位在与父div同级的末尾  
			    	error.appendTo(element.parents("div[class='form-group']"));
				},
				errorClass:"error",
				ignore:""
			});
		});
	</script>
<ol class="breadcrumb">
  <li><a href="#">首页</a></li>
  <li><a>发表主题</a></li>
</ol>
<div class="row">
    <div class="col-lg-12">
        <form id="topicForm" action="create_topic" method="post" class="form-horizontal" role="form">
            <div class="form-group" id="room">
                <label for="title" class="col-sm-1 control-label">标题</label>
                <div class="col-sm-7">
                    <input type="text" id="title" name="title" value="" class="form-control" placeholder="标题" />
                </div>
            </div>
            <div class="form-group" id="room">
                <label for="forumId" class="col-sm-1 control-label">版块</label>
                <div class="col-sm-7">
                	<select id="forumId" name="forum.id" class="form-control">
                		<c:forEach var="main" items="${forums}">
                		<option value="0">---请选择版块---</option>
                		<optgroup label="${main.name}">
                			<c:forEach var="sub" items="${main.children}">
                			<option value="${sub.id}" ${param.forumId==sub.id?'selected="selected"':''} >${sub.name}</option>
                			</c:forEach>
                		</optgroup>
                		</c:forEach>
                	</select>
                </div>
            </div>
            <div class="form-group">
                <label for="content" class="col-sm-1 control-label">内容</label>
                <div class="col-sm-7">
                	<textarea id="content" name="content"></textarea>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-1 col-sm-11">
                    <button type="submit" class="btn btn-default">发表</button>
                </div>
            </div>
            <c:if test="${errorMessage!=null}">
            <div class="form-group">
            	<div class="col-sm-offset-1 col-sm-11">
            		<label class="error">${errorMessage}</label>
            	</div>
            </div>
            </c:if>
        </form>
    </div>
</div>