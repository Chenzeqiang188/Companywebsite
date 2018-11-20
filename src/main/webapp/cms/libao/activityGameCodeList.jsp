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
	<form id="form1" action="queryActivityGameCode.do" method="post">
	领取状态：<select name="status">
				<option value="-1" <c:if test="${gameCode.status==-1 }">selected="selected"</c:if>>所有</option>
				<option value="0" <c:if test="${gameCode.status==0 }">selected="selected"</c:if>>未领取</option>
				<option value="1" <c:if test="${gameCode.status==1 }">selected="selected"</c:if>>已领取</option>
		   </select>
	<input type="submit" value="搜索" class="button" />
	</form>
</div>
<div class="data_box">
	<table>
		<tr>
			<td>编号</td>
			<td>礼包码</td>
			<td>领取状态</td>
			<td>游戏id</td>
			<td>游戏名</td>
			<td>创建时间</td>
			<td>操作</td>
		</tr>
		<c:forEach var="gameCode" items="${gameCodeList }" varStatus="state">
			<tr>
				<td>${gameCode.id }</td>
				<td>${gameCode.code }</td>
				<td>${gameCode.status }</td>
				<td>${gameCode.gameId }</td>
				<td>${gameCode.gameName }</td>
				<td>${gameCode.ceatertime }</td>				
				<td>
					<input type="button" value="修改" class="button" onclick="window.location.href='findGameById.do?id=${game.id}'" />
					<input type="button" value="删除" class="button" onclick="delGame(${gameCode.id})" />
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>