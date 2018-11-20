<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增榜单</title>
<link href="../../css/public/style.css" rel="stylesheet" type="text/css" />
<link href="../../css/public/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="../../ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="../../ueditor/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8" src="../../ueditor/ueditor.parse.js"></script>
<link rel="stylesheet" type="text/css" href="../../ueditor/themes/default/css/ueditor.css" />
<script type="text/javascript" charset="utf-8" src="../../ueditor/lang/zh-cn/zh-cn.js"></script>
<script src="../../js/jquery.js"></script>
<script src="../js/cms.js"></script>
<link href="../../css_3/main.css" rel="stylesheet" type="text/css" />
<link href="../../css_3/media-queries.css" rel="stylesheet" type="text/css" />
<link href="../css/cms.css" rel="stylesheet" type="text/css" />
<script>
function check(){
	var flag = true;
	flag = isNull("title","请填写榜单名称！") && flag;
	
	return flag;
}
</script>
</head>
<body>
<div class="location">
	您当前的位置—编辑榜单 <span style="color: #F00">${requestScope.message }</span>
</div>
<div class="checked">
	<form action="addRank.do"  method="post" onsubmit="return check();" >
	<table>
	  <tr>
			<td>终端：</td>
			<td>
			  <select id="type" name="type">
			    <option value="0" <c:if test="${show.type eq 0 }">selected</c:if>>pc端</option>
<!--			    <option value="1" <c:if test="${show.type eq 1 }">selected</c:if>>移动端</option>-->
			  </select>
			</td>
		</tr>
		<tr>
			<td>榜单标题：</td>
			<td>
				<input type="text" id="title" name="title" value="${show.title }" />
				<span id="titleTip"></span>
			</td>
		</tr>
		<tr>
			<td>是否开启：</td>
			<td>
				<input type="radio" name="status" value="0" checked="checked" />是
				<input type="radio" name="status" value="1" <c:if test="${show.status eq 1}">checked</c:if>/>否
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="提交" class="button"/>
				<input type="reset" value="清空" class="button" />
			</td>
		</tr>
	</table>
	<input type="hidden" id="showid" name="id" value="${show.id }" />
	</form>
</div>
</body>
</html>