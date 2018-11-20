<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增游戏排名</title>
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
function checks(){
	var flag = true;
	//flag = isNull("gameRanking","请输入游戏排名！") && flag;
	flag = isNull("gameName","请输入游戏名称！") && flag;
	flag = isNull("gameType","请输入游戏类型！") && flag;
	flag = isNull("url","请输入推广链接！") && flag;
	var img2 = $("#img").val();
	if(img2==""){
		flag = isNull("image","请上传图片！") && flag;
	}
	var url =  $('input:radio[name="rankGame.linkPosition"]:checked').val();
	if(url==1){
	      $(".linkPositionTip").text("");
	  var urls =  $(".urls2").val() ;
	     if(urls==''){
	       $(".linkPosition2Tip").text("请填写路径");
	       flag = false;
	     }
	}
	
	return flag;
}

</script>
</head>
<body>
<div class="location">
	您当前的位置—编辑游戏排名<span style="color: #F00">${requestScope.message }</span>
</div>
<div class="checked">
	<form action="addRankGame.do"  method="post" enctype="multipart/form-data" onsubmit="return checks();" >
	<table>
	  <tr>
			<td>所属榜单：</td>
			<td>
			  <select id="rankid" name="rankId" onchange="changeLink();">
				  <c:forEach items="${ranksList}" var="rank">
			      <c:if test="${rank.type eq 1 }">
				      <option value="${rank.id }" <c:if test="${rank.id eq rankGame.rankId}">selected</c:if>>${rank.title }</option>
			      </c:if>
			    </c:forEach>
			  </select>
			</td>
		</tr>
	  <tr>
			<td>游戏排名：</td>
			<td>
				<input type="text" id="gameRanking" name="gameRanking" value="${rankGame.gameRanking }" />
				<span id="gameRankingTip"></span>
			</td>
		</tr>
		<tr>
			<td>游戏名称：</td>
			<td>
				<input type="text" id="gameName" name="gameName" value="${rankGame.gameName }" />
				<span id="gameNameTip"></span>
			</td>
		</tr>
		<tr>
			<td>图片：</td>
			<td>
				<input type="file" id="image" name="image"  /><font color="blue"></font>
				<input type="hidden" id="img" name="image1" value="${rankGame.image }" />
				<span id="imageTip"></span><br/>
				<c:if test="${not empty rankGame.image }"><img style="width:200px;" src="../../${rankGame.image }" /></c:if>
				
			</td>
		</tr>
		<tr>
			<td>游戏类型：</td>
			<td>
				<input type="text" id="gameType" name="gameType" value="${rankGame.gameType }" />
				<span id="gameTypeTip"></span>
			</td>
		</tr>
		<tr>
			<td>是否添加reffer标识：</td>
			<td>
				<input type="radio" name="reffer" checked="checked" value="0"/>是
				<input type="radio" name="reffer" <c:if test="${rankGame.reffer eq 1 }">checked</c:if> value="1" />否
			</td>
		</tr>
		<tr>
			<td>推广链接：</td>
			<td>
				<input type="text" id="url" name="url" value="${rankGame.url }"/>
				<span id="urlTip"></span>
			</td>
		</tr>
		<tr>
			<td>下载/详情：</td>
			<td>
				<div class="linkPosition2">
					<input  type="hidden" name=linkPosition value="0" />
					<input  class="urls"  type="text"  name="downloadUrl" value="${rankGame.downloadUrl }" placeholder="详情链接/下载地址"/>
					<span class="linkPositionTip"></span><br/>
				</div>
				
			</td>
		</tr>
   <tr>
			<td>描述：</td>
			<td>
			  <textarea rows="5" cols="50" id="info" name="info" maxlength="24" >${rankGame.info }</textarea>
				<span id="infoTip"></span>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="提交" class="button" /><input type="reset" value="清空" class="button" />
			</td>
		</tr>
	</table>
	<input type="hidden" name="id" value="${rankGame.id }" />
	<input type="hidden" name="type" value="1" />
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
        if(size>30){  
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