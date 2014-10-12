<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="Content/script/jquery.validate.js"></script>
<script type="text/javascript">
	$(function(){
		$("#loginForm").validate({
			rules:{
				username:{required:true},
				password:{required:true}
			},
			messages:{
				username:{required:"请填写用户名"},
				password:{required:"请填写密码"}
			},
			errorPlacement: function(error, element) {	//重写错误的定位，把错误定位在与父div同级的末尾  
			    error.appendTo(element.parents("div[class='form-group']"));  
			},
			errorClass:"error"
		});
	});
</script>
<ol class="breadcrumb">
  <li><a href="#">首页</a></li>
  <li><a href="#">登录</a></li>
</ol>
<div class="row">
    <div class="col-lg-6">
        <form id="loginForm" action="login" method="post" class="form-horizontal" role="form">
            <div class="form-group" id="room">
                <label for="username" class="col-sm-3 control-label">用户名</label>
                <div class="col-sm-5">
                    <input type="text" id="username" name="username" value="${username}" class="form-control" placeholder="用户名" />
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-3 control-label">密码</label>
                <div class="col-sm-5">
                    <input type="password" id="password" name="password" class="form-control" placeholder="密码" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-10">
                    <button type="submit" class="btn btn-default">登录</button>
                </div>
            </div>
            <c:if test="${errorMessage!=null}">
            <div class="form-group">
            	<div class="col-sm-offset-3 col-sm-10">
            		<label class="error">${errorMessage}</label>
            	</div>
            </div>
            </c:if>
        </form>
    </div>
</div>