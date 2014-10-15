<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>Music Store 后台商品编辑</title>
	<link type="text/css" rel="Stylesheet" href="/MusicStore/style/admin.css"/>
	<script type="text/javascript" src="../script/jquery-1.8.2.min.js"></script>
    <script type="text/javascript" src="../script/jquery.validate.js"></script>
    <script type="text/javascript" src="../script/jquery-ui-1.8.24.custom.min.js"></script>
    <script type="text/javascript" src="../script/jquery.ui.datepicker-zh-CN.js"></script>
	<script type="text/javascript">
 		$(function () {
            $('#albumEditForm').validate({
                    rules: {
                        "album.title": { required: true },
                        "album.artist": { required: true },
                        "album.price": { required: true, number: true, range: [0, 1000]  },
                        "album.stock": { required: true, digits: true, range: [0, 100000]  },
                        uploadCover: { accept: "jpeg|jpg" },
                        "album.dateReleased": { required: true, dateISO: true }
                    },
                    messages: {
                        "album.title": { required: "请输入唱片标题" },
                        "album.artist": { required: "请输入歌手" },
                        "album.price": { required: "请输入单价", number: "单价应为数值", range:"单价应在0-1000元之间"},
                        "album.stock": { required: "请输入库存", digits: "库存应为正整数", range: "库存量在0-100000之间" },
                        uploadCover: { accept: "图片格式应为jpeg" },
                        "album.dateReleased": { required: "请输入发行日期", dateISO: "日期格式应为yyyy-mm-dd" }
                    }
            });
            //日期控件
            $("#dateReleased").datepicker({dateFormat:"yy-mm-dd"});
        });
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
	<link type="text/css" rel="stylesheet" href="../style/jquery-ui/jquery-ui-1.8.24.custom.css" />
  </head>
  
  <body>
    <div id="wrapper">
		<jsp:include page="../shared/admin_header.jsp"></jsp:include>
    	<div id="content">
    		<div id="content-title">
				<h3>编辑商品</h3>
			</div>
			<div id="content-main">
				<form id="albumEditForm" action="/MusicStore/admin/albumSave" method="post" enctype="multipart/form-data">
				<input type="hidden" id="id" name="album.id" value="${album.id}" />
    			<table id="albumEdit">
    				<tr>
    					<td>唱片名称：</td>
    					<td><input class="txt" type="text" id="title" name="album.title" value="${album.title}" />
    						<s:fielderror   fieldName="album.title" /></td>
    				</tr>
    				<tr>
    					<td>唱片封面：</td>
    					<td>
    					<img src="../CoverImages/${album.id}.jpg" alt="" /><br/>
    					<input class="txt" type="file" id="uploadCover" name="uploadCover" /><label class="error"></label>
    					</td>
    				</tr>
    				<tr>
    					<td>歌手：</td>
    					<td><input class="txt" type="text" id="artist" name="album.artist" value="${album.artist}" />
    						<s:fielderror fieldName="album.artist" /></td>
    				</tr>
    				<tr>
    					<td>唱片分类：</td>
    					<td>
    						<select id="genreId" name="album.genre.id">
    							<c:forEach var="g" items="${genreList}" >
    							<c:if test="${album.genre.id != g.id}">
    							<option value="${g.id}">${g.name}</option>
    							</c:if>
    							<c:if test="${album.genre.id == g.id}">
    							<option value="${g.id}" selected="selected">${g.name}</option>
    							</c:if>
    							</c:forEach>
    						</select>
    					</td>
    				</tr>
    				<tr>
    					<td>单价：</td>
    					<td><input class="txt" type="text" id="price" name="album.price"  value="${album.price}"/>
    						<s:fielderror fieldName="album.price" /></td>
    				</tr>
    				<tr>
    					<td>库存：</td>
    					<td><input class="txt" type="text" id="stock" name="album.stock"  value="${album.stock}"/>
    						<s:fielderror fieldName="album.stock" /></td>
    				</tr>
    				<tr>
    					<td>发行日期：</td>
    					<td><input class="txt" type="text" id="dateReleased" name="album.dateReleased"  value="${album.dateReleased}"/>
    						<s:fielderror fieldName="album.dateReleased" /></td>
    				</tr>
    				<tr>
    					<td>唱片详细信息：</td>
    					<td><textarea id="description" name="album.description"  style="width:400px; height:120px;" >
    						${album.description}
    						</textarea>
    					</td>
    				</tr>
    				<tr>
    					<td></td>
    					<td>
    						<input id="btnSubmit" type="submit" name="btnSubmit" value="保存" />
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