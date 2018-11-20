<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>荣誉列表</title>
<link href="../../css/public/style.css" rel="stylesheet" type="text/css" />
<link href="../../css/public/css.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery.js"></script>
<link href="../../css_3/main.css" rel="stylesheet" type="text/css" />
<link href="../../css_3/media-queries.css" rel="stylesheet" type="text/css" />
<link href="../css/cms.css" rel="stylesheet" type="text/css" />
<script>
function delHonor(id){
	if(confirm("确定删除这条荣誉？")){
		$.post("deleteHonorById.do",{'id':id},function(data){
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
	您当前的位置—荣誉列表
</div>
<div class="checked minW">
	<form id="form1" action="honorList.do" method="post">
	年份：
	<select name="year">
		<option value="0" <c:if test="${honor.year==0 }">selected="selected"</c:if>>所有</option>
		<c:forEach begin="1999" end="2030" varStatus="state">
			<option value="${state.index }" <c:if test="${honor.year==state.index}">selected="selected"</c:if>>${state.index }</option>
		</c:forEach>
	</select>
	获奖产品/单位：<input type="text" name="winner" value="${honor.winner }" />
	颁发单位：<input type="text" name="awardUnit" value="${honor.awardUnit }" />
	状态：
	<select name="state">
		<%-- <option value="-1" <c:if test="${honor.state==-1 }">selected="selected"</c:if>>所有</option> --%>
		<option value="1" <c:if test="${honor.state==1 }">selected="selected"</c:if>>有效</option>
		<option value="0" <c:if test="${honor.state==0 }">selected="selected"</c:if>>无效</option>
	</select>
	<input type="submit" value="搜索" class="button" />
	</form>
</div>
<div class="data_box">
	<table>
		<tr>
			<td>编号</td>
			<td>年份</td>
			<td>获奖产品/单位：</td>
			<td>颁发单位：</td>
			<td>荣誉</td>
			<td>状态</td>
			<td>创建时间</td>
			<td>操作</td>
		</tr>
		<c:forEach var="honor" items="${requestScope.honorList }" varStatus="state">
			<tr>
				<td>${state.count }</td>
				<td>${honor.year}</td>
				<td>${honor.winner }</td>
				<td>${honor.awardUnit }</td>
				<td>${honor.honor }</td>
				<td>
					<c:if test="${honor.state==1 }">有效(显示)</c:if>
					<c:if test="${honor.state==0 }">无效(隐藏)</c:if>
				</td>
				<td>${honor.awardDate }</td>
				<td>
					<input type="button" value="修改" class="button" onclick="window.location.href='findHonorById.do?id=${honor.id}'" />
					<input type="button" value="删除" class="button" onclick="delHonor(${honor.id})" />
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>