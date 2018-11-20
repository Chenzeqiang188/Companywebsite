<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加荣誉</title>
<link href="../../css/public/style.css" rel="stylesheet" type="text/css" />
<link href="../../css/public/css.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery.js"></script>
<script src="../js/cms.js"></script>
<link href="../../css_3/main.css" rel="stylesheet" type="text/css" />
<link href="../../css_3/media-queries.css" rel="stylesheet" type="text/css" />
<link href="../css/cms.css" rel="stylesheet" type="text/css" />
<script>
function check(){
	var flag = true;
	flag = isNull("winner","请输入获取产品/单位！") && flag;
	flag = isNull("awardUnit","请输入颁发单位！") && flag;
	//flag = isNull("sorting","请输入序号！") && flag;
	flag = isNull("honor","请输入荣誉！") && flag;
	var sorting = $("#sorting").val();
	/**
	if(sorting!="" && (isNaN(sorting) || sorting<1)){
		$("#sortingTip").html("请输入一个正整数！");
		flag = false;
	}**/
	return flag;
}
</script>
</head>
<body>
<div class="location">
	您当前的位置—添加荣誉 <span style="color: #F00">${requestScope.message }</span>
</div>
<div class="checked">
	<form action="saveOrUpdateHonor.do" method="post" onsubmit="return check()">
	<table>
		<tr>
			<td>年份：</td>
			<td>
				<select name="year">
					<c:forEach begin="1999" end="2030" varStatus="state">
						<option value="${state.index }" <c:if test="${honor.year==state.index}">selected="selected"</c:if>>${state.index }</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>获取产品/单位：</td>
			<td>
				<input type="text" id="winner" name="winner" value="${honor.winner }" />
				<span id="winnerTip"></span>
			</td>
		</tr>
		<tr>
			<td>颁发单位：</td>
			<td>
				<input type="text" id="awardUnit" name="awardUnit" value="${honor.awardUnit }" />
				<span id="awardUnitTip"></span>
			</td>
		</tr>
		<!--  
		<tr>
			<td>排序：</td>
			<td>
				<input type="text" id="sorting" name="honor.sorting" value="${honor.sorting }" />
				<span id="sortingTip"></span>
			</td>
		</tr>
		-->
		<tr>
			<td>荣誉：</td>
			<td>
				<textarea rows="5" cols="40" id="honor" name="honor">${honor.honor }</textarea>
				<span id="honorTip"></span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="提交" class="button" /><input type="reset" value="清空" class="button" />
			</td>
		</tr>
	</table>
	<input type="hidden" name="id" value="${honor.id }" />
	<input type="hidden" name="awardDate" value="${honor.awardDate }" />
	
	</form>
</div>
</body>
</html>