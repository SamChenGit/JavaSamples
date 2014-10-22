<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
    <title>代码检索</title>
    <link href="css/base.css" rel="stylesheet" type="text/css" >
    <link href="css/index.css" rel="stylesheet" type="text/css" >
    <script type="text/javascript" src="script/jquery-1.4.4.js"></script>
    <script type="text/javascript" src="script/jquery.validate.js"></script>
    <script type="text/javascript" src="ckeditor/ckeditor.js"></script>
    <script type="text/javascript">
    	//异步加载CKEditor后的回调函数
    	function setUpCkEditor(){
	        /* 注释部份代码为3.6时需要的
	        if (CKEDITOR.instances['codeInfo.content']) {
	            CKEDITOR.remove(CKEDITOR.instances['codeInfo.content']);
	        } */
	        CKEDITOR.replace('codeInfo.content', { toolbar: 'Full' });
    	}
    	function validateEdit(){
    		if($("#codeInfo_title").val() == ""){
    			return false;
    		}
    		if($("#codeInfo_points").val() == "" ||isNaN($("#codeInfo_points").val())){
    			return false;
    		}
    		if($("#codeInfo_keys").val() == ""){
    			return false;
    		}
    		if($("#codeInfo_category_id").val() == "" || parseInt($("#codeInfo_category_id").val())<=0){
    			return false;
    		}
    		if(CKEDITOR.instances["codeInfo.content"].getData() == ""){
    			return false;
    		}
    		return true;
    	}
    	
    	$(function(){
    		//执行查询
    		$("#search").click(function(){
    			var data = $("#searchForm").serialize()
    			var url = "_codeInfo_list?"+data; 
    			$("#codeList").load(url);
    		});
    		$("#searchKey,#searchTitle").keypress(function(e){
    			if(e.which == 13){
    				$("#search").trigger("click");		
    			}
    		});
    		//加载代码明细
    		$("a.detail").live("click", function(e){
    			var url = $(this).attr("href");
    			$("#codeDetail").load(url);
    			return false;
    		});
    		//添加&编辑代码明细
    		$("#addCodeInfo,#editCodeInfo").live("click", function(){
    			var url = $(this).attr("href");
    			$("#codeDetail").load(url,setUpCkEditor);
    			return false;
    		});
    		//返回只读
    		$("#showCodeInfo").live("click", function(){
    			var url = $(this).attr("href");
    			$("#codeDetail").load(url);
    			return false;
    		});
    		//保存变更（添加或修改后
    		$("#save").live("click", function(){
    			if(!validateEdit()){
    				$("#eidtError").text("请按要求完整填写信息。");
    				return false;
    			}
    			$("textarea").val(CKEDITOR.instances["codeInfo.content"].getData());
    			var formData = $("#editForm").serialize();
    			$.ajax({
    				type:"POST",
    				url:"_codeInfo_save",
    				data:formData,
    				dataType:"json",
    				success:function(json){
    					if(json.jsonResult.success){
    						var id = json.jsonResult.data;
							var url = "_codeInfo_detail?id="+id;
    						$("#codeDetail").load(url);
    					}else{
    						$("#eidtError").text(json.jsonResult.message);
    					}
    				},
    				error:function(){
    					alert("AJAX请求失败。");
    				}
    			});
    		});
    		//删除当前记录
    		$("#delete").live("click", function(){
    			if(!confirm("确定删除该记录？")){
    				return;
    			}
    			var url = "_codeInfo_delete";
    			var data = "id="+$(this).val();
    			$.post(url, data, function(){$("#search").trigger("click");});
    			$("#codeDetail").empty();
    		});
    	});
    </script>
</head>
<body>
	<div id="wrapper">
		<h3>代码检索</h3>
		<div id="ribbon">
		<form id="searchForm">
		    <label>分类：</label>
		   		<select name="cid">
		   			<option value="0">----</option>
		   			<c:forEach var="c" items="${categories}">
		   			<option value="${c.id}">${c.name}</option>
		   			</c:forEach>
		   		</select>
		    <label>关键字：</label><input type="text" id="searchKey" name="key" />
		    <label>标题：</label><input type="text" id="searchTitle" name="title" />
		    <button type="button" id="search">检索</button>
		    <button type="reset">清空</button>
		    <a href="category_list">分类维护</a>
		</form>
		</div>
		<div id="formPanel">
			<h4><a id="addCodeInfo" href="_codeInfo_edit">添加代码</a></h4>
			<div class="container"  id="codeDetail">
				<!-- 如果带codeInfoId参数，则同步加载源代码信息，用于响应返回 -->
				<c:if test="${param.codeInfoId!=null}">
				<s:action name="_codeInfo_detail" executeResult="true">
					<s:param name="id">${param.codeInfoId}</s:param>
				</s:action>
				</c:if>
			</div>
		</div>
		<div id="gridPanel">
			<h4>检索结果</h4>
			<div class="container" id="codeList">
			</div>
		</div>
		
		<div class="clearBoth"></div>
	</div>
</body>
</html>