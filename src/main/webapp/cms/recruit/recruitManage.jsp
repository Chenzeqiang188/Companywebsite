<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>职位类别、标签与部门</title>
<link href="../../css/public/style.css" rel="stylesheet" type="text/css" />
<link href="../../css/public/css.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery.js"></script>
<link href="../../css_3/main.css" rel="stylesheet" type="text/css" />
<link href="../../css_3/media-queries.css" rel="stylesheet" type="text/css" />
<link href="../css/cms.css" rel="stylesheet" type="text/css" />
<style type="text/css">
li{display:inline-block;width:auto;font-size:14px;}
.myBlock{
	width:330px;
	height:100%;
	min-height:300px;
	float:left;
	margin:10px;
}
._title{font-size: 14px;padding-left:10px;color: #666}
</style>
<script type="text/javascript">
function add(obj){
	var id = obj.getAttribute("lang");
	var typeName = $("#"+id).val();
	if(typeName==""){
		alert("不能添加空值！");
	}else{
		var url;
		var typeValue;
		if(id=="type1"){
			url = "addPositionType.do";
			typeValue = 1;
		}else if(id=="type2"){
			url = "addPositionType.do";
			typeValue = 2;
		}else{
			url = "addDept.do";
			typeValue = 0;
		}
		$.post(url,{
			'typeName':typeName,
			'typeValue':typeValue
		},function(data){
			if(data==1){
				window.location.reload(true);
			}else if(data==0){
				alert("添加失败！");
			}else{
				window.parent.location.href = "../../login.jsp";
			}
		});
	}
}
function del(obj,id){
	if(confirm("是否删除此项？")){
		var type = obj.getAttribute("lang");
		var url,typeValue;
		if(type=="type1"){
			url = "delPositionType.do";
		}else if(type=="type2"){
			url = "delPositionType.do";
		}else{
			url = "delDept.do";
		}
		$.post(url,{
			'id':id
		},function(data){
			if(data==1){
				window.location.reload(true);
			}else if(data==0){
				alert("删除失败！");
			}else{
				window.parent.location.href = "../../login.jsp";
			}
		});
	}
}
</script>
</head>
<body>
<div class="location">
	您当前的位置—职位类别、标签与部门
	<span>${requestScope.message }</span>
</div>
<div class="checked">
	<div style="width:100%;min-width:1100px;">
		职位类别：<input type="text" id="type1" /><input type="button" class="button" lang="type1" value="添加" onclick="add(this)">
		搜索标签：<input type="text" id="type2" /><input type="button" class="button" lang="type2" value="添加" onclick="add(this)">
		部门：<input type="text" id="dept" /><input type="button" class="button" lang="dept" value="添加" onclick="add(this)">
	</div>
</div>

<div style="width:100%;min-width:1100px;">
	<div class="myBlock">
		<div class="_title">职位类别：</div>
		<ul>
		<c:forEach var="p1" items="${applicationScope.positionTypeList }">
			<c:if test="${p1.typeValue==1 }">
				<li>
					${p1.typeName }
					<input type="button" value="删除" lang="type1" onclick="del(this,${p1.id})" />
				</li>
			</c:if>
		</c:forEach>
		</ul>
	</div>
	<div class="myBlock">
		<div class="_title">搜索标签：</div>
		<ul>
		<c:forEach var="p2" items="${applicationScope.positionTypeList }">
			<c:if test="${p2.typeValue==2 }">
				<li>
					${p2.typeName }
					<input type="button" value="删除" lang="type2" onclick="del(this,${p2.id})" />
				</li>
			</c:if>
		</c:forEach>
		</ul>
	</div>
	<div class="myBlock">
		<div class="_title">部门：</div>
		<ul>
		<c:forEach var="dept" items="${applicationScope.deptList }">
			<li>
				${dept.deptName }
				<input type="button" value="删除" lang="dept" onclick="del(this,${dept.id})" />
			</li>
		</c:forEach>
		</ul>
	</div>
</div>
</body>
</html>