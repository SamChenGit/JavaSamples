<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" import="com.musicStore.entity.*" %>
<%@ page language="java" import="com.musicStore.dao.*" %>
<%@ page language="java" import="com.musicStore.dao.impl.*" %>
<%@ page language="java" import="java.net.*" %>
<%
	GenreDao gDao = new GenreDaoImpl();
 	List<Genre> genres = gDao.getAll();
 	request.setAttribute("genres", genres);
 	
 	int pageSize = 5;
 	request.setAttribute("pageSize", pageSize);
 	
 	int genreId = 0;
 	if(request.getParameter("genreId") != null)
 		genreId = Integer.valueOf(request.getParameter("genreId"));
 	request.setAttribute("genreId", genreId);
 	
 	String title = "";
 	if(request.getParameter("title") != null)
 		title = new String(request.getParameter("title").getBytes("iso-8859-1"), "gbk");
 		
 	request.setAttribute("title", title);
 	 	
 	int pageNum = 1;
 	if(request.getParameter("page") != null){
 		pageNum = Integer.valueOf(request.getParameter("page"));
 	}
 	request.setAttribute("pageNum", pageNum);
 	
 	AlbumDao aDao = new AlbumDaoImpl();
 	List<Album> albums = aDao.getAlbumsByConditions(genreId, title, pageNum, pageSize);
 	request.setAttribute("albums",albums);
 	
 	int rowCount = aDao.getRowCountByConditions(genreId, title);
 	int pageCount = (int)Math.ceil(((double)rowCount) / pageSize);
 	request.setAttribute("pageCount",pageCount);
 %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>Music Store 后台商品管理</title>
	<link type="text/css" rel="Stylesheet" href="../style/admin.css"/>
	<script type="text/javascript" src="../script/jquery-1.4.1.js"></script>
	<script type="text/javascript">
	/*
		$(function(){
			$("#btnSearch").click(function(){
				var url = "album_management.jsp?genreId=" + $("#queryGenre").val()
						+"&title=" + encodeURIComponent($("#queryAlbumName").val());
				window.location.href = url;
			});
		});
		*/
	</script>
  </head>
  
  <body>
    <div id="wrapper">&nbsp; 
		<jsp:include page="../shared/admin_header.jsp"></jsp:include>
    	<div id="content">
    		<div id="content-title">
				<h3>商品管理</h3>
			</div>
			<div id="content-main">
				<form id="searchForm" action="album_management.jsp" method="get">
				唱片分类：
				<select id="genreId" name="genreId">
					<option value="0">所有分类</option>
					<c:forEach var="g" items="${genres}">
					<option value="${g.id}">${g.name}</option>
					</c:forEach>
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;唱片名称：
				<input type="text" id="title" name="title" />
				&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" id="btnSearch" value="查询" />
				</form>
				<hr/>
				<a href="album_edit.jsp">添加新唱片</a>
				<table id="album-list">
					<tr><th>唱片名称</th><th>歌手</th><th>单价</th><th>编辑</th></tr>
					<c:forEach var="a" items="${albums}">
					<tr><td>${a.title}</td><td>${a.artist}</td><td>${a.price}</td><td><a href="album_edit.jsp?albumId=${a.id}">编辑</a></td></tr>
					</c:forEach>
					<tr>
						<td class="pager" colspan="4">
							<c:if test="${pageNum>1}">
			    				<a href="album_management.jsp?page=${pageNum-1}&genreId=${genreId}&title=${title}">上一页</a>
			    			</c:if>
			    			<c:if test="${pageNum<pageCount}">
				    			<a href="album_management.jsp?page=${pageNum+1}&genreId=${genreId}&title=${title}">下一页</a>
				    		</c:if>
				    		<c:if test="${pageCount>0}">
				    			&nbsp;共${pageCount}页，第${pageNum}页。
				    		</c:if>
						</td>
					</tr>
				</table>
			</div>
    	</div>
    	<jsp:include page="../shared/admin_footer.jsp"></jsp:include>
    </div>
  </body>
</html>
