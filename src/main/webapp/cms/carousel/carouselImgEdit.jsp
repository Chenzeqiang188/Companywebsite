<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增轮播图片</title>
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
<script src="../../common/datePicker/WdatePicker.js"></script>
<script>
function check(){
	var flag = true;
//	flag = isNull("url","请输入推广链接！") && flag;
	flag = isNull("startTime","请输入开始时间！") && flag;
	flag = isNull("endTime","请输入结束时间！") && flag;
	var img2 = $("#img").val();
	if(img2==""){
		flag = isNull("image","请上传图片！") && flag;
	}
	
	return flag;
}
</script>
</head>
<body>
<div class="location">
	您当前的位置—编辑榜单 <span style="color: #F00">${requestScope.message }</span>
</div>
<div class="checked">
	<form action="addCarouselImg.do"  method="post" enctype="multipart/form-data" onsubmit="return check();" >
	<table>
	  <tr>
			<td>图片：</td>
			<td>
				<input type="file" id="image" name="image" onchange="filefujianChange(this);"/><font color="blue">(高*宽:360*640 格式:JPG/PNG 小于300Kb)</font>
				<input type="hidden" id="img" name="image1" value="${carouselImg.image }" />
				<span id="imageTip"></span><br/>
				<c:if test="${not empty carouselImg.image }"><img style="width:200px;" src="../../${carouselImg.image }" /></c:if>
				
			</td>
		</tr>
		<tr>
			<td>推广链接：</td>
			<td>
				<input type="text" id="url" name="url" value="${carouselImg.url }" />
				<span id="urlTip"></span>
			</td>
		</tr>
		<tr>
			<td>开始时间：</td>
			<td>
				<input type="text" id="startTime" name="startTime" class="Wdate"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${carouselImg.startTime }" />
				<span id="startTimeTip"></span>
			</td>
		</tr>
		<tr>
			<td>结束时间：</td>
			<td>
				<input type="text" id="endTime" name="endTime" class="Wdate"
				onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${carouselImg.endTime }" />
				<span id="endTimeTip"></span>
			</td>
		</tr>
		<tr>
			<td>是否添加reffer标识：</td>
			<td>
				<input type="radio" name="reffer" checked="checked" value="0"/>是
				<input type="radio" name="reffer" <c:if test="${carouselImg.reffer eq 1 }">checked</c:if> value="1" />否
			</td>
		</tr>
		<tr>
			<td>是否有效：</td>
			<td>
				<input type="radio" name="status" checked="checked" value="0"/>是
				<input type="radio" name="status" <c:if test="${carouselImg.status eq 1 }">checked</c:if> value="1" />否
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="提交" class="button" "/>
				<input type="reset" value="清空" class="button" />
			</td>
		</tr>
	</table>
	<input type="hidden" name="id" value="${carouselImg.id }" />
	<input type="hidden" name="createTime" value="${carouselImg.createTime }" />
	</form>
</div>
<script>
var isIE = /msie/i.test(navigator.userAgent) && !window.opera; 
 function filefujianChange(target) {
       var fileSize = 0;         
       if (isIE && !target.files) {     
         var filePath = target.value;     
         var fileSystem = new ActiveXObject("Scripting.FileSystemObject");        
         var file = fileSystem.GetFile (filePath);     
         fileSize = file.Size;    
       } else {    
        fileSize = target.files[0].size;     
        }   
        var size = fileSize / 1024;    
        if(size>300){  
         $("#imageTip").text("附件不能大于30kb");
         target.value="";
         return
        }else{
          $("#imageTip").text("");
        }
        var name=target.value;
        var fileName = name.substring(name.lastIndexOf(".")+1).toLowerCase();
        if(fileName !="jpg" && fileName !="png" ){
          alert("请选择图片格式文件上传(jpg,png)！");
            target.value="";
            return
        }
      }
 </script>
</body>
</html>