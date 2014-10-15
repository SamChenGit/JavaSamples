<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

			<div id="sidebar">
	            <h3>唱片分类</h3>
	            <ul class="categories">
	            	<c:forEach var="g" items="${genres}" varStatus="s">
	            	<c:if test="${s.index%2==1}">
		            <li><a href="/MusicStore/albumList?genreId=${g.id}">${g.name}</a></li>
		            </c:if>
		            <c:if test="${s.index%2==0}">
		            <li><a class="even" href="/MusicStore/albumList?genreId=${g.id}">${g.name}</a></li>
            		</c:if>
            		</c:forEach>
	            </ul>
            </div>