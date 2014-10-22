<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ page import="java.util.*"  %>
<%@ page import="java.net.*" %>
<%@ page import="com.musicStore.entity.*"  %>
<%@ page import="com.musicStore.dao.*"  %>
<%@ page import="com.musicStore.dao.impl.*"  %>
<%
	int pageSize = 3;
	request.setAttribute("pageSize", pageSize);
	int pageNum = 1; 
 	if(request.getParameter("page") != null)
 		pageNum = Integer.valueOf(request.getParameter("page"));
 	request.setAttribute("pageNum", pageNum);
 	
 	int genreId = 0;
 	if(request.getParameter("genreId") != null){
 		genreId = Integer.valueOf(request.getParameter("genreId"));
 	}
 	request.setAttribute("genreId", genreId);
 	
 	String title = "";
 	if(request.getParameter("title") != null)
 		title = new String(request.getParameter("title").getBytes("iso-8859-1"),"gbk");
 	
 	AlbumDao albumDao = new AlbumDaoImpl();
 	List<Album> albums = albumDao.getAlbumsByConditions(genreId, title, pageNum, pageSize);
 	request.setAttribute("albums", albums);
 	
 	int rowCount = albumDao.getRowCountByConditions(genreId, title);
 	int pageCount = (int)Math.ceil(((double)rowCount) / pageSize);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>欢迎光临 Music Store</title>
	<link type="text/css" rel="Stylesheet" href="style/front.css"/>
	<script type="text/javascript" src="script/jquery-1.4.1.js"></script>
  </head>
  <body>
    <div id="wrapper">
   	 	<%@ include file="shared/front_header.jsp" %>
    	<div id="content">
    		<%@ include file="shared/front_sidebar.jsp" %>
    		<div id="main">
				<h3 id="main-title">唱片列表</h3>
    			<% for(Album a : albums) { %>
   				<table class="albumItem">
   					<tr>
   						<td rowspan="3" class="albumItem-image"><img src="CoverImages/<%=a.getId()%>.jpg" alt="" /></td>
   						<td colspan="2" class="albumItem-title">
   							<a href="album.jsp?albumId=<%=a.getId()%>"><%=a.getTitle()%></a>
   						</td>
   					</tr>
   					<tr>
   						<td class="albumItem-artist"><strong>歌手：</strong><%=a.getArtist()%></td>
   						<td class=".albumItem-price"><strong>定价：</strong>￥<%=a.getPrice()%></td>
   					</tr>
   					<tr>
   						<td colspan="2">
   						<%=a.getDescription() %>
   						</td>
   					</tr>
   				</table>	
    			<% } %>
    			<hr/>
    			<% if(pageNum>1){ %>
    				<a href="album_list.jsp?page=<%=pageNum-1%>&genreId=<%=genreId%>&title=<%=title%>">上一页</a>
    			<% } %>
    			<% if(pageNum<pageCount){ %>
	    			<a href="album_list.jsp?page=<%=pageNum+1%>&genreId=<%=genreId%>&title=<%=title%>">下一页</a>
	    		<% } %>
	    		<% if(pageCount>0){ %>
	    			&nbsp;共<%=pageCount%>页，第<%=pageNum%>页。
	    		<% } %>
    		</div>
    		<div class="clearBoth"></div>
    	</div>
    	<%@ include file="shared/front_footer.jsp" %>
    </div>
  </body>
</html>
