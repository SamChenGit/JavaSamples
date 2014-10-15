<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>唱片明细</title>
	<link type="text/css" rel="Stylesheet" href="/MusicStore/style/front.css"/>
	<script type="text/javascript" src="/MusicStore/script/jquery-1.5.1.js"></script>
  </head>
  
  <body>
    <div id="wrapper">
    	<%@ include file="shared/front_header.jsp" %>
    	<div id="content">
    		<s:action name="genreSubList" executeResult="true" />
    		<div id="main">
    			<h3 id="main-title">唱片详细资料</h3>
    			<table id="albumDetail">
    				<tr>
    					<td rowspan="5" id="albumDetail-image"><img src="/MusicStore/CoverImages/${album.id}.jpg" alt="" /></td>
    					<td class="albumDetail-label">专辑名称：</td>
    					<td id="albumDetail-title">《${album.title}》</td>
    				</tr>
    				<tr>
    					<td class="albumDetail-label">歌手：</td>
    					<td>${album.artist}</td>
    				</tr>
    				<tr>
    				   	<td class="albumDetail-label">定价：</td>
    					<td>${album.price}</td>
    				</tr>
    				<tr>
    					<td class="albumDetail-label">发行时间：</td>
    					<td>${album.dateReleased}</td>
    				</tr>
    				<tr>
    				   	<td class="albumDetail-label"></td>
    					<td>
    						<s:form action="addAlbumToShoppingCart" method="post">
    						<input type="hidden" name="albumId" value="${album.id}" />
    						<input type="submit" value="购买"/>
    						</s:form>
    					</td>
    				</tr>
    				<tr>
    					<td class="albumDetail-label center">详细介绍：</td>
    					<td colspan="3">
    						${album.description}
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
