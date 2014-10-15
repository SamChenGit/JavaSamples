<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/struts-tags"  prefix="s" %>
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
    		<s:action name="genreSubList" executeResult="true" />
    		<div id="main">
				<h3 id="main-title">唱片列表</h3>
				<c:forEach var="a" items="${albums}">
   				<table class="albumItem">
   					<tr>
   						<td rowspan="3" class="albumItem-image"><img src="/MusicStore/CoverImages/${a.id}.jpg" alt="" /></td>
   						<td colspan="2" class="albumItem-title">
   							<a href="/MusicStore/album?albumId=${a.id}">${a.title}</a>
   						</td>
   					</tr>
   					<tr>
   						<td class="albumItem-artist"><strong>歌手：</strong>${a.artist}</td>
   						<td class=".albumItem-price"><strong>定价：</strong>￥${a.price}</td>
   					</tr>
   					<tr>
   						<td colspan="2">
   						${fn:substring(a.description, 0, 128)}......
   						</td>
   					</tr>
   				</table>	
    			</c:forEach>
    			<hr/>
    			<c:if test="${page>1}">
    				<a href="/MusicStore/albumList?page=${page-1}&genreId=${genreId}&title=${title}">上一页</a>
    			</c:if>
    			<c:if test="${page<pages}">
	    			<a href="/MusicStore/albumList?page=${page+1}&genreId=${genreId}&title=${title}">下一页</a>
	    		</c:if>
	    		<c:if test="${pages>0}">
	    			&nbsp;共${pages}页，第${page}页。
	    		</c:if>
    		</div>
    		<div class="clearBoth"></div>
    	</div>
    	<%@ include file="shared/front_footer.jsp" %>
    </div>
  </body>
</html>
