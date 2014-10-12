<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<base href="/MyCommunity/" >
    <title>Hellow Bootstrap V3</title>
    <!-- 移动优先，保持当前绘制和触屏缩放 -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="Content/bootstrap-3.0.3/css/bootstrap.css" rel="stylesheet" media="screen">
    <!-- Custom CSS -->
    <link href="Content/css/main.css" rel="stylesheet">
</head>
<body>
    <div class="container">
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
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="#">个人主页</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">我的邮箱<b class="caret"></b></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">收件箱</a></li>
                                <li><a href="#">发件箱</a></li>
                                <li class="divider"></li>
                                <li><a href="#">回收站</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="主题">
                        </div>
                        <button type="submit" class="btn btn-default">搜索</button>
                    </form>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#">登录</a></li>
                        <li><a href="#">注册</a></li>
                    </ul>
                </div>
            </nav>
        </header>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-lg-6">
                <div id="banner" class="carousel slide">
                    <ol class="carousel-indicators">
                        <li data-target="#banner" data-slide-to="0" class=""></li>
                        <li data-target="#banner" data-slide-to="1" class=""></li>
                        <li data-target="#banner" data-slide-to="2" class=""></li>
                        <li data-target="#banner" data-slide-to="3" class="active"></li>
                    </ol>
                    <!-- Wrapper for slides -->
                    <div class="carousel-inner">
                        <div class="item">
                            <img src="Content/pictures/banner/okwu.jpg" alt="OKWU.edu Homepage">
                        </div>
                        <div class="item">
                            <img src="Content/pictures/banner/okwu-athletics.jpg" alt="OKWU Athletics Homepage">
                        </div>
                        <div class="item">
                            <img src="Content/pictures/banner/bartlesvillecf.jpg" alt="Bartlesville Community Foundation">
                        </div>
                        <div class="item active">
                            <img src="Content/pictures/banner/emancipation.jpg" alt="Emancipation Stories">
                        </div>
                    </div><!-- /.carousel-inner -->

                    <!-- Controls -->
                    <a class="left carousel-control" href="#banner" data-slide="prev">
                        <span class="chevron">&lt;</span>
                    </a>
                    <a class="right carousel-control" href="#banner" data-slide="next">
                        <span class="chevron">&gt;</span>
                    </a>
                </div>
            </div>
            <div class="col-md-6">
                <h4><strong>热门话题</strong></h4>
                <ul class="list-group">
                  <li class="list-group-item"><span class="badge">14</span>Cras justo odio</li>
                  <li class="list-group-item"><span class="badge">33</span>Dapibus ac facilisis in</li>
                  <li class="list-group-item"><span class="badge">11</span>Morbi leo risus</li>
                  <li class="list-group-item"><span class="badge">41</span>Porta ac consectetur ac</li>
                  <li class="list-group-item"><span class="badge">21</span>Vestibulum at eros</li>
                  <li class="list-group-item"><span class="badge">21</span>Vestibulum at eros</li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                  <div class="panel-heading">讨论区</div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-3">
               <div class="thumbnail">
                  <img src="Content/pictures/forum/forum_1_icon.png" alt="">
                  <h3>XXX 讨论区</h3>
                  <p>详细介绍详细介绍详细介绍详细介绍...</p>
                </div>
            </div>
            <div class="col-md-3">
               <div class="thumbnail">
                  <img src="Content/pictures/forum/forum_2_icon.png" alt="">
                  <h3>XXX 讨论区</h3>
                  <p>详细介绍详细介绍详细介绍详细介绍...</p>
                </div>
            </div>
            <div class="col-md-3">
               <div class="thumbnail">
                  <img src="Content/pictures/forum/forum_3_icon.png" alt="">
                  <h3>XXX 讨论区</h3>
                  <p>详细介绍详细介绍详细介绍详细介绍...</p>
                </div>
            </div> 
            <div class="col-md-3">
               <div class="thumbnail">
                  <img src="Content/pictures/forum/forum_4_icon.png" alt="">
                  <h3>XXX 讨论区</h3>
                  <p>详细介绍详细介绍详细介绍详细介绍...</p>
                </div>
            </div>                   
            <div class="col-md-3">
               <div class="thumbnail">
                  <img src="Content/pictures/forum/forum_5_icon.png" alt="">
                  <h3>XXX 讨论区</h3>
                  <p>详细介绍详细介绍详细介绍详细介绍...</p>
                </div>
            </div>                    
            <div class="col-md-3">
               <div class="thumbnail">
                  <img src="Content/pictures/forum/forum_6_icon.png" alt="">
                  <h3>XXX 讨论区</h3>
                  <p>详细介绍详细介绍详细介绍详细介绍...</p>
                </div>
            </div>                     
            <div class="col-md-3">
               <div class="thumbnail">
                  <img src="Content/pictures/forum/forum_7_icon.png" alt="">
                  <h3>XXX 讨论区</h3>
                  <p>详细介绍详细介绍详细介绍详细介绍...</p>
                </div>
            </div>                     
            <div class="col-md-3">
               <div class="thumbnail">
                  <img src="Content/pictures/forum/forum_8_icon.png" alt="">
                  <h3>XXX 讨论区</h3>
                  <p>详细介绍详细介绍详细介绍详细介绍...</p>
                </div>
            </div>                     
        </div>
    </div>
    <div class="container">
        <footer id="footer" class="panel-footer">
            <p>手机论坛 xda.cn </p>
            <p>Designed by Sam Chen </p>
        </footer>
    </div>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="Content/script/jquery-1.10.2.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="Content/bootstrap-3.0.3/js/bootstrap.js"></script>
</body>
</html>