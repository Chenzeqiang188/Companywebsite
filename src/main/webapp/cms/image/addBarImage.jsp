<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加图片</title>
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
/* $(function(){
	var editor = UE.getEditor('content');
	editor.setOpt( 'initialFrameWidth',950);
}); */
function check(){
	var flag = true;
	flag = isNull("title","请输入标题！") && flag;
	//flag = isNull("englishTitle","请输入英文标题！") && flag;
	var img2 = $("#img2").val();
	if(img2==""){
		flag = isNull("img","请上传图片！") && flag;
	}
	flag = isNull("sorting","请输入序号！") && flag;
	//flag = isNull("content","请输入内容！") && flag;
	
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
	您当前的位置—添加图片 <span style="color: #F00">${requestScope.message }</span>
</div>
<div class="checked">
	<form action="saveOrUpdateImage.do" method="post" enctype="multipart/form-data" onsubmit="return check()">
	<table>
		<tr>
			<td>标题：</td>
			<td>
				<input type="text" id="title" name="title" value="${image.title }" />
				<span id="titleTip"></span>
			</td>
		</tr>
		<%-- <tr>
			<td>英文标题：</td>
			<td>
				<input type="text" id="englishTitle" name="image.englishTitle" value="${image.englishTitle }" />
				<span id="englishTitleTip"></span>
			</td>
		</tr> --%>
		<tr>
			<td>图片：</td>
			<td>
				<input type="file" id="img" name="img" />
				<input type="hidden" id="img2" name="img1" value="${image.img }" />
				<span id="imgTip"></span><br/>
				<c:if test="${not empty image.img }"><img style="width:200px;" src="../../${image.img }" /></c:if>
			</td>
		</tr>
		<%-- <tr>
			<td>类型：</td>
			<td>
				<select name="image.imgType">
					<option value="TD" <c:if test="${image.imgType eq 'TD' }">selected="selected"</c:if>>培训发展(training and development)</option>
					<option value="MT" <c:if test="${image.imgType eq 'MT' }">selected="selected"</c:if>>管理团队(management team)</option>
					<option value="CB" <c:if test="${image.imgType eq 'CB' }">selected="selected"</c:if>>薪资福利(Compensation Benefit)</option>
					<option value="CI" <c:if test="${image.imgType eq 'CI' }">selected="selected"</c:if>>公司简介(company introduce)</option>
					<option value="EV" <c:if test="${image.imgType eq 'EV' }">selected="selected"</c:if>>企业愿景(entrepreneurial vision)</option>
					<option value="EL" <c:if test="${image.imgType eq 'EL' }">selected="selected"</c:if>>员工生活(employee life)</option>
					<option value="WC" <c:if test="${image.imgType eq 'WC' }">selected="selected"</c:if>>办公环境(working condition)</option>
				</select>
			</td>
		</tr> --%>
		<tr>
			<td>排序：</td>
			<td>
				<input type="text" id="sorting" name="sorting" value="${image.sorting }" />
				<span id="sortingTip"></span>
			</td>
		</tr>
		<%-- <tr>
			<td>内容：</td>
			<td>
				<textarea rows="5" cols="40" id="content" name="image.content">${image.content }</textarea>
				<span id="contentTip"></span>
			</td>
		</tr> --%>
		<tr>
			<td colspan="2">
				<input type="submit" value="提交" class="button" /><input type="reset" value="清空" class="button" />
			</td>
		</tr>
	</table>
	<input type="hidden" name="id" value="${image.id }" />
	<input type="hidden" name="imgType" value="bar" />
	<input type="hidden" id="englishTitle" name="englishTitle" value="×" />
	<input type="hidden" id="content" name="content" value="×" />
	</form>
</div>
</body>
</html>