<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <title>分类列表</title>
    <link href="css/base.css" rel="stylesheet" type="text/css" >
    <link href="css/category.css" rel="stylesheet" type="text/css" >
    <script type="text/javascript" src="script/jquery-1.4.4.js"></script>
    <script type="text/javascript" src="script/jquery.validate.js"></script>
    <script type="text/javascript">
    	$(function(){
    		$("#delete").click(function(){
    			$(this).parents("form").attr("action","resource_delete");
    		});
    		$("#modify").click(function(){
    			if($("#resourceId").val()=="" && $("#upload").val()==""){
    				$("label.error").text("请选择上传文件。");
    				return false;	
    			}
    			if($("#resourceId").val()!="" && $("#resourceTitle").val()==""){
    				$("label.error").text("请指定资源标题。");
    			}
    		});
    	});
    </script>
</head>
<body>
	<div id="wrapper">
		<h3>资源维护</h3>
		<a href="index?codeInfoId=${codeInfoId}">回到首页</a>
		<div id="gridPanel">
			<h4>资源列表</h4>
			<table class="gridList">
	            <thead>
	                <tr><th>操作</th><th>资源标题</th></tr>
	            </thead>
	            <tbody> 
	            	<c:forEach var="r" items="${resourceList}">
	                <tr><td><a href="resource_list?codeInfoId=${param.codeInfoId}&resourceId=${r.id}">选择</a></td>
	                	<td>${r.title}</td>
	                </tr>
	                </c:forEach>
	            </tbody>
			</table>
		</div>
		<div id="formPanel">
			<h4>编辑资源</h4>
			<form action="resource_save" method="post" enctype="multipart/form-data">
			<input type="hidden" id="resourceId" name="resource.id" value="${resource.id}"/>
			<input type="hidden" name="resource.codeInfo.id" value="${param.codeInfoId}" />
			<input type="hidden" name="resource.location" value="${resource.location}" />
			<input type="hidden" name="resource.resourceType" value="${resource.resourceType}" />
			<table class="editForm">
                <tr><td>资源标题</td>
                    <td><input type="text" id="resourceTitle" name="resource.title" value="${resource.title}" /></td>
                </tr>
                <c:if test="${resource==null}">
                <tr><td>资源文件</td>
                    <td><input type="file" name="upload" id="upload"></td>
                </tr>
                </c:if>
                <tr><td></td>
                    <td>
                    <button type="submit" id="modify">保存</button>
                    <c:if test="${resource != null}">
                    <button type="submit" id="delete">删除</button>
                    </c:if>
                    <a href="resource_list?codeInfoId=${param.codeInfoId}">取消</a>
                    </td>
                </tr>
			</table>
			</form>
            <label class="error">${errorMessage}</label>
		</div>
		<div class="clearBoth"></div>
	</div>
	<s:fielderror></s:fielderror>
</body>
</html>