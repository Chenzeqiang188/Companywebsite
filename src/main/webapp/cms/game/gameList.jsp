<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>游戏列表</title>
<link href="../../css/public/css.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery.js"></script>
<link href="../../css_3/main.css" rel="stylesheet" type="text/css" />
<link href="../../css_3/media-queries.css" rel="stylesheet" type="text/css" />
<link href="../css/cms.css" rel="stylesheet" type="text/css" />
<script>
function delGame(id){
	if(confirm("确定删除这个游戏？")){
		$.post("deleteGameById.do",{'id':id},function(data){
			if(data==1){
				$("#form1").submit();
			}else if(data==0){
				alert("删除失败！");
			}else{
				window.location.href = "../../login.jsp";
			}
		});
	}
}
</script>
</head>
<body>
<div class="location minW">
	您当前的位置—游戏列表
</div>
<div class="checked minW">
	<form id="form1" action="gameList.do" method="post">
	游戏名：<input type="text" name="gameName" value="${game.gameName }" />
	类型：
	<select name="hot">
		<option value="-1" <c:if test="${game.hot==-1 }">selected="selected"</c:if>>所有</option>
		<option value="1" <c:if test="${game.hot==1 }">selected="selected"</c:if>>热门游戏</option>
		<option value="0" <c:if test="${game.hot==0 }">selected="selected"</c:if>>旗下游戏</option>
	</select>
	状态：
	<select name="state">
		<%-- <option value="-1" <c:if test="${game.state==-1 }">selected="selected"</c:if>>所有</option> --%>
		<option value="1" <c:if test="${game.state==1 }">selected="selected"</c:if>>有效</option>
		<option value="0" <c:if test="${game.state==0 }">selected="selected"</c:if>>无效</option>
	</select>
	<input type="submit" value="搜索" class="button" />
	</form>
</div>
<div class="data_box">
	<table>
		<tr>
			<td>编号</td>
			<td>游戏名</td>
			<td>排序</td>
			<td>平台</td>
			<td>官网</td>
			<td>下载链接</td>
			<td>热门</td>
			<td>图片</td>
			<td>二维码</td>
			<!-- <td>图标</td> -->
			<td>状态</td>
			<td>操作</td>
		</tr>
		<c:forEach var="game" items="${requestScope.gameList }" varStatus="state">
			<tr>
				<td>${state.count }</td>
				<td>${game.gameName }</td>
				<td>${game.sort }</td>
				<td>
					<c:forTokens var="platform" items="${game.platform }" delims=",">
						<c:if test="${platform eq 'android' }">安卓<br /></c:if>
						<c:if test="${platform eq 'ios' }">苹果<br /></c:if>
						<c:if test="${platform eq 'pc' }">PC</c:if>
					</c:forTokens>
				</td>
				<td><a href="${game.host }" target="_blank">${game.host }</a></td>
				<td><a href="${game.downloadUrl }" target="_blank">${game.downloadUrl }</a></td>
				<td>
					<c:if test="${game.hot==1 }">热门游戏</c:if>
					<c:if test="${game.hot==0 }">旗下游戏</c:if>
				</td>
				<td><img style="width:100px;" src="../../${game.img }" /></td>
				<td>
					<c:if test="${game.hot==0 and not empty game.qrCodeImg}"><img style="width:100px;" src="../../${game.qrCodeImg }" /></c:if>
					<c:if test="${game.hot==1 }">×</c:if>
				</td>
				<%-- <td><img style="width:100px;" src="../../${game.icon }" /></td> --%>
				<td>
					<c:if test="${game.state==1 }">有效(显示)</c:if>
					<c:if test="${game.state==0 }">无效(隐藏)</c:if>
				</td>
				<td>
					<input type="button" value="修改" class="button" onclick="window.location.href='findGameById.do?id=${game.id}'" />
					<input type="button" value="删除" class="button" onclick="delGame(${game.id})" />
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>