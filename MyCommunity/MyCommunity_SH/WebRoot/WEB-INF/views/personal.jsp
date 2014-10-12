<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="Content/script/jquery.validate.js"></script>
<script type="text/javascript">
	$(function(){
		$("#personalForm").validate({
			rules:{
				password:{required:true},
				passwordAgain:{equalTo:"#password"},
				headmage: { accept: "jpg" },
				nickname:{required:true}
			},
			messages:{
				password:{required:"请填写密码"},
				passwordAgain:{equalTo:"两次输入的密码须一致"},
				headImage:{accept:"头像应该是jpg图片"},
				nickname:{required:"请填写昵称"}
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
  <li><a href="#">个人主页</a></li>
</ol>
<div class="row">
    <div class="col-lg-6">
        <form id="personalForm" action="personal" method="post" enctype="multipart/form-data" class="form-horizontal" role="form">
        	<input type="hidden" name="id" value="${loginUser.id}">
        	<div class="form-group" id="room">
                <label for="head-image" class="col-sm-3 control-label">上传头像</label>
                <div class="col-sm-5">
                	<div class="head-image"><img class="head-image" src="Content/pictures/user/${loginUser.id}.jpg" alt="" /></div>
                    <input type="file" id="head-image" name="headImage" />
                </div>
            </div>
            <div class="form-group" id="room">
                <label for="username" class="col-sm-3 control-label">用户名</label>
                <div class="col-sm-5">
                    <input type="text" id="username" disabled="disabled" value="${loginUser.username}" class="form-control" />
                </div>
            </div>
            <div class="form-group">
                <label for="password" class="col-sm-3 control-label">密码</label>
                <div class="col-sm-5">
                    <input type="password" id="password" name="password" class="form-control" placeholder="密码" value="${loginUser.password}" />
                </div>
            </div>
            <div class="form-group">
                <label for="passwordAgain" class="col-sm-3 control-label">密码确认</label>
                <div class="col-sm-5">
                    <input type="password" id="passwordAgain" name="passwordAgain" class="form-control" placeholder="密码确认" />
                </div>
            </div>
            <div class="form-group" id="room">
                <label for="nickname" class="col-sm-3 control-label">昵称</label>
                <div class="col-sm-5">
                    <input type="text" id="nickname" name="nickname" value="${loginUser.nickname}" class="form-control" placeholder="昵称" />
                </div>
            </div>
            <div class="form-group" id="room">
                <label for="signature" class="col-sm-3 control-label">个性签名</label>
                <div class="col-sm-5">
                    <input type="text" id="signature" name="signature" value="${loginUser.signature}" class="form-control" placeholder="个性签名" />
                </div>
            </div>
            <div class="form-group" id="room">
                <label for="grade" class="col-sm-3 control-label">积分</label>
                <div class="col-sm-5">
                    <input type="text" id="email" disabled="disabled" value="${loginUser.grade}" class="form-control" />
                </div>
            </div>
            <div class="form-group" id="room">
                <label for="emial" class="col-sm-3 control-label">Email</label>
                <div class="col-sm-5">
                    <input type="text" id="email" disabled="disabled" value="${loginUser.email}" class="form-control" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-10">
                    <button type="submit" class="btn btn-default">修改</button>
                    <a href="index" class="btn btn-default">回到首页</a>
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