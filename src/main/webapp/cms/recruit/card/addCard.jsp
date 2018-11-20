<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加幻灯片</title>
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
	flag = isNull("title","请输入标题！") && flag;
	var img2 = $("#img2").val();
	if(img2==""){
		flag = isNull("img","请上传图片！") && flag;
	}
	flag = isNull("sorting","请输入序号！") && flag;
	var sorting = $("#sorting").val();
	if(sorting!="" && (isNaN(sorting) || sorting<1)){
		$("#sortingTip").html("请输入一个正整数！");
		flag = false;
	}
	return flag;
}
</script>
</head>
<body>
<div class="location">
	您当前的位置—添加幻灯片 <span style="color: #F00">${message }</span>
</div>
<div class="checked">
	<form action="saveOrUpdateCard.do" method="post" enctype="multipart/form-data" onsubmit="return check()">
	<table>
		<tr>
			<td>标题：</td>
			<td>
				<input type="text" id="title" name="card.title" value="${card.title }" />
				<span id="titleTip"></span>
			</td>
		</tr>
		<tr>
			<td>类型：</td>
			<td>
				<select name="card.cardType">
					<option value="0" <c:if test="${card.cardType==0 }">selected="selected"</c:if>>大图片</option>
					<option value="1" <c:if test="${card.cardType==1 }">selected="selected"</c:if>>小图片</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>图片：</td>
			<td>
				<input type="file" id="img" name="img" />
				<input type="hidden" id="img2" name="card.img" value="${card.img }" />
				<span id="imgTip"></span><br/>
				<c:if test="${not empty card.img }"><img style="width:200px;" src="../../${card.img }" /></c:if>
			</td>
		</tr>
		<tr>
			<td>链接：</td>
			<td>
				<input type="text" id="url" name="card.url" value="${card.url }" />
			</td>
		</tr>
		<tr>
			<td>排序：</td>
			<td>
				<input type="text" id="sorting" name="card.sorting" value="${card.sorting }" />
				<span id="sortingTip"></span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="提交" class="button" /><input type="reset" value="清空" class="button" />
			</td>
		</tr>
	</table>
	<input type="hidden" name="id" value="${card.id }" />
	</form>
</div>
</body>
</html>