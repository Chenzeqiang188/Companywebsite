<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加热门游戏</title>
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
$(function(){
	var editor = UE.getEditor('content');
	editor.setOpt( 'initialFrameWidth',950);
	
	var platform = $("#platform").val();
	if(platform!=""){
		var pfs = platform.split(",");
		var pfs2 = $("input[name='platform']");
		for(var i=0;i<pfs.length;i++){
			for(var j=0;j<pfs2.length;j++){
				if(pfs2[j].value==pfs[i]){
					pfs2[j].setAttribute("checked","checked");
					continue;
				}
			}
		}
	}
	
	/* var hot = $("#hot").val();
	if(hot!="" && hot==1){
		$("input[name='game.hot']")[0].setAttribute("checked","checked");
	} */
});
function check(){
	var flag = true;
	flag = isNull("gameName","请输入游戏名！") && flag;
	var img2 = $("#img2").val();
	if(img2==""){
		flag = isNull("img","请上传图片！") && flag;
	}
	/* var qrCodeImg2 = $("#qrCodeImg2").val();
	if(qrCodeImg2==""){
		flag = isNull("qrCodeImg","请上传二维码！") && flag;
	} */
	/* var icon2 = $("#icon2").val();
	if(icon2==""){
		flag = isNull("icon","请上传图标！") && flag;
	} */
	/* flag = isNull("host","请输入官网地址！") && flag;
	flag = isNull("downloadUrl","请输入下载地址！") && flag; */
	flag = isNull("description","请输入游戏简介(热门摘要)！") && flag;
	
	var $platformTip = $("#platformTip");
	$platformTip.html("");
	var platform = $("input[name='platform']:checked");
	if(platform.length==0){
		$platformTip.html("请至少选择一个平台！");
		flag = false;
	}
	
	return flag;
}
</script>
</head>
<body>
<div class="location">
	您当前的位置—添加热门游戏 <span style="color: #F00">${requestScope.message }</span>
</div>
<div class="checked">
	<form action="saveOrUpdateGame.do" method="post" enctype="multipart/form-data" onsubmit="return check()">
	<table>
		<tr>
			<td>游戏名：</td>
			<td>
				<input type="text" id="gameName" name="gameName" value="${game.gameName }" />
				<span id="gameNameTip"></span>
			</td>
		</tr>
		<tr>
			<td>排序：</td>
			<td>
				<input type="text" id="sort" name="sort" value="${game.sort}" />
				<span id="sort"></span>
			</td>
		</tr>
		<tr>
			<td>图片：</td>
			<td>
				<input type="file" id="img" name="img" />
				<input type="hidden" id="img2" name="img1" value="${game.img }" />
				<span id="imgTip"></span><br/>
				<c:if test="${not empty game.img }"><img style="width:200px;" src="../../${game.img }" /></c:if>
			</td>
		</tr>
		<%-- <tr>
			<td>二维码：</td>
			<td>
				<input type="file" id="qrCodeImg" name="qrCodeImg" />
				<input type="hidden" id="qrCodeImg2" name="game.qrCodeImg" value="${game.qrCodeImg }" />
				<span id="qrCodeImgTip"></span><br/>
				<c:if test="${not empty game.qrCodeImg }"><img style="width:200px;" src="../../${game.qrCodeImg }" /></c:if>
			</td>
		</tr> --%>
		<%-- <tr>
			<td>图标：</td>
			<td>
				<input type="file" id="icon" name="icon" />
				<input type="hidden" id="icon2" name="game.icon" value="${game.icon }" />
				<span id="iconTip"></span><br/>
				<c:if test="${not empty game.icon }"><img style="width:200px;" src="../../${game.icon }" /></c:if>
			</td>
		</tr> --%>
		<tr>
			<td>官网地址：</td>
			<td>
				<input type="text" id="host" name="host" value="${game.host }" />
				<span id="hostTip"></span>
			</td>
		</tr>
		<tr>
			<td>下载地址：</td>
			<td>
				<input type="text" id="downloadUrl" name="downloadUrl" value="${game.downloadUrl }" />
				<span id="downloadUrlTip"></span>
			</td>
		</tr>
		<tr>
			<td>平台：</td>
			<td>
				<input type="checkbox" name="platform" value="android" />android
				<input type="checkbox" name="platform" value="ios" />ios
				<input type="checkbox" name="platform" value="pc" />pc
				<input type="hidden" id="platform" value="${game.platform }"/>
				<span id="platformTip"></span>
			</td>
		</tr>
		<tr>
			<td>是否有效：</td>
			<td>
				<input type="radio" name="state" value="1" <c:if test="${game.state==1}">checked="checked"</c:if>  />有效
				<input type="radio" name="state" value="0" <c:if test="${game.state==0}">checked="checked"</c:if> />无效
				<span id="platformTip"></span>
			</td>
		</tr>
		<%-- <tr>
			<td></td>
			<td>
				<input type="checkbox" name="game.hot" value="1" />热门游戏
				<input type="hidden" id="hot" value="${game.hot }"/>
				<span>tip:热门游戏将会在官网首页显示</span>
			</td>
		</tr> --%>
		<tr>
			<td>简介(热门摘要)：</td>
			<td>
				<textarea rows="5" cols="40" id="description" name="description">${game.description }</textarea>
				<span id="descriptionTip"></span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="提交" class="button" /><input type="reset" value="清空" class="button" />
			</td>
		</tr>
	</table>
	<input type="hidden" name="id" value="${game.id }" />
	<input type="hidden" id="hot" name="hot" value="1"/>
	</form>
</div>
</body>
</html>