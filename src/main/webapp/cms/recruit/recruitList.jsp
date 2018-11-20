<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>招聘信息列表</title>
<link href="../../css/public/style.css" rel="stylesheet" type="text/css" />
<link href="../../css/public/css.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery.js"></script>
<link href="../../css_3/main.css" rel="stylesheet" type="text/css" />
<link href="../../css_3/media-queries.css" rel="stylesheet" type="text/css" />
<link href="../css/cms.css" rel="stylesheet" type="text/css" />
<script>
function delRecruit(id){
	if(confirm("确定删除这条荣誉？")){
		$.post("delRecruitById.do",{'id':id},function(data){
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
	您当前的位置—招聘信息列表
</div>
<div class="checked minW">
	<form id="form1" action="recruitList.do" method="post">
	职位名称：<input type="text" name="position" value="${recruit.position }" />
	职位类别：
	<select name="positionType1">
		<option value="0" <c:if test="${recruit.positionType1==0 }">selected="selected"</c:if>>所有类别</option>
		<c:forEach var="p1" items="${applicationScope.positionTypeList }">
			<c:if test="${p1.typeValue==1 }">
			<option value="${p1.id }" <c:if test="${recruit.positionType1==p1.id }">selected="selected"</c:if>>${p1.typeName }</option>
			</c:if>
		</c:forEach>
	</select>
	搜索标签：
	<select name="positionType2">
		<option value="0" <c:if test="${recruit.positionType2==0 }">selected="selected"</c:if>>所有标签</option>
		<c:forEach var="p2" items="${applicationScope.positionTypeList }">
			<c:if test="${p2.typeValue==2 }">
			<option value="${p2.id }" <c:if test="${recruit.positionType2==p2.id }">selected="selected"</c:if>>${p2.typeName }</option>
			</c:if>
		</c:forEach>
	</select>
	用人部门：
	<select name="deptId">
		<option value="0" <c:if test="${recruit.deptId==0 }">selected="selected"</c:if>>所有部门</option>
		<c:forEach var="dept" items="${applicationScope.deptList }">
			<option value="${dept.id }" <c:if test="${recruit.deptId==dept.id }">selected="selected"</c:if>>${dept.deptName }</option>
		</c:forEach>
	</select>
	<br />
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;状态：
	<select name="state">
		<%-- <option value="-1" <c:if test="${recruit.state==-1 }">selected="selected"</c:if>>所有</option> --%>
		<option value="1" <c:if test="${recruit.state==1 }">selected="selected"</c:if>>有效</option>
		<option value="0" <c:if test="${recruit.state==0 }">selected="selected"</c:if>>无效</option>
	</select>
	<input type="submit" value="搜索" class="button" />
	</form>
</div>
<div class="data_box">
	<table>
		<tr>
			<td>编号</td>
			<td>职位名称</td>
			<td>职位类别</td>
			<td>用人单位</td>
			<td>搜索标签</td>
			<td>招聘人数</td>
			<td>状态</td>
			<td>招聘类型</td>
			<td>操作</td>
		</tr>
		<c:forEach var="recruit" items="${requestScope.recruitArray }" varStatus="state">
			<tr>
				<td>${state.count }</td>
				<td>${recruit.position}</td>
				<td>${recruit.positionType1 }</td>
				<td>${recruit.dept }</td>
				<td>${recruit.positionType2 }</td>
				<td>${recruit.recruitNum }</td>
				<td>
					<c:if test="${recruit.state==1 }">有效(显示)</c:if>
					<c:if test="${recruit.state==0 }">无效(隐藏)</c:if>
				</td>
				<td>
					<c:if test="${recruit.type==0 }">社会招聘</c:if>
					<c:if test="${recruit.type==1 }">校园招聘</c:if>
				</td>
				<td>
					<input type="button" value="修改" class="button" onclick="window.location.href='findRecruitById.do?id=${recruit.id}'" />
					<input type="button" value="删除" class="button" onclick="delRecruit(${recruit.id})" />
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>