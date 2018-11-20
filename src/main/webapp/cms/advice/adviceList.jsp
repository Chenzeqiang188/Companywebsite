<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>游戏列表</title>
<link href="../../css/public/css.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery.js"></script>
<link href="../../css_3/main.css" rel="stylesheet" type="text/css" />
<link href="../../css_3/media-queries.css" rel="stylesheet" type="text/css" />
<link href="../css/cms.css" rel="stylesheet" type="text/css" />
<script>
function delGame(id){
	if(confirm("确定删除这条广告吗？")){
		$.post("deleteAdviceById.do",{'id':id},function(data){
			if(data==1){
				//alert("删除成功！");
				$("#form1").submit();
			}else if(data==0){
				alert("删除失败！");
			}else{
				window.parent.location.href = "${pageContext.request.contextPath}/login.jsp";
			}
		});
		//window.location.href = "deleteAdviceById.do?id=" + id;
        //window.location.reload();
	}
}

function showpos(obj){
  if(obj==1){
   $("#position option").remove(); 
   $("#position").append('<option value="" >所有</option>');
   $("#position").append('<option value="3" <c:if test="${advice.position==3 }">selected="selected"</c:if>>开屏图片</option>');  
 	 $("#position").append('<option value="4" <c:if test="${advice.position==4 }">selected="selected"</c:if>>底部图片</option>');  
  }else{
    $("#position option").remove(); 
    $("#position").append('<option value="" >所有</option>');
    $("#position").append('<option value="1" <c:if test="${advice.position==1 }">selected="selected"</c:if>>TOP Banner</option>'); 
  }
}

$(function(){
   showpos(${advice.type});
   //location.reload();
});
</script>
</head>
<body>
<div class="location minW">
	您当前的位置—广告列表
</div>
<div class="checked minW">
	<form id="form1" action="adviceList.do" method="post">
	终端：
	<select name="type" onchange="showpos(this.value);">
<!--		<option value="" >所有</option>-->
		<option value="0" <c:if test="${advice.type==0 }">selected="selected"</c:if>>PC端</option>
		<option value="1" <c:if test="${advice.type==1 }">selected="selected"</c:if>>移动端</option>
	</select>
	展示位置：
	<select name="position" id="position">
<!--		<option value="">所有</option>-->
<!--		<option value="1" <c:if test="${advice.position==1 }">selected="selected"</c:if>>TOP Banner</option>-->
<!--		<option value="3" <c:if test="${advice.position==3 }">selected="selected"</c:if>>开屏图片</option>-->
<!--		<option value="4" <c:if test="${advice.position==4 }">selected="selected"</c:if>>底部图片</option>-->
	</select>
	<input id="search" type="submit" value="搜索" class="button" />
	</form>
</div>
<div class="data_box">
	<table>
		<tr>
			<td>终端</td><!--
			<td>小图</td>
			<td>大图</td>
			-->
			<td>广告语</td>
			<td>展示位置</td>
			<td>推广链接</td>
			<td>是否添加reffer标识</td>
			<td>展示时间(秒)</td>
			<td>缓存时间(小时)</td>
			<td>开始时间</td>
			<td>结束时间</td>
			<td>是否有效</td>
			<td>操作</td>
		</tr>
		<c:forEach var="entity" items="${requestScope.advices }" varStatus="state">
			<tr>
				<td>
					<c:if test="${entity.type==0 }">PC端</c:if>
					<c:if test="${entity.type==1 }">移动端</c:if>
				</td>
				<!--<td>
					<%-- <img style="width:50px;" src="../../${entity.minImage }" onerror="this.src='${pageContext.request.contextPath}/images/buttom.png'"/> --%>
					<img style="width:50px;" src="${entity.minImage }" onerror="$(this).remove();"/>
				</td>
				<td>
					<%-- <img style="width:100px;" src="../../${entity.image }" onerror="this.src='${pageContext.request.contextPath}/images/buttom.png'"/> --%>
					<img style="width:100px;" src="${entity.image }" onerror="$(this).remove();"/>
				</td>
				-->
				<td>${entity.advertisement }</td>
				<td>
					<c:if test="${entity.sectionId==1 }">TOP banner</c:if>
					<c:if test="${entity.sectionId==3 }">开屏图片</c:if>
					<c:if test="${entity.sectionId==4 }">底部图片</c:if>
				</td>
				<td>${entity.refferUrl }</td>
				<td>
					<c:if test="${entity.reffer==0 }">是</c:if>
					<c:if test="${entity.reffer==1 }">否</c:if>
				</td>
				<td>${entity.showTime }</td>
				<td>${entity.cooking }</td>
			  <td>${entity.startTime }</td>
				<td>${entity.endTime }</td>
				<td><c:if test="${entity.status==0 }">有效</c:if><c:if test="${entity.status==1 }">无效</c:if></td>
				<td>
					<input type="button" value="修改" class="button" onclick="window.location.href='findAdviceById.do?id=${entity.id}'" />
					<input type="button" value="删除" class="button" onclick="delGame(${entity.id})" />
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>