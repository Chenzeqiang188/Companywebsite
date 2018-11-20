<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.good.web.domain.ActivityGame" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>游戏列表</title>
<link href="../../css/public/css.css" rel="stylesheet" type="text/css" />
<script src="../../javascript/public/jquery.js"></script>
<link href="../../css_3/main.css" rel="stylesheet" type="text/css" />
<link href="../../css_3/media-queries.css" rel="stylesheet" type="text/css" />
<link href="../css/cms.css" rel="stylesheet" type="text/css" />
<script>

function delGame(id){
	if(confirm("确定删除个游戏吗？")){
		$.post("deleteActivityGameById.do",{'id':id},function(data){
			alert(data);
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
<body >
<div class="location minW">
	您当前的位置—活动游戏列表
</div>
<div class="checked minW">
	<form id="form1"  method="post" action="findActivityGameByName.do" >
	游戏名：<input type="text" name="game.name" value="${game.name }" />

	<input type="submit" value="搜索"  class="button" />
	
	</form>
</div>
<div class="data_box">
	<table>
		<tr>
			<td>编号</td>
			<td>游戏名</td>
			<td>游戏图标</td>
			<td>下载链接</td>
			<td>简介信息</td>
			<td>游戏上架图片</td>
			<td>描述信息</td>
			<td>信息</td>
			<td>玩家数量</td>
			<td>点赞数量</td>
			<td>创建时间</td>
			<td>排序</td>
			<!-- <td>图标</td> -->
			
			<td>操作</td>
		</tr>
	
		<c:forEach var="game" items="${activityGameList }" varStatus="state">
		
			<tr>
				<td>${game.id }</td>
				<td>${game.name }</td>
				<td><img style="width:50px;" src="../../upload/${game.icon }" /></td>

				<td><a href="${game.url }" target="_blank">${game.url }</a></td>
				<td>${game.intro }</td>
				
				<td>
				
				<c:forTokens var="img" items="${game.imgs }" delims=",">
					<img style="width:50px;" src="../../upload/${img }" />
				</c:forTokens>
				
				
				</td>
				<td>${game.description }</td>
				<td>${game.info }</td>
				<td>${game.playcount }</td>
				<td>${game.endorsecount }</td>
				<td>${game.createtime }</td>
				<td>${game.sort }</td>
				
				<td>
					<input type="button" value="修改" class="button" onclick="window.location.href='findActivityGameById.do?id=${game.id}'" />
					<input type="button" value="删除" class="button" onclick="delGame(${game.id})" />
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>