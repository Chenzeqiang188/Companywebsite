<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加招聘信息</title>
<link href="../../css/public/style.css" rel="stylesheet" type="text/css" />
<link href="../../css/public/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="../../ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="../../ueditor/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8" src="../../ueditor/ueditor.parse.js"></script>
<link rel="stylesheet" type="text/css" href="../../ueditor/themes/default/css/ueditor.css" />
<script type="text/javascript" charset="utf-8" src="../../ueditor/lang/zh-cn/zh-cn.js"></script>
<script src="../../js/jquery.js"></script>
<script src="../../common/My97DatePicker/WdatePicker.js"></script>
<script src="../js/cms.js"></script>
<link href="../../css_3/main.css" rel="stylesheet" type="text/css" />
<link href="../../css_3/media-queries.css" rel="stylesheet" type="text/css" />
<link href="../css/cms.css" rel="stylesheet" type="text/css" />
<script>
$(function(){
	var editor1 = UE.getEditor('duty');
	editor1.setOpt( 'initialFrameWidth',950);
	var editor2 = UE.getEditor('required');
	editor2.setOpt( 'initialFrameWidth',950);
});
function check(){
	var flag = true;
	flag = isNull("position","请输入职位名称！") && flag;
	flag = isNull("recruitNum","请输入招聘人数！") && flag;
	flag = isNull("duty","请输入职责！") && flag;
	flag = isNull("required","请输入任职要求！") && flag;
	var recruitNum = $("#recruitNum").val();
	if(recruitNum!="" && (isNaN(recruitNum) || recruitNum<1)){
		if(recruitNum!="若干"){
			$("#recruitNumTip").html("请输入一个正整数！(如果不确定人数，请输入\"若干\")");
			flag = false;
		}
	}
	return flag;
}
</script>
</head>
<body>
<div class="location">
	您当前的位置—发布招聘信息 <span style="color: #F00">${requestScope.message }</span>
</div>
<div class="checked">
	<form action="saveOrUpdateRecruit.do" method="post" onsubmit="return check()">
	<table>
		<tr>
			<td>职位名称：</td>
			<td>
				<input type="text" id="position" name="position" value="${recruit.position }" />
				<span id="positionTip"></span>
			</td>
		</tr>
		<tr>
			<td>职位类别：</td>
			<td>
				<select name="positionType1">
					<c:forEach var="p1" items="${applicationScope.positionTypeList }">
						<c:if test="${p1.typeValue==1 }">
						<option value="${p1.id }" <c:if test="${recruit.positionType1==p1.id }">selected="selected"</c:if>>${p1.typeName }</option>
						</c:if>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>搜索标签：</td>
			<td>
				<select name="positionType2">
					<c:forEach var="p2" items="${applicationScope.positionTypeList }">
						<c:if test="${p2.typeValue==2 }">
						<option value="${p2.id }" <c:if test="${recruit.positionType2==p2.id }">selected="selected"</c:if>>${p2.typeName }</option>
						</c:if>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>用人部门：</td>
			<td>
				<select name="deptId">
					<c:forEach var="dept" items="${applicationScope.deptList }">
						<option value="${dept.id }" <c:if test="${recruit.deptId==dept.id }">selected="selected"</c:if>>${dept.deptName }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>招聘类型：</td>
			<td>
				<select name="type">
					<option value="0" <c:if test="${recruit.type==0 }">selected="selected"</c:if>>社会招聘</option>
					<option value="1" <c:if test="${recruit.type==1 }">selected="selected"</c:if>>校园招聘</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>招聘人数：</td>
			<td>
				<input type="text" id="recruitNum" name="recruitNum" value="${recruit.recruitNum }" />
				<span id="recruitNumTip"></span>
			</td>
		</tr>
		<tr>
			<td>职责：</td>
			<td>
				<textarea rows="5" cols="40" id="duty" name="duty">${recruit.duty }</textarea>
				<span id="dutyTip"></span>
			</td>
		</tr>
		<tr>
			<td>任职要求：</td>
			<td>
				<textarea rows="5" cols="40" id="required" name="required">${recruit.required }</textarea>
				<span id="requiredTip"></span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="提交" class="button" /><input type="reset" value="清空" class="button" />
			</td>
		</tr>
	</table>
	<input type="hidden" name="id" value="${recruit.id }" />
	</form>
</div>
</body>
</html>