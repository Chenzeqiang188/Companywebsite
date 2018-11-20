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
	flag = isNull("name","请输入游戏名！") && flag;
	flag = isNull("url","请输入下载地址！") && flag;
	flag = isNull("intro","请输入简介信息！") && flag;
	flag = isNull("description","请输入描述信息！") && flag;
	flag = isNull("info","请输入信息！") && flag;
	flag = isNull("playcount","请输入玩家数量！") && flag;
	flag = isNull("endorsecount","请输入点赞数量！") && flag;
	flag = isNull("sort","请输入排序！") && flag;
	var icon2 = $("#icon2").val();
	if(icon2==""){
		flag = isNull("icon","请上传图标！") && flag;
	}
	var img2 = $("#img2").val();
	if(img2==""){
		flag = isNull("img","请上传图片！") && flag;
	}

	
	return flag;
}
</script>
</head>
<body>
<div class="location">
	您当前的位置—新增热门游戏 <span style="color: #F00">${requestScope.message }</span>
</div>
<div class="checked">
	<form action="saveOrUpdateActivityGame.do"  method="post" enctype="multipart/form-data"   onsubmit="return check()" >
	<table>
		<tr>
			<td>游戏名：</td>
			<td>
				<input type="text" id="name" name="name" value="${game.name }" />
				<span id="nameTip"></span>
			</td>
		</tr>
		<tr>
			<td>游戏图标：</td>
			<td>
				<input type="file" id="icon" name="icon"/>
				<input type="hidden" id="icon2" name="icon1" value="${game.icon }" />
				<span id="iconTip"></span><br/>
				<c:if test="${not empty game.icon }"><img style="width:200px;" src="../../upload/${game.icon }" /></c:if>
				
			</td>
		</tr>
		<tr>
			<td>下载链接：</td>
			<td>
				<input type="text" id="url" name="url" value="${game.url }" />
				<span id="urlTip"></span>
			</td>
		</tr>
		<tr>
			<td>简介信息：</td>
			<td>
				<textarea rows="5" cols="40" id="intro" name="intro">${game.intro }</textarea>
				<span id="introTip"></span>
			</td>
		</tr>
		<tr>
			<td>游戏上架图片：</td>
			<td>
				<input type="file" id="img" name="imgs" multiple="multiple"/>
				<input type="hidden" id="img2" name="imgs1" value="${game.imgs }" />
				<span id="imgTip"></span><br/>
				<c:forTokens items="${game.imgs }" delims="," var="img">
					<c:if test="${not empty img }"><img style="width:200px;" src="../../upload/${img }" /></c:if>
				</c:forTokens>
				
			</td>
		</tr>
		<tr>
			<td>描述信息：</td>
			<td>
				<textarea rows="5" cols="40" id="description" name="description">${game.description }</textarea>
				<span id="descriptionTip"></span>
			</td>
		</tr>
		<tr>
			<td>信息：</td>
			<td>
				<textarea rows="5" cols="40" id="info" name="info">${game.info }</textarea>
				<span id="infoTip"></span>
			</td>
		</tr>
		<tr>
			<td>玩家数量：</td>
			<td>
				<input type="text" id="playcount" name="playcount" value="${game.playcount}" />
				<span id="playcountTip"></span>
			</td>
		</tr>
		
		<tr>
			<td>点赞数量：</td>
			<td>
				<input type="text" id="endorsecount" name="endorsecount" value="${game.endorsecount}" />
				<span id="endorsecountTip"></span>
			</td>
		</tr>
		
		<tr>
			<td>排序：</td>
			<td>
				<input type="text" id="sort" name="sort" value="${game.sort}" />
				<span id="sortTip"></span>
			</td>
		</tr>

		<tr>
			<td colspan="2">
				<input type="submit" value="提交" class="button" /><input type="reset" value="清空" class="button" />
			</td>
		</tr>
	</table>
	<input type="hidden" name="id" value="${game.id }" />
	<!--  <input type="hidden" id="hot" name="game.hot" value="1"/>-->
	</form>
</div>
</body>
</html>