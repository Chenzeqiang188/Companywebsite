<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>幻灯片列表</title>
<link href="../../css/public/css.css" rel="stylesheet" type="text/css" />
<script src="../../javascript/public/jquery.js"></script>
<link href="../../css_3/main.css" rel="stylesheet" type="text/css" />
<link href="../../css_3/media-queries.css" rel="stylesheet" type="text/css" />
<link href="../css/cms.css" rel="stylesheet" type="text/css" />
<script>
function delCard(id){
	if(confirm("确定删除这张幻灯片？")){
		$.post("deleteCardById.do",{'id':id},function(data){
			if(data==1){
				alert("删除成功！");	
				$("#form1").submit();
			}else{
				alert("删除失败！");
			}
		},"json");
	}
}
</script>
</head>
<body>
<div class="location minW">
	您当前的位置—通用活动-幻灯片列表
</div>
<div class="checked minW">
	<form id="form1" action="cardList.do" method="post">
	标题：<input type="text" name="title" value="${card.title }" />
	<input type="submit" value="搜索" class="button" />
	</form>
</div>
<div class="data_box">
	<table>
		<tr>
			<td>编号</td>
			<td>标题</td>
			<td>链接</td>
			<td>图片</td>
			<td>创建时间</td>
			<td>排序</td>
			<td>操作</td>
		</tr>
		<c:forEach var="card" items="${requestScope.cardList }" varStatus="state">
			<tr>
				<td>${state.count }</td>
				<td>${card.title }</td>
				<td><a href="${card.url }" target="_blank">${card.url }</a></td>
				<td><img style="width:100px;" src="../../${card.img }" /></td>
				<td>
					${card.createtime }
				</td>
				<td>${card.sort }</td>
				<td>
					<input type="button" value="修改" class="button" onclick="window.location.href='findCardById.do?id=${card.id}'" />
					<input type="button" value="删除" class="button" onclick="delCard(${card.id})" />
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>