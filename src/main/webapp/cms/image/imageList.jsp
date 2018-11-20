<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图片列表</title>
<link href="../../css/public/css.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery.js"></script>
<link href="../../css_3/main.css" rel="stylesheet" type="text/css" />
<link href="../../css_3/media-queries.css" rel="stylesheet" type="text/css" />
<link href="../css/cms.css" rel="stylesheet" type="text/css" />
<script>
function delImage(id){
	if(confirm("确定删除这张图片？")){
		$.post("deleteImageById.do",{'id':id},function(data){
			if(data==1){
				$("#form1").submit();
			}else if(data==0){
				alert("删除失败！");
			}else{
				window.location.href = "../../login.jsp";
			}
		});
	}
}
</script>
</head>
<body>
<div class="location minW">
	您当前的位置—图片列表
</div>
<div class="checked minW">
	<form id="form1" action="imageList.do" method="post">
	标题：<input type="text" name="title" value="${image.title }" />
	图片类型：
	<select name="imgType">
		<option value="" <c:if test="${empty image.imgType }">selected="selected"</c:if>>所有类型</option>
		<option value="bar" <c:if test="${image.imgType eq 'bar' }">selected="selected"</c:if>>底部通栏</option>
		<option value="TD" <c:if test="${image.imgType eq 'TD' }">selected="selected"</c:if>>培训发展<!-- (training and development) --></option>
		<%-- <option value="MT" <c:if test="${image.imgType eq 'MT' }">selected="selected"</c:if>>管理团队<!-- (management team) --></option> --%>
		<option value="CB" <c:if test="${image.imgType eq 'CB' }">selected="selected"</c:if>>薪资福利<!-- (Compensation Benefit) --></option>
		<option value="CI" <c:if test="${image.imgType eq 'CI' }">selected="selected"</c:if>>公司简介<!-- (company introduce) --></option>
		<option value="EV" <c:if test="${image.imgType eq 'EV' }">selected="selected"</c:if>>企业文化<!-- (entrepreneurial vision) --></option>
		<option value="EL" <c:if test="${image.imgType eq 'EL' }">selected="selected"</c:if>>谷得生活<!-- (employee life) --></option>
		<option value="WC" <c:if test="${image.imgType eq 'WC' }">selected="selected"</c:if>>工作环境<!-- (working condition) --></option>
	</select>
	状态：
	<select name="state">
		<%-- <option value="-1" <c:if test="${image.state==-1 }">selected="selected"</c:if>>所有</option> --%>
		<option value="1" <c:if test="${image.state==1 }">selected="selected"</c:if>>有效</option>
		<option value="0" <c:if test="${image.state==0 }">selected="selected"</c:if>>无效</option>
	</select>
	<input type="submit" value="搜索" class="button" />
	</form>
</div>
<div class="data_box">
	<table>
		<tr>
			<td>编号</td>
			<td>标题</td>
			<td>英文标题</td>
			<td>类型</td>
			<td>序号</td>
			<td>图片</td>
			<td>状态</td>
			<td>操作</td>
		</tr>
		<c:forEach var="image" items="${requestScope.imageList }" varStatus="state">
			<tr>
				<td>${state.count }</td>
				<td>${image.title }</td>
				<td>${image.englishTitle }</td>
				<td>
					<c:if test="${image.imgType eq 'bar' }">底部通栏</c:if>
					<c:if test="${image.imgType eq 'TD' }">培训发展</c:if>
					<c:if test="${image.imgType eq 'MT' }">管理团队</c:if>
					<c:if test="${image.imgType eq 'CB' }">薪资福利</c:if>
					<c:if test="${image.imgType eq 'CI' }">公司简介</c:if>
					<c:if test="${image.imgType eq 'EV' }">企业文化</c:if>
					<c:if test="${image.imgType eq 'EL' }">谷得生活</c:if>
					<c:if test="${image.imgType eq 'WC' }">工作环境</c:if>
				</td>
				<td>${image.sorting }</td>
				<td><img style="width:100px;" src="../../${image.img }" /></td>
				<td>
					<c:if test="${image.state==1 }">有效(显示)</c:if>
					<c:if test="${image.state==0 }">无效(隐藏)</c:if>
				</td>
				<td>
					<input type="button" value="修改" class="button" onclick="window.location.href='findImageById.do?id=${image.id}'" />
					<input type="button" value="删除" class="button" onclick="delImage(${image.id})" />
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
</body>
</html>