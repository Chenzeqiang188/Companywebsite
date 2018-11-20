<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新闻列表</title>
<link href="../../css/public/css.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery.js"></script>
<link href="../../css_3/main.css" rel="stylesheet" type="text/css" />
<link href="../../css_3/media-queries.css" rel="stylesheet" type="text/css" />
<link href="../css/cms.css" rel="stylesheet" type="text/css" />
<script>
function delNews(id){
	if(confirm("确定删除这条新闻？")){
		$.post("delGameNewsById.do",{'id':id},function(data){
			if(data==1){
				$("#form1").submit();
			}else if(data==0){
				alert("删除失败！");
			}else{
				window.parent.location.href = "../../login.jsp";
			}
		});
	}
}
</script>
</head>
<body>
<div class="location minW">
	您当前的位置—活动新闻列表
</div>
<div class="checked minW">
	<form id="form1" action="queryActivityGameNews.do" method="post">
	新闻分类：
	<select  name="categoryId">
		<option value="-1" <c:if test="${gameNews.categoryId==-1 }">selected="selected"</c:if>>全部分类</option>
		<option value="0" <c:if test="${gameNews.categoryId==0 }">selected="selected"</c:if>>新闻</option>
		<option value="1" <c:if test="${gameNews.categoryId==1 }">selected="selected"</c:if>>游戏动态</option>
	</select>
	标题：<input type="text" name="title" value="${gameNews.title }" />
	<input type="submit" value="搜索" class="button" />
	</form>
</div>
<div class="data_box">
	<table>
		<tr>
			<td>编号</td>
			<td>标题</td>
			<td>类别</td>
			<td>简介</td>
			<td>发布时间</td>			
			<td>作者</td>
			<td>游戏id</td>
			<td>操作</td>
		</tr>
		<c:forEach var="gameNews" items="${requestScope.gameNewsList }" varStatus="state">
			<tr>
				<td>${gameNews.id }</td>
				<td>${gameNews.title }</td>
				<td>${gameNews.categoryId }</td>
				<td>${gameNews.intro }</td>
				<td>${gameNews.ceatetime }</td>
				<td>${gameNews.gameId }</td>
				<td>${gameNews.author }</td>
				
				
				<td>
					<input type="button" value="修改" class="button" onclick="window.location.href='queryActivityGameNewsById.do?id=${gameNews.id}'" />
					<input type="button" value="删除" class="button" onclick="delNews(${gameNews.id})" />
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>