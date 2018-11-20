<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.good.web.domain.ActivityGame" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>排行榜列表</title>
<link href="../../css/public/css.css" rel="stylesheet" type="text/css" />
<script src="../../javascript/public/jquery.js"></script>
<link href="../../css_3/main.css" rel="stylesheet" type="text/css" />
<link href="../../css_3/media-queries.css" rel="stylesheet" type="text/css" />
<link href="../css/cms.css" rel="stylesheet" type="text/css" />
<script>
  function delRank(obj){
     $("#rankgameid").val(obj);
     $("#form").attr("action","delRankGame.do");
      $("#form").submit();
  }
  
  
  function showpos(obj){
  if(obj==1){
   $("#rankid option").remove(); 
   $("#rankid").append('<option value="">全部</option>');
   $("#rankid").append('<c:forEach items="${ranksList}" var="rank"><c:if test="${rank.type eq 1}">'+
				      '<option value="${rank.id }" <c:if test="${rank.id eq rankGame.rankId}">selected</c:if>>${rank.title }</option>'+
			      '</c:if></c:forEach>');  
  }else{
    $("#rankid option").remove(); 
    $("#rankid").append('<option value="">全部</option>');
    $("#rankid").append('<c:forEach items="${ranksList}" var="rank"><c:if test="${rank.type eq 0}">'+
				      '<option value="${rank.id }" <c:if test="${rank.id eq rankGame.rankId}">selected</c:if>>${rank.title }</option>'+
			      '</c:if></c:forEach>');
  }
}

$(function(){
   showpos(${showtype});
});
</script>
</head>
<body >
<div class="location minW">
	您当前的位置—游戏排行列表
</div>
<div class="checked minW">
	<form id="form"  method="post" action="rankGameList.do" >
	   <input type="hidden"  id="rankgameid" name="id" />
	终端： <select name="showtype" onchange="showpos(this.value);">
			    <option value="0" <c:if test="${not empty showtype and showtype eq 0 }">selected</c:if>>PC端</option>
			    <option value="1" <c:if test="${not empty showtype  and showtype eq 1 }">selected</c:if>>移动端</option>
			  </select>
      榜单:<select id="rankid" name="rankId">
			  </select>
	游戏名称:<input type="text" id="gameName" name="gameName" value="${rankGame.gameName }" />
	<input type="submit" value="搜索"  class="button" />

		</form>
</div>
<div class="data_box">
	<table>
		<tr>
			<td>排名</td>
			<td>所属榜单</td>
			<td>游戏名称</td>
			<td>游戏类型</td>
			<td>是否添加reffer标识</td>
			<td>推广链接</td>
			<td>描述</td>
			<td>是否有效</td>
			<td>操作</td>
		</tr>
	
		<c:forEach var="s" items="${rankGamelist }" varStatus="state">
			<tr>
				<td>${s.gameRanking }</td>
				<td>
				 <c:forEach items="${ranksList}" var="rank">
				      <c:if test="${rank.id eq s.rankId}">${rank.title }</c:if>
			    </c:forEach>
			  </td>
				<td>${s.gameName }</td>
				<td>${s.gameType }</td>
				<td><c:if test="${s.reffer==0 }">是</c:if><c:if test="${s.reffer==1 }">否</c:if></td>
				<td>${s.downloadUrl }</td>
				<td>${s.info }</td>
			  <td><c:if test="${s.status==0 }">有效</c:if><c:if test="${s.status==1 }">无效</c:if></td>
				<td>
					<input type="button" value="修改" class="button" onclick="window.location.href='editRankGamePage.do?id=${s.id}&type=${s.type }'" />
					<input type="button" value="删除" class="button" onclick="delRank(${s.id})" />
				</td>
			</tr>
		</c:forEach>
	</table>
	<jsp:include page="../pages.jsp"></jsp:include>
</div>
</body>
</html>