<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html>
<head>
	<base href="${initParam.WebRoot}" >
    <title><tiles:insertAttribute name="title" /></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="Content/bootstrap-3.0.3/css/bootstrap.css" rel="stylesheet" media="screen">
    <!-- Custom CSS -->
    <link href="Content/css/main.css" rel="stylesheet">
    <script src="Content/script/jquery-1.10.2.js"></script>
</head>
<body>
    <div class="container">
  		<tiles:insertAttribute name="top" />
    
    	<div class="content">
        	<tiles:insertAttribute name="content" />
    	</div>
    
		<tiles:insertAttribute name="bottom" />
    </div>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="Content/bootstrap-3.0.3/js/bootstrap.js"></script>
</body>
</html>