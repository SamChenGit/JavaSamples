<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>欢迎光临 Music Store</title>
	<link type="text/css" rel="Stylesheet" href="/MusicStore/style/front.css"/>
	<script type="text/javascript" src="/MusicStore/script/jquery-1.5.1.js"></script>
  </head>
  
  <body>
    <div id="wrapper">
    	<%@ include file="shared/front_header.jsp" %>
    	<div id="content">
			<s:action name="genreSubList" executeResult="true"></s:action>
    		<div id="main">
    			<h3 id="main-title">最新热销唱片</h3>
    			<div id="hotAlbums">
    				<c:forEach var="a" items="${albums}">
					<dl class="hotAlbumsItem">
	    				<dt>
	    					<a href="/MusicStore/album?albumId=${a.id}">
	    					<img src="/MusicStore/CoverImages/${a.id}.jpg" alt=""/></a>
	    				</dt>
	    				<dd><c:out value="${a.title}" escapeXml="true"></c:out></dd>
	    			</dl>
	    			</c:forEach>
    			</div>
    		</div>
    		<div class="clearBoth"></div>
    	</div>
    	<%@ include file="shared/front_footer.jsp" %>
    </div>
  </body>
</html>
