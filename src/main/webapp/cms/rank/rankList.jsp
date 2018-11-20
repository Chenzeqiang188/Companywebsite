<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.good.web.domain.ActivityGame" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>板块列表</title>
<link href="../../css/public/css.css" rel="stylesheet" type="text/css" />
<script src="../../javascript/public/jquery.js"></script>
<link href="../../css_3/main.css" rel="stylesheet" type="text/css" />
<link href="../../css_3/media-queries.css" rel="stylesheet" type="text/css" />
<link href="../css/cms.css" rel="stylesheet" type="text/css" />
<script>

function delGame(id){
    if(confirm("确定删除吗？")){
       $.post("delRank.do",{'id':id},function (data) {
           if(data==1){
               $("#form").submit();
           }else if(data==0){
               alert("删除失败！");
           }else{
               window.parent.location.href = "../../login.jsp";
           }
       });
        //location.href='delRank.do?id='+id;
}
}
</script>
</head>
<body >
<div class="location minW">
	您当前的位置—榜单列表
</div>
<div class="checked minW">
	<form id="form"  method="post" action="rankList.do" >
   终端：<select id="type" name="type">
<!--          <option value="" >全部</option>-->
			    <option value="0" <c:if test="${show.type eq 0 }">selected</c:if>>pc端</option>
			    <option value="1" <c:if test="${show.type eq 1 }">selected</c:if>>移动端</option>
			  </select>
	榜单名称:<input type="text" id="title" name="title" value="${show.title }" />
	<input type="submit" value="搜索"  class="button" />
	
	</form>
</div>
<div class="data_box">
	<table>
		<tr>
			<td>序号</td>
			<td>榜单标题</td>
			<td>是否展示</td>
			<td>终端</td>
			<td>操作</td>
		</tr>
	
		<c:forEach var="rank" items="${rankList }" varStatus="state">
			<tr>
				<td>${state.index+1 }</td>
				<td>${rank.title }</td>
				<td><c:if test="${rank.status eq 0}">是</c:if><c:if test="${rank.status eq 1}">否</c:if></td>
				<td><c:if test="${rank.type eq 0 }">PC端</c:if><c:if test="${rank.type eq 1 }">移动端</c:if></td>
				<td>
					<c:if test="${rank.type ne 1 }">
					  <input type="button" value="修改" class="button" onclick="window.location.href='editRankPage.do?id=${rank.id}'" />
						<input type="button" value="删除" class="button" onclick="delGame(${rank.id});" />
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	<jsp:include page="../pages.jsp"></jsp:include>
</div>
</body>
</html>