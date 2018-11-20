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
		$.post("delNewsById.do",{'id':id},function(data){
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
	您当前的位置—新闻列表
</div>
<div class="checked minW">
	<form id="form1" action="newsList.do" method="post">
	新闻分类：
		<select  name="categoryId">
			<option value="0" <c:if test="${news.categoryId==0 }">selected="selected"</c:if>>全部分类</option>
			<c:forEach var="newsCate" items="${applicationScope.newsCategory }">
				<option value="${newsCate.id }" <c:if test="${news.categoryId==newsCate.id }">selected="selected"</c:if>>${newsCate.cateName }</option>
			</c:forEach>
		</select>
	标题：<input type="text" name="title" value="${news.title }" />
	<select name="state">
		<%-- <option value="-1" <c:if test="${news.state==-1 }">selected="selected"</c:if>>所有</option> --%>
		<option value="1" <c:if test="${news.state==1 }">selected="selected"</c:if>>有效</option>
		<option value="0" <c:if test="${news.state==0 }">selected="selected"</c:if>>无效</option>
	</select>
	<input type="submit" value="搜索" class="button" />
	</form>
</div>
<div class="data_box">
	<table>
		<tr>
			<td>编号</td>
			<td>标题</td>
			<td>类别</td>
			<td>作者</td>
			<td>发布时间</td>
			<td>链接</td>
			<td>图片</td>
			<td>状态</td>
			<td>操作</td>
		</tr>
		<c:forEach var="news" items="${requestScope.newsArray }" varStatus="state">
			<tr>
				<td>${state.count }</td>
				<td>${news.title }</td>
				<td>${news.category }</td>
				<td>${news.author }</td>
				<td>${news.createTime }</td>
				<td><a href="${news.url }" target="_blank">${news.url }</a></td>
				<td><img style="width:100px;" src="../../${news.img }" /></td>
				<td>
					<c:if test="${news.state==1 }">有效(显示)</c:if>
					<c:if test="${news.state==0 }">无效(隐藏)</c:if>
				</td>
				<td>
					<input type="button" value="修改" class="button" onclick="window.location.href='findNewsById.do?id=${news.id}'" />
					<input type="button" value="删除" class="button" onclick="delNews(${news.id})" />
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>