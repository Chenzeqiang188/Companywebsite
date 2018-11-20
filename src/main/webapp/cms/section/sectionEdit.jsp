<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增热门游戏</title>
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
	var showid = $("#showid").val();
	if(showid==2){
		flag = isNull("title","请填写展示板块名称！") && flag;
	}
	return flag;
}
</script>
</head>
<body>
<div class="location">
	您当前的位置—编辑显示板块 <span style="color: #F00">${requestScope.message }</span>
</div>
<div class="checked">
	<form action="editSection.do"  method="post" onsubmit="return check();" >
	<table>
		<tr>
			<td>显示板块：</td>
			<td>
			  <c:if test="${show.id ne 2 }">
			    ${show.title }
			    <input type="hidden" id="title" name="title" value="${show.title }" />
			  </c:if>
			  <c:if test="${show.id eq 2 }">
				  <input type="text" id="title" name="title" value="${show.title }" />
				  <span id="titleTip"></span>
				</c:if>
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
				<input type="submit" value="提交" class="button" /><input type="reset" value="清空" class="button" />
			</td>
		</tr>
	</table>
	<input type="hidden" id="showid" name="show.id" value="${show.id }" />
	</form>
</div>
</body>
</html>