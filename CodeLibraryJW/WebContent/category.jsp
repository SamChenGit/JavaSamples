<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    			$(this).parents("form").attr("action","category_delete");
    		});
    	});
    </script>
</head>
<body>
	<div id="wrapper">
		<h3>分类维护</h3>
		<a href="index">回到首页</a>
		<div id="gridPanel">
			<h4>分类列表</h4>
			<table class="gridList">
	            <thead>
	                <tr><th>操作</th><th>分类标识</th><th>分类名称</th><th>父级标识</th></tr>
	            </thead>
	            <tbody> 
	            	<c:forEach var="c" items="${categories}">
	                <tr><td><a href="category_list?id=${c.id}">选择</a></td>
	                	<td>${c.id}</td><td class="alignLeft">${c.name}</td>
	                	<td>${c.parentId}</td>
	                </tr>
	                </c:forEach>
	            </tbody>
			</table>
		</div>
		<div id="formPanel">
			<h4>编辑分类</h4>
			<form action="category_save" method="post">
			<input type="hidden" name="category.id" value="${category.id}"/>
			<table class="editForm">
                <tr><td>父级分类</td>
                    <td>
                    	<select name="category.parentId">
                    		<option value="0">----</option>
                    		<c:forEach var="c" items="${categories}">
                    		<c:if test="${category.parentId==c.id}">
                    		<option value="${c.id}" selected="selected">${c.name}</option>
                    		</c:if>
                    		<c:if test="${category.parentId!=c.id}">
                    		<option value="${c.id}">${c.name}</option>
                    		</c:if>
                    		</c:forEach>
                    	</select>
                    </td>
                </tr>
                <tr><td>分类名称</td>
                    <td><input type="text" name="category.name" value="${category.name}" /></td>
                </tr>
                <tr><td></td>
                    <td>
                    <button type="submit" id="modify">保存</button>
                    <c:if test="${category != null}">
                    <button type="submit" id="delete">删除</button>
                    </c:if>
                    <a href="category_list">取消</a>
                    </td>
                </tr>
			</table>
			</form>
            <label class="error">${errorMessage}</label>
		</div>
		<div class="clearBoth"></div>
	</div>
</body>
</html>