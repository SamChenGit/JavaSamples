<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ page language="java" import="com.musicStore.entity.*" %>
<%@ page language="java" import="com.musicStore.dao.*" %>
<%@ page language="java" import="com.musicStore.dao.impl.*" %>
<%@ page language="java" import="java.net.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%
	GenreDao gDao = new GenreDaoImpl();
	List<Genre> genres = gDao.getAll();
	request.setAttribute("genres",genres);
	
	if(request.getParameter("albumId") != null){
		int albumId = Integer.valueOf(request.getParameter("albumId"));
		AlbumDao albumDao = new AlbumDaoImpl();
		Album album = albumDao.fetchAlbumByKey(albumId);
		request.setAttribute("album",album);
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>Music Store ��̨��Ʒ�༭</title>
	<link type="text/css" rel="Stylesheet" href="/MusicStore/style/admin.css"/>
	<script type="text/javascript" src="/MusicStore/script/jquery-1.4.1.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#albumEditForm").submit(checkInput);
		});
		function checkInput(){
			var flag = true;
			$("label.error").text("");
			if($("#title").val() == ""){
				$("#title").next(".error").text("�����볪Ƭ���ơ�");
				flag = false;
			}
			if($("#artist").val() == ""){
				$("#artist").next(".error").text("������������ơ�");
				flag = false;
			}
			if($("#price").val() == ""){
				$("#price").next(".error").text("�����뵥�ۡ�");
				flag = false;
			}
			else{
				var regexp = /^\d+(\.){0,1}\d{0,2}$/;
				if(regexp.test($("#price").val())== false){
					$("#price").next(".error").text("������Ϸ����ۡ�");
					flag = false;
				}
			}
			if($("#dateReleased").val() == ""){
				$("#dateReleased").next(".error").text("�����뷢�����ڡ�");
				flag = false;
			}
			else{
				var regexp = /^\d{4}-\d{1,2}-\d{1,2}$/;
				if(regexp.test($("#dateReleased").val())== false){
					$("#dateReleased").next(".error").text("�������׼���ڸ�ʽ��yyyy-mm-dd��");
					flag = false;
				}
			}
			if($("#uploadCover").val() != ""){
				var regexp = /^.{1,}\.((j|J)(p|P)(g|G))$/;
				if(regexp.test($("#uploadCover").val())== false){
					$("#uploadCover").next(".error").text("�������׼���ڸ�ʽ��yyyy-mm-dd��");
					flag = false;
				}
			}
			return flag;
		}
	</script>
	<style type="text/css">
		#albumEdit{
			margin:0 0 0 40px;
			line-height:2;
		}
		#albumEdit input.txt, #albumEdit select, #albumEdit textarea.txt{
			width:200px;
		}
		#albumEdit img{
			width:100px;
		}
	</style>
  </head>
  
  <body>
    <div id="wrapper">&nbsp; 
		<jsp:include page="../shared/admin_header.jsp"></jsp:include>
    	<div id="content">
    		<div id="content-title">
				<h3>�����Ʒ</h3>
			</div>
			<div id="content-main">
				<form id="albumEditForm" enctype="multipart/form-data" action="/MusicStore/EditAlbumAction" method="post">
				<input type="hidden" id="id" name="id" value="${album.id}" />
    			<table id="albumEdit">
    				<tr>
    					<td>��Ƭ���ƣ�</td>
    					<td><input class="txt" type="text" id="title" name="title" value="${album.title}" /><label class="error"></label></td>
    				</tr>
    				<tr>
    					<td>��Ƭ���棺</td>
    					<td>
    					<img src="../CoverImages/${album.id}.jpg" alt="" /><br/>
    					<input class="txt" type="file" id="uploadCover" name="uploadCover" /><label class="error"></label>
    					</td>
    				</tr>
    				<tr>
    					<td>���֣�</td>
    					<td>
    					<input class="txt" type="text" id="artist" name="artist" value="${album.artist}" /><label class="error"></label>
    					</td>
    				</tr>
    				<tr>
    					<td>��Ƭ���ࣺ</td>
    					<td>
    						<select id="genreId" name="genreId">
    							<c:forEach var="g" items="${genres}" >
    							<c:if test="${album.genreId != g.id}">
    							<option value="${g.id}">${g.name}</option>
    							</c:if>
    							<c:if test="${album.genreId == g.id}">
    							<option value="${g.id}" selected="selected">${g.name}</option>
    							</c:if>
    							</c:forEach>
    						</select>
    					<label class="error"></label></td>
    				</tr>
    				<tr>
    					<td>���ۣ�</td>
    					<td>
    					<input class="txt" type="text" id="price" name="price"  value="${album.price}"/><label class="error"></label>
    					</td>
    				</tr>
    				<tr>
    					<td>�������ڣ�</td>
    					<td>
    					<input class="txt" type="text" id="dateReleased" name="dateReleased"  value="${album.dateReleased}"/><label class="error"></label>
    					</td>
    				</tr>
    				<tr>
    					<td>��Ƭ��ϸ��Ϣ��</td>
    					<td><textarea id="description" name="description"  style="width:400px; height:120px;" >
    						${album.description}
    						</textarea>
    					</td>
    				</tr>
    				<tr>
    					<td></td>
    					<td>
    						<input id="btnSubmit" type="submit" name="btnSubmit" value="����" />
    					</td>
    				</tr>
    			</table>
    			</form>
			</div>
    	</div>
    	<jsp:include page="../shared/admin_footer.jsp"></jsp:include>
    </div>
  </body>
</html>