<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<header>
    <div id="logo">
        <img src="Content/css/images/logo.png" alt="" />
    </div>
    <nav class="navbar navbar-default" >
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse-menu">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
            <a class="navbar-brand" href="#">首页</a>
        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse-menu">
        	<c:if test="${loginUser!=null}">
            <ul class="nav navbar-nav">
                <li class="active"><a href="personal">个人主页</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">我的邮箱<b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="message/receive_list">收件箱</a></li>
                        <li><a href="message/send_list">发件箱</a></li>
                        <li class="divider"></li>
                        <li><a href="message/create">编写新邮件</a></li>
                    </ul>
                </li>
                <li><c:if test="${forum!=null}">
                	<a href="create_topic?forumId=${forum.id}">发表主题</a>
                	</c:if>
                	<c:if test="${forum==null}">
                	<a href="create_topic">发表主题</a>
                	</c:if>
                </li>
            </ul>
            </c:if>
            <form action="search_result" method="get" class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" name="title" value="${param.title}" class="form-control" placeholder="主题">
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>
            <ul class="nav navbar-nav navbar-right">
            <c:if test="${loginUser==null}">
                <li><a href="login">登录</a></li>
                <li><a href="register">注册</a></li>
			</c:if>
            <c:if test="${loginUser!=null}">
            	<li><a href="logout">注销</a></li>
            	<li><a>您好，${loginUser.nickname}</a></li>
			</c:if>
            </ul>
        </div>
    </nav>
</header>