<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加旗下游戏</title>
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
	flag = isNull("code","请输入礼包码")&&flag;
	flag = isNull("status","请输入礼包状态")&&flag;
	flag = isNull("gameId","请输入游戏id")&&flag;
	flag = isNull("gameName","请输入游戏名")&&flag;
	
	return flag;
}
</script>
</head>
<body>
<div class="location">
	您当前的位置—添加礼包 <span style="color: #F00">${requestScope.message }</span>
</div>
<div class="checked">
	<form action="saveOrUpdateActivityGameCode.do" method="post" onsubmit="return check()" >
	<table>
		<tr>
			<td>礼包码：</td>
			<td>
				<input type="text" id="code" name="code" value="${gameCode.code }" />
				<span id="codeTip"></span>
			</td>
		</tr>
		<tr>
			<td>领取状态：</td>
			<td>
				<input type="text" id="status" name="status" value="${gameCode.status}" />
				<span id="statusTip"></span>
			</td>
		</tr>
		<tr>
			<td>游戏id：</td>
			<td>
				<input type="text" id="gameId" name="gameId" value="${gameCode.gameId}" />
				<span id="gameIdTip"></span>
			</td>
		</tr>
		<tr>
			<td>游戏名：</td>
			<td>
				<input type="text" id="gameName" name="gameName" value="${gameCode.gameName}" />
				<span id="gameNameTip"></span>
			</td>
		</tr>	
		<tr>
			<td colspan="2">
				<input type="submit" value="提交" class="button" /><input type="reset" value="清空" class="button" />
			</td>
		</tr>
	</table>
	<input type="hidden" name="id" value="${gameCode.id }" />
	</form>
</div>
</body>
</html>