<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.musicStore.entity.*"  %>
<%@ page import="com.musicStore.dao.*"  %>
<%@ page import="com.musicStore.dao.impl.*"  %>
<%
	AlbumDao aDao = new AlbumDaoImpl();
	List<Album> albums = aDao.getHotAlbums(9);
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
    			<h3 id="main-title">最新热销唱片</h3>
    			<div id="hotAlbums">
    				<% for(Album a : albums){ %>
					<dl class="hotAlbumsItem">
	    				<dt>
	    					<a href="album.jsp?albumId=<%=a.getId()%>"><img src="CoverImages/<%=a.getId()%>.jpg" alt=""/></a>
	    				</dt>
	    				<dd><%=a.getTitle()%></dd>
	    			</dl>
	    			<% } %>
    			</div>
    		</div>
    		<div class="clearBoth"></div>
    	</div>
    	<%@ include file="shared/front_footer.jsp" %>
    </div>
  </body>
</html>
