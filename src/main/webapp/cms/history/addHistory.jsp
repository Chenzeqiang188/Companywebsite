<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加历程</title>
<link href="../../css/public/style.css" rel="stylesheet" type="text/css" />
<link href="../../css/public/css.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery.js"></script>
<script src="../../common/datePicker/WdatePicker.js"></script>
<script src="../js/cms.js"></script>
<link href="../../css_3/main.css" rel="stylesheet" type="text/css" />
<link href="../../css_3/media-queries.css" rel="stylesheet" type="text/css" />
<link href="../css/cms.css" rel="stylesheet" type="text/css" />
<script>
function check(){
	var flag = true;
	flag = isNull("historyDate","请输入时间！") && flag;
	flag = isNull("content","请输入历程！") && flag;
	return flag;
}
</script>
</head>
<body>
<div class="location">
	您当前的位置—添加历程 <span style="color: #F00">${requestScope.message }</span>
</div>
<div class="checked">
	<form action="saveOrUpdateHistory.do" method="post" onsubmit="return check()">
	<table>
		<tr>
			<td>年份：</td>
			<td>
				<select name="year">
					<c:forEach begin="1999" end="2030" varStatus="state">
						<option value="${state.index }" <c:if test="${history.year==state.index}"></c:if>>${state.index}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>时间：</td>
			<td>
				<input type="text" id="historyDate" name="historyDate" class="Wdate"
				onfocus="WdatePicker()" value="${history.historyDate }" />
				<span id="historyDateTip"></span>
			</td>
		</tr>
		<tr>
			<td>历程：</td>
			<td>
				<textarea rows="5" cols="40" id="content" name="content">${history.content }</textarea>
				<span id="contentTip"></span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="提交" class="button" /><input type="reset" value="清空" class="button" />
			</td>
		</tr>
	</table>
	<input type="hidden" name="id" value="${history.id }" />
	</form>
</div>
</body>
</html>