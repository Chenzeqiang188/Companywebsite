<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
<head>
<title>谷得cms</title>
<link rel="StyleSheet" href="css/style.css" type="text/css" />
<script type="text/javascript" src="javascript/dtree/dtree.js"></script>
<link href="css/cms/css.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />




<script src="js/jquery.min.js"></script>
<script src="js/js.left.js"></script>
<script>
	$(document).ready(
			function() {
				$(".menu1").eq(0).css("background",
						"url(images/left_menu_bg1.png) no-repeat").css("color",
						"#fff");
			})
</script>
<link href="css/left.css" rel="stylesheet" type="text/css" />

<style>
body {
	font-size: 12px;
	background: #f5f5f5;
}

.menu1 {
	color: #1e5494;
	font-weight: bold;
}

.li {
	
}

.li_click {
	background: url(images/left_menu_bg3.png);
	color: #81cdff
}

.li_clicks {
	background: url(images/left_menu_bg3.png);
	color: #81cdff
}

.menu_1 {
	display: none;
	margin-left: 20px;
	border: 0px solid #000;
	margin-top: 0px;
}

.menu_1 li a {
	color: #FFF;
	display: block;
	width: 100%;
}

.menu_1 li a:hover {
	color: #81cdff
}

.menu1 {
	background: url(images/left_menu_bg.png) no-repeat;
	width: 210px;
	cursor: pointer;
	margin: 5px;
	margin-left: 20px;
	padding: 8px 0px 8px 0px;
	text-indent: 20px;
	color: #6e6e6e;
	margin-bottom: 0px;
	border: 0px solid #000;
}

#all {
	background: #ff6600;
	cursor: pointer;
	margin: 0px;
	padding: 8px;
	text-indent: 20px;
	font-weight: bold;
	color: #fff;
	margin-bottom: 3px;
	position: fixed;
	left: 0px;
	top: 0px;
	width: 100%;
}
</style>
</head>
<body>
	<div style="margin-top: 10px;">
		<div class="menu1">新闻管理</div>
		<div class="menu_1" style="display: block;">
			<ul>
				<li><a href="cms/news/newsList.jsp" target="main">新闻列表</a></li>
				<li><a href="cms/news/addNews.jsp" target="main">添加新闻</a></li>
			</ul>
		</div>
	</div>
	<div>
		<div class="menu1">招聘信息</div>
		<div class="menu_1">
			<ul>
				<li><a href="cms/recruit/recruitList.jsp" target="main">职位列表</a></li>
				<li><a href="cms/recruit/addRecruit.jsp" target="main">发布信息</a></li>
				<li><a href="cms/recruit/recruitManage.jsp" target="main">职位类别、标签与部门</a></li>
			</ul>
		</div>
	</div>
	<div>
		<div class="menu1">图片管理</div>
		<div class="menu_1">
			<ul>
				<li><a href="cms/image/imageList.jsp" target="main">图片列表</a></li>
				<li><a href="cms/image/addImage.jsp" target="main">添加图片</a></li>
				<li><a href="cms/image/addBarImage.jsp" target="main">底部通栏</a></li>
				<li><a href="cms/card/cardList.jsp" target="main">幻灯片列表</a></li>
				<li><a href="cms/card/addCard.jsp" target="main">添加幻灯片</a></li>
			</ul>
		</div>
	</div>
	<div class="menus">
		<div class="menu1">游戏管理</div>
		<div class="menu_1">
			<ul>
				<li><a href="cms/game/gameList.jsp" target="main">游戏列表</a></li>
				<li><a href="cms/game/addHotGame.jsp" target="main">添加热门游戏</a></li>
				<li><a href="cms/game/addGame.jsp" target="main">添加旗下游戏</a></li>
			</ul>
		</div>
	</div>
	<div>
		<div class="menu1">历程与荣誉</div>
		<div class="menu_1">
			<ul>
				<li><a href="cms/history/historyList.jsp" target="main">历程列表</a></li>
				<li><a href="cms/history/addHistory.jsp" target="main">添加历程</a></li>
				<li><a href="cms/honor/honorList.jsp" target="main">荣誉列表</a></li>
				<li><a href="cms/honor/addHonor.jsp" target="main">添加荣誉</a></li>
			</ul>
		</div>
	</div>
	
	<div>
		<div class="menu1">通用活动</div>
		<div class="menu_1">
			<ul>
				<li><a href="cms/activity/addCard.jsp" target="main">新增幻灯片</a></li>
				<li><a href="cms/activity/cardList.jsp" target="main">幻灯片列表</a></li>
				<li><a href="cms/game/addActivityGame.jsp" target="main">新增热门游戏</a></li>
				<li><a href="cms/game/activityGameList.jsp" target="main">热门游戏列表</a></li>
				<li><a href="cms/news/activityNewsList.jsp" target="main">活动新闻列表</a></li>
				<li><a href="cms/news/addActivityNews.jsp" target="main">添加活动新闻</a></li>
				<li><a href="cms/libao/addActivityGameCode.jsp" target="main">添加礼包</a></li>
				<li><a href="cms/libao/activityGameCodeList.jsp" target="main">礼包列表</a></li>
			</ul>
		</div>
	</div>
	<div>
		<div class="menu1">展示板块设置</div>
		<div class="menu_1">
			<ul>
				<li><a href="cms/section/sectionList.jsp" target="main">展示板块列表</a></li>
<!--				<li><a href="cms/section/sectionEdit.jsp" target="main">编辑展示板块</a></li>-->
			</ul>
		</div>
	</div>
	<div>
		<div class="menu1">广告管理</div>
		<div class="menu_1">
			<ul>
				<li><a href="cms/advice/adviceList.jsp" target="main">广告列表</a></li>
				<li><a href="cms/advice/addAdvice.jsp" target="main">新增广告</a></li>
			</ul>
		</div>
	</div>
	<div>
		<div class="menu1">榜单管理</div>
		<div class="menu_1">
			<ul>
				<li><a href="cms/rank/rankList.jsp" target="main">榜单列表</a></li>
				<li><a href="cms/rank/rankEdit.jsp" target="main">编辑榜单</a></li>
				<li><a href="cms/rankGame/rankGameListPage.do" target="main">游戏排名列表</a></li>
				<li><a href="cms/rankGame/editRankGamePage.do?id=" target="main">编辑游戏排名</a></li>
				<li><a href="cms/carousel/carouselImgList.jsp" target="main">轮播图列表</a></li>
				<li><a href="cms/carousel/carouselImgEdit.jsp" target="main">上传轮播图</a></li>
			</ul>
		</div>
	</div>
	
</body>
</html>
