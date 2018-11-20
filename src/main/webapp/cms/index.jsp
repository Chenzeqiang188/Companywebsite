<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CMS管理平台</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/common/jquery-easyui-1.3.6/themes/black/easyui.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/common/jquery-easyui-1.3.6/themes/icon.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/common/jquery-easyui-1.3.6/jquery.easyui.min.js"></script>
<script type="text/javascript">
function open(title,href){
	$("#mainframe").tabs("add",{
	    'title':title,
	    'href':href
	});
}
function addTab(subtitle,url){
	alert(1)
	if(!$('#mainframe').tabs('exists',subtitle)){
		$('#mainframe').tabs('add',{
			title:subtitle,
			content:createFrame(url),
			closable:true
		});
	}else{
		$('#mainframe').tabs('select',subtitle);
		//$('#mm-tabupdate').click();
	}
	//tabClose();
}
</script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',height:200">
	<%@include file="../top.jsp" %>
</div>
<div data-options="region:'west',title:'菜单',split:false" title="West" style="width:200px;">
	<div class="easyui-panel" data-options="title:'新闻管理',border:false,collapsible:true">
		<a class="easyui-linkbutton" onclick="addTab('新闻列表','news/newsList.jsp')" style="width:100%;border:0;margin:2px 0;">新闻列表</a>
	</div>
	<div class="easyui-panel" data-options="title:'新闻管理',border:false,collapsible:true">
		
	</div>
	<div class="easyui-panel" data-options="title:'新闻管理',border:false,collapsible:true">
		
	</div>
</div>
<div id="mainframe" data-options="region:'center',title:'主窗口'"></div>
</body>
</html>