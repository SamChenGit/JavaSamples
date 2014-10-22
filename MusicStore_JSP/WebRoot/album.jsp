<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%@ page import="java.util.*"  %>
<%@ page import="com.musicStore.entity.*"  %>
<%@ page import="com.musicStore.dao.*"  %>
<%@ page import="com.musicStore.dao.impl.*"  %>
<%
 	int albumId = 0;
 	String albumParam = request.getParameter("albumId");
 	if(albumParam != null){
 		albumId = Integer.valueOf(albumParam);
 	}
 	AlbumDao albumDao = new AlbumDaoImpl();
 	Album album = albumDao.fetchAlbumByKey(albumId);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>唱片明细</title>
	<link type="text/css" rel="Stylesheet" href="style/front.css"/>
	<script type="text/javascript" src="script/jquery-1.4.1.js"></script>
  </head>
  
  <body>
    <div id="wrapper">
   	 	<%@ include file="shared/front_header.jsp" %>
    	<div id="content">
    		<%@ include file="shared/front_sidebar.jsp" %>
    		<div id="main">
    			<h3 id="main-title">唱片详细资料</h3>
    			<table id="albumDetail">
    				<tr>
    					<td rowspan="5" id="albumDetail-image"><img src="CoverImages/<%=album.getId()%>.jpg" alt="" /></td>
    					<td class="albumDetail-label">专辑名称：</td>
    					<td id="albumDetail-title">《<%=album.getTitle()%>》</td>
    				</tr>
    				<tr>
    					<td class="albumDetail-label">歌手：</td>
    					<td><%=album.getArtist()%></td>
    				</tr>
    				<tr>
    				   	<td class="albumDetail-label">定价：</td>
    					<td><%=album.getPrice()%></td>
    				</tr>
    				<tr>
    					<td class="albumDetail-label">发行时间：</td>
    					<td><%=album.getDateReleased()%></td>
    				</tr>
    				<tr>
    				   	<td class="albumDetail-label"></td>
    					<td>
    						<form action="AddCartItemAction" method="post">
    						<input type="hidden" name="albumId" value="<%=album.getId()%>" />
    						<input type="submit" value="购买"/>
    						</form>
    					</td>
    				</tr>
    				<tr>
    					<td class="albumDetail-label center">详细介绍：</td>
    					<td colspan="3">
    						<%=album.getDescription()%>
    					</td>
    				</tr>
    			</table>
    		</div>
    		<div class="clearBoth"></div>
    	</div>
    	<%@ include file="shared/front_footer.jsp" %>
    </div>
  </body>
</html>
