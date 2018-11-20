<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加新闻</title>
<link href="../../css/public/style.css" rel="stylesheet" type="text/css" />
<link href="../../css/public/css.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" charset="utf-8" src="../../ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="../../ueditor/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8" src="../../ueditor/ueditor.parse.js"></script>
<link rel="stylesheet" type="text/css" href="../../ueditor/themes/default/css/ueditor.css" />
<script type="text/javascript" charset="utf-8" src="../../ueditor/lang/zh-cn/zh-cn.js"></script>
<script src="../../javascript/public/jquery.js"></script>
<script src="../../common/datePicker/WdatePicker.js"></script>
<script src="../js/cms.js"></script>
<link href="../../css_3/main.css" rel="stylesheet" type="text/css" />
<link href="../../css_3/media-queries.css" rel="stylesheet" type="text/css" />
<link href="../css/cms.css" rel="stylesheet" type="text/css" />
<script>
$(function(){
	var editor = UE.getEditor('context');
	editor.setOpt( 'initialFrameWidth',950);
});
function check(){
	var flag = true;
	flag = isNull("title","请输入标题！") && flag;	
	flag = isNull("author","请输入作者！") && flag;
	flag = isNull("intro","请输入简介！") && flag;
	flag = isNull("context","请输入内容！") && flag;
	return flag;
	
}
</script>
</head>
<body>
<div class="location">
	您当前的位置—添加活动新闻 <span style="color: #F00">${requestScope.message }</span>
</div>
<div class="checked">
	<form action="saveOrUpdateActivityGameNews.do" method="post" enctype="multipart/form-data" onsubmit="return check()">
	<table>
		<tr>
			<td>类型：</td>
			<td>
				<select name="categoryId">
				<option value="-1" <c:if test="${gameNews.categoryId==-1 }">selected="selected"</c:if>>全部分类</option>
				<option value="0" <c:if test="${gameNews.categoryId==0 }">selected="selected"</c:if>>新闻</option>
				<option value="1" <c:if test="${gameNews.categoryId==1 }">selected="selected"</c:if>>游戏动态</option>
				</select>
				<!-- <input type="button" value="添加类别" class="button" /> -->
			</td>
		</tr>
		<tr>
			<td>标题：</td>
			<td>
				<input type="text" id="title" name="title" value="${gameNews.title }" />
				<span id="titleTip"></span>
			</td>
		</tr>
		<tr>
			<td>作者：</td>
			<td>
				<input type="text" id="author" name="author" value="${gameNews.author }" />
				<span id="authorTip"></span>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>游戏id：</td>
			<td>
			<select	name="gameId">
				<c:forEach var="gameId" items="${applicationScope.gameIdList }">
					<option>${gameId }</option>
				</c:forEach>
			
			</select></td>
		</tr>

		<tr>
			<td>简介：</td>
			<td rowspan="2">
				<textarea rows="5" cols="40" id="intro" name="intro">${gameNews.intro }</textarea>
				<span id="introTip"></span>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>内容：</td>
			<td>
				<textarea rows="5" cols="40" id="context" name="context">${gameNews.context }</textarea>
				<span id="contextTip"></span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="提交" class="button" /><input type="reset" value="清空" class="button" />
			</td>
		</tr>
	</table>
	<input type="hidden" name="id" value="${gameNews.id }"/>
	</form>
</div>
</body>
</html>