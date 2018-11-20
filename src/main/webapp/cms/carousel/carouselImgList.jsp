<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.good.web.domain.ActivityGame" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>板块列表</title>
<link href="../../css/public/css.css" rel="stylesheet" type="text/css" />
<script src="../../javascript/public/jquery.js"></script>
<link href="../../css_3/main.css" rel="stylesheet" type="text/css" />
<link href="../../css_3/media-queries.css" rel="stylesheet" type="text/css" />
<link href="../css/cms.css" rel="stylesheet" type="text/css" />
<script src="../../common/datePicker/WdatePicker.js"></script>
<script>
    function delCarouseImg(id) {
        if(confirm("确定删除这条轮播图")){
            $.post("delCarouseImg.do",{'id':id},function (data) {
                if(data==1)
                {
                    $("#form1").submit();
                }else if(data==0){
                    alert("删除失败！");
                }else{
                    window.location.href = "../../login.jsp";
                }
            })
        }
    }
    //window.location.href='delCarouseImg.do?id=${img.id}'
</script>

</head>
<body >
<div class="location minW">
	您当前的位置—轮播图列表
</div>
<div class="checked minW">
	<form id="form1"  method="post" action="carouselImgList.do" >
       日期：<input type="text" id="startTime" name="startTime" class="Wdate"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${carouselImg.startTime }" />-
				<input type="text" id="endTime" name="endTime" class="Wdate"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${carouselImg.endTime }" />
	<input type="submit" value="搜索"  class="button" />
	
	</form>
</div>
<div class="data_box">
	<table>
		<tr>
			<td>图片</td>
			<td>推广链接</td>
			<td>开始时间</td>
			<td>结束时间</td>
			<td>是否添加reffer标识</td>
			<td>是否有效</td>
			<td>操作</td>
		</tr>
	
		<c:forEach var="img" items="${carouselImglist }" varStatus="state">
			<tr>
				<td><c:if test="${not empty img.image }"><img style="width:100px;height:20px;" src="../../${img.image }" /></c:if></td>
				<td>${img.url }</td>
				<td>${img.startTime }</td>
				<td>${img.endTime }</td>
				<td><c:if test="${img.reffer==0 }">是</c:if>
					<c:if test="${img.reffer==1 }">否</c:if></td>
					<td><c:if test="${img.status==0 }">有效</c:if><c:if test="${img.status==1 }">无效</c:if></td>
				<td>
					<input type="button" value="修改" class="button" onclick="window.location.href='editCarouselImgPage.do?id=${img.id}'" />
					<input type="button" value="删除" class="button" onclick="delCarouseImg(${img.id})" />
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>