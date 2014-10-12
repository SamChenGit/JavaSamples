<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
    <div class="col-lg-6">
		<div id="banner" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#banner" data-slide-to="0"></li>
				<li data-target="#banner" data-slide-to="1"></li>
				<li data-target="#banner" data-slide-to="2"></li>
				<li data-target="#banner" data-slide-to="3" class="active"></li>
			</ol>
			<!-- Wrapper for slides -->
			<div class="carousel-inner">
				<div class="item">
                    <img src="Content/pictures/banner/okwu.jpg" alt="OKWU.edu Homepage">
                    <div class="carousel-caption">广告图片0001</div>
                </div>
                <div class="item">
                    <img src="Content/pictures/banner/okwu-athletics.jpg" alt="OKWU Athletics Homepage">
                    <div class="carousel-caption">广告图片0002</div>
                </div>
                <div class="item">
                    <img src="Content/pictures/banner/bartlesvillecf.jpg" alt="Bartlesville Community Foundation">
                    <div class="carousel-caption">广告图片0003</div>
                </div>
                <div class="item active">
                    <img src="Content/pictures/banner/emancipation.jpg" alt="Emancipation Stories">
                    <div class="carousel-caption">广告图片0004</div>
                </div>
			</div>
			<!-- Controls -->
			<a class="left carousel-control" href="#banner" data-slide="prev"> 
				<span class="glyphicon glyphicon-chevron-left"></span> 
			</a> 
			<a class="right carousel-control" href="#banner"data-slide="next"> 
				<span class="glyphicon glyphicon-chevron-right"></span> 
			</a>
		</div>
	</div>
	
    <div class="col-md-6">
        <h4><strong>正在讨论</strong></h4>
        <ul class="list-group">
          <c:forEach var="t" items="${topics}">
          <li class="list-group-item">
          	<span class="badge">回复：${t.commentTimes}</span>
          	<a href="topic/${t.id}">${t.title}</a>
          </li>
          </c:forEach>
        </ul>
    </div>
</div>

<c:forEach var="mainForum" items="${forums}">


<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
          <div class="panel-heading">${mainForum.name}</div>
        </div>
    </div>
</div>

<div class="row">
	<c:forEach var="subForum" items="${mainForum.children}">
    
    <div class="col-md-3">
       <div class="thumbnail">
          <img src="Content/pictures/forum/forum_${subForum.id}_icon.png" alt="">
          <h3 class="text-center"><a href="forum/${subForum.id}">${subForum.name}</a></h3>
          <p>${subForum.description}</p>
        </div>
    </div>
	</c:forEach>
</div>

</c:forEach>