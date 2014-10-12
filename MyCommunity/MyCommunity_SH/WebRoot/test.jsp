<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.myCommunity.com/tags" prefix="ct" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'test.jsp' starting page</title>
    <script type="text/javascript" src="Content/script/jquery-1.10.2.js"></script>
    <script type="text/javascript" src="Content/ckeditor/ckeditor.js"></script>
	<script type="text/javascript">
		$(function(){
			CKEDITOR.replace( 'content' );
		});
	</script>
  </head>
  
  <body>
    <textarea id="content" rows="" cols=""></textarea>
  </body>
</html>
