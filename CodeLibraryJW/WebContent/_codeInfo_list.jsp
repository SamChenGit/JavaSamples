<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<table class="gridList">
<c:forEach var="ci" items="${codeInfoList}">
	<tr><td><a class="detail" href="_codeInfo_detail?id=${ci.id}">${ci.title}</a></td></tr>
</c:forEach>
</table>