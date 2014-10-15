<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>Music Store 后台商品管理</title>
	<link type="text/css" rel="Stylesheet" href="/MusicStore/style/admin.css"/>
  </head>
  
  <body>
    <div id="wrapper">&nbsp; 
    	<%@ include file="../shared/admin_header.jsp" %>
    	<div id="content">
    		<div id="content-title">
				<h3>商品管理</h3>
			</div>
			<div id="content-main">
				<form id="searchForm" action="/MusicStore/admin/albumManagement" method="get">
				唱片分类：
				<select id="genreId" name="genreId">
					<option value="0">所有分类</option>
					<c:forEach var="g" items="${genreList}">
					<c:if test="${g.id==genreId}">
					<option value="${g.id}" selected="selected">${g.name}</option>
					</c:if>
					<c:if test="${g.id!=genreId}">
					<option value="${g.id}">${g.name}</option>
					</c:if>
					</c:forEach>
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;唱片名称：
				<input type="text" id="title" name="title" value="${title}" />
				&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" id="btnSearch" value="查询" />
				</form>
				<hr/>
				<a href="/MusicStore/admin/albumEdit">添加新唱片</a>
				<table id="album-list">
					<tr><th>唱片名称</th><th>歌手</th><th>单价</th><th>库存</th><th>编辑</th></tr>
					<c:forEach var="a" items="${albumList}">
					<tr><td>${a.title}</td><td>${a.artist}</td><td>${a.price}</td><td>${a.stock}</td><td><a href="/MusicStore/admin/albumEdit?albumId=${a.id}">编辑</a></td></tr>
					</c:forEach>
					<tr>
						<td class="pager" colspan="5">
							<c:if test="${page>1}">
	    						<a href="/MusicStore/admin/albumManagement?page=${page-1}&genreId=${genreId}&title=${title}">上一页</a>
			    			</c:if>
			    			<c:if test="${page<pages}">
				    			<a href="/MusicStore/admin/albumManagement?page=${page+1}&genreId=${genreId}&title=${title}">下一页</a>
				    		</c:if>
				    		<c:if test="${pages>0}">
				    			&nbsp;共${pages}页，第${page}页。
			    			</c:if>
						</td>
					</tr>
				</table>
			</div>
    	</div>
    	<%@ include file="../shared/admin_footer.jsp" %>
    </div>
  </body>
</html>
