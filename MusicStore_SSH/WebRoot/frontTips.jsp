<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>唱片明细</title>
	<link type="text/css" rel="Stylesheet" href="/MusicStore/style/front.css"/>
	<script type="text/javascript" src="/MusicStore/script/jquery-1.5.1.js"></script>
	<script type="text/javascript">
		var num = ${seconds};
		$(function(){
			$("#seconds").text(num);
			function showTime(){
				if(num > 1 ){
					num--;
					$("#seconds").text(num);
				}
				else{
					window.location.href="${url}";
				}
			}
			setInterval(showTime, 1000);
		});
	</script>
  </head>
  
  <body>
    <div id="wrapper">
    	<%@ include file="shared/front_header.jsp" %>
    	<div id="content">
    		<s:action name="genreSubList" executeResult="true" />
    		<div id="main">
    			<h3 id="main-title">系统消息</h3>
    			<h4 style="margin-left:30px;">${message},
    				<label id="seconds" style="color:red;">${seconds}</label>
    				秒后自动跳转。</h4>
    			<a style="margin-left:30px;" href="${url}">或点击此处立即跳转。</a>
    		</div>
    		<div class="clearBoth"></div>
    	</div>
    	<%@ include file="shared/front_footer.jsp" %>
    </div>
  </body>
</html>
