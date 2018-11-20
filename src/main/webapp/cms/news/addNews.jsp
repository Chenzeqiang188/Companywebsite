<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	var editor = UE.getEditor('content');
	editor.setOpt( 'initialFrameWidth',950);
});
function check(){
	var flag = true;
	flag = isNull("title","请输入标题！") && flag;
	flag = isNull("createTime","请输入时间！") && flag;
	var img2 = $("#img2").val();
	if(img2==""){
		flag = isNull("img","请上传图片！") && flag;
	}
	flag = isNull("author","请输入作者！") && flag;
	flag = isNull("description","请输入简介！") && flag;
	flag = isNull("content","请输入正文！") && flag;
	return flag;
}
function  upd() {
    var i=document.getElementById("")

}
</script>
</head>
<body>
<div class="location">
	您当前的位置—添加新闻 <span style="color: #F00">${requestScope.message }</span>
</div>
<div class="checked">
	<form action="saveOrUpdateNews.do" method="post" enctype="multipart/form-data" onsubmit="return check()">
	<table>
		<tr>
			<td>类型：</td>
			<td>
				<select name="categoryId">
					<c:forEach var="newsCate" items="${applicationScope.newsCategory }">
						<option value="${newsCate.id }" <c:if test="${news.categoryId==newsCate.id }">selected="selected"</c:if>>${newsCate.cateName }</option>
					</c:forEach>
				</select>
				<!-- <input type="button" value="添加类别" class="button" /> -->
			</td>
		</tr>
		<tr>
			<td>标题：</td>
			<td>
				<input type="text" id="title" name="title" value="${news.title }" />
				<span id="titleTip"></span>
			</td>
		</tr>
		<tr>
            <td>作者：</td>
            <td>
                <input type="text" id="author" name="author" value="${news.author }" />
                <span id="authorTip"></span>
            </td>
        </tr>

        <tr>
            <td>时间：</td>
            <td>
				<input type="text" id="createTime" name="createTime" class="Wdate"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value="${news.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				<span id="createTimeTip"></span>
			</td>
		</tr>

		<tr>
			<td>图片：</td>
			<td>
				<input type="file" id="img" name="img" />
				<input type="hidden" id="img1" name="img1" value="${news.img}"/>
				<span id="imgTip"></span><br/>
				<c:if test="${not empty news.img }"><img style="width:200px;" src="../../${news.img }" /></c:if>
			</td>
		</tr>
		<tr style="display:none">
			<td>链接：</td>
			<td>
				<input type="text" id="url" name="url" value="${news.url }" />
			</td>
		</tr>
		<tr>
			<td>简介：</td>
			<td rowspan="2">
				<textarea rows="5" cols="40" id="description" name="description">${news.description }</textarea>
				<span id="descriptionTip"></span>
			</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td>正文：</td>
			<td>
				<textarea rows="5" cols="40" id="content" name="content">${news.content }</textarea>
				<span id="contentTip"></span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="提交" class="button" /><input type="reset" value="清空" class="button" />
			</td>
		</tr>
	</table>
	<input type="hidden" name="id" value="${news.id }"/>
	</form>
</div>
</body>
</html>