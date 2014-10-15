<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style type="text/css">
    #autoComplete{
    	position:absolute;
    	border:solid 1px silver;
    	background-color: #FFF;
    	width: 150px;
    	display: none;
    }
    #autoComplete_info,#autoComplete_info tr,#autoComplete_info td{
    	width: 148px;
    }
    #autoComplete_info tr:hover{
        background-color:#96BFF0;
    }
    #autoComplete_info tr{    
        cursor:pointer;	
    }
</style>

<script type="text/javascript">
    $(function() {
        //键入后执行关键字查询
        $('#keyword').keyup(txtSearch_keyup).blur(txtSearch_blur);         
        //把单击的候选项放置到文本框
        $('#autoComplete_info td').live('click', function() {
            $('#keyword').val($(this).text());
            $('#autoComplete').hide();
        });
    });
    function txtSearch_blur() {
        //不能马上消失，否则点击选择时txt失焦，该table会造马上消失无法选择
        setTimeout(function() { $('#autoComplete').hide() }, 200); 
    };
    function txtSearch_keyup(event) {
        var word = encodeURIComponent($(this).val());
        if ($.trim(word) != "") {
            $.getJSON('json/searchAutoComplete?prefix='+word,successCallback);   //异步调用JSON请求
        }
    };
    //成功回调
    function successCallback(returnValue) {
        var x = $('#keyword').offset().left;
        var y = $('#keyword').offset().top + $('#keyword').outerHeight(true) + 1;
        $('#autoComplete_info').empty();
        $('#autoComplete').css({ 'left': x, 'top': y }).show();
        for (var i = 0; i < returnValue.titles.length; i++) {
            var html = '<tr><td>' + returnValue.titles[i] + '</tr></td>'
            $('#autoComplete_info').append($(html));
        }
    };
</script>
   	 	<div id="header">
	  		<div id="header-logo" >
	  			<a href="/MusicStore/index"><img src="/MusicStore/style/images/logo.png" alt=""></img></a>
	  		</div>
	  		<div id="header-login"><a href="/MusicStore/login.jsp">登录</a> | <a href="/MusicStore/register.jsp">注册</a></div>
	  		<div class="clearBoth"></div>
	  		<div id="header-search">
	  			<form action="albumList" method="get">
	  			<input id="keyword" name="title" type="text" autocomplete="off"  /><input id="btnSearch" type="submit" value="搜索" />
	  			</form>
	  		</div>
	  		<div id="header-nav">
	  			<ul>
	  				<li><a href="/MusicStore/index">首页</a></li>
	  				<li><a href="/MusicStore/albumList">商店</a></li>
	  				<li><a href="/MusicStore/shoppingCart">购物车</a></li>
	  				<li><a href="#">个人信息</a></li>
	  				<c:if test='${loginUser!=null && loginUser.userRole.name=="管理员" }'>
	  				<li><a href="/MusicStore/admin/index">后台管理</a></li>	
	  				</c:if>
	  			</ul>
	  			<label id="welcome">您好，${loginUser==null?"游客":loginUser.username}。</label>
	  		</div>
	  	</div>
	  	
	  	<div id="autoComplete">
	        <table>
	            <tbody id="autoComplete_info">
	            </tbody>
	        </table>
    	</div>