<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加广告</title>
<link href="../../css/public/style.css" rel="stylesheet" type="text/css" />
<link href="../../css/public/css.css" rel="stylesheet" type="text/css" />
<script src="../../js/jquery.js"></script>
<script src="../js/cms.js"></script>
<link href="../../css_3/main.css" rel="stylesheet" type="text/css" />
<link href="../../css_3/media-queries.css" rel="stylesheet" type="text/css" />
<link href="../css/cms.css" rel="stylesheet" type="text/css" />
<script src="../../common/datePicker/WdatePicker.js"></script>
<script>
$(function (){
	limit = {"pc0small":0, "pc0big":100, "app1big":200, "app1small":0, "app2big":20, "app2small":20};
	check = function (){
		var flags = new Array();
	 	var radioIsChecked =  new Array();
	 	var flag = true;
		$("td.radio").each(function (index, el){
			var $el = $(el);
			if(!($el.find('input:checked').length > 0)){
				flags.push(false);
				radioIsChecked.push(false);
				$("span[id='" + $($(el).children()[0]).attr("name") + "']").text("请选择" + $(el).attr("data"));
			} else {
				$("span[id='" + $($(el).children()[0]).attr("name") + "']").text("");
			}
		});
		$("input[type='text']").each(function (index, el){
			var $el = $(this);
			if($.trim($el.val()) == ""){
				flags.push(false);
				$("span[id='" + $(el).attr("name") + "']").text("请填写" + $el.attr("data-name"));
			} else {
				$("span[id='" + $(el).attr("name") + "']").text("");
			}
		});
		$("input.imgValue").each(function (index, el){
			var $el = $(this);
			var $elFile = $(this).prev();
			if($.trim($el.val()) == ""){
				if(radioIsChecked.length == 0){
					var key = "";
					$(".key-item input:checked").each(function (index, el){
						key += $(this).attr("key-item");
					});
					key += $elFile.attr("key-item");
					if(typeof $elFile.get(0).files[0] == "undefined" || $elFile.get(0).files[0] == undefined){
						if(limit[key] != undefined && limit[key] != 0){
							flags.push(false);
							$("span[id='" + $(el).attr("name") + "']").text("请选择一个" + $elFile.attr("data-name") + "文件");
						} else {
							$("span[id='" + $(el).attr("name") + "']").text("");
						}
					} else {
						var _fileSize = $elFile.get(0).files[0].size;
							if(limit[key] != "undefined" || limit[key] != undefined){
								if(Math.ceil(_fileSize / 1024) > limit[key]){
									flags.push(false);
									$("span[id='" + $(el).attr("name") + "']").text($elFile.attr("data-name") + "文件大小超过限制(" + limit[key] + ")");
								} else {
// 									$("span[id='" + $(el).attr("name") + "']").text("");
									
								}
							}
					}
				}
			}
		});
		$.each(flags, function (index, el){
			if(!el){
				flag = el;
				return el;
			}
		});
		if(flag){
			$("#form1").submit();
		}
	}
	
	getPhotoSize = function (obj){
	    photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
	    if(photoExt!='.jpg' && photoExt!='.png' && photoExt!='.gif'){
	        alert("请上传后缀名为jpg、png、gif的图片!");
	        return false;
	    }
// 	    var fileSize = 0;
// 	    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;            
// 	    if (isIE && !obj.files) {          
// 	         var filePath = obj.value;            
// 	         var fileSystem = new ActiveXObject("Scripting.FileSystemObject");   
// 	         var file = fileSystem.GetFile (filePath);               
// 	         fileSize = file.Size;         
// 	    }else {  
// 	         fileSize = obj.files[0].size;     
// 	    } 
// 	    fileSize=Math.round(fileSize/1024*100)/100; //单位为KB
// 	    if(fileSize>=10){
// 	        alert("照片最大尺寸为10KB，请重新上传!");
// 	        return false;
// 	    }
	}
});
</script>
</head>
<body>
<div class="location">
	您当前的位置—添加广告 <span style="color: #F00">${requestScope.message }</span>
</div>
<div class="checked">
	<form id="form1" action="saveOrUpdateAdvice.do" method="post" enctype="multipart/form-data" onsubmit="javascript:return false;">
	<input type="hidden" name="id" value="${advice.id }" />
	<input type="hidden" name="createTime" value="${advice.createTime }" />
	<table>
		<tr>
			<td>终端：</td>
			<td class="radio key-item" data="终端">
				<input key-item="pc" type="radio" name="type" checked="checked" value="0" onclick="showpos(0);" />pc端
				<input key-item="app" type="radio" name="type" <c:if test="${advice.type == 1 }">checked="checked"</c:if> value="1" onclick="showpos(1);"/>移动端
				<span id="advice.type"></span>
			</td>
		</tr>
		<!--
		<tr>
			<td>图片位置：</td>
			<td class="radio key-item" data="图片位置">
				<input key-item="0" type="radio" name="advice.position" checked="checked" value="0" />TOP banner
				<input key-item="1" type="radio" name="advice.position" <c:if test="${advice.position == 1 }">checked="checked"</c:if> value="1" />开屏图片
				<input key-item="2" type="radio" name="advice.position" <c:if test="${advice.position == 2 }">checked="checked"</c:if> value="2" />底部图片
				<span id="advice.position"></span>
			</td>
		</tr>
		--><tr>
			<td>小图：</td>
			<td>
				<input key-item="small" type="file" id="minImage" name="minImage" data-name="小图" onchange="getPhotoSize();" />
				<input class="imgValue" type="hidden" id="img2" name="minImage1" value="${advice.minImage }" />
				<c:if test="${not empty advice.minImage }"><img style="width:200px;" src="../../${advice.minImage }" /></c:if>
				<span id="advice.minImage"></span>(没有可以不上传)
			</td>
		</tr>
		<tr>
			<td>图片：</td>
			<td>
				<input key-item="big" type="file" id="image" name="image" data-name="大图" onchange="getPhotoSize();" />
				<input class="imgValue" type="hidden" name="image1" value="${advice.image }" />
				<span id="advice.image"></span><br/>
				<c:if test="${not empty advice.image }"><img style="width:200px;" src="../../${advice.image }" /></c:if>
			</td>
		</tr>
		<tr>
			<td>开始时间：</td>
			<td>
				<input type="text" id="createTime" name="startTime" data-name="开始时间" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${advice.startTime }" />
				<span id="advice.startTime"></span>
			</td>
		</tr>
		<tr>
			<td>结束时间：</td>
			<td>
				<input type="text" id="createTime" name="endTime" data-name="结束时间" class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${advice.endTime }" />
				<span id="advice.endTime"></span>
			</td>
		</tr>
		<tr>
			<td>推广链接：</td>
			<td>
				<input type="text" id="downloadUrl" name="refferUrl" data-name="推广链接" value="${advice.refferUrl }"  />
				<span id="advice.refferUrl"></span>
			</td>
		</tr>
		<tr>
			<td>是否添加reffer标识：</td>
			<td>
				<input type="radio" name="reffer" checked="checked" value="0"/>是
				<input type="radio" name="reffer" <c:if test="${advice.reffer eq 1 }">checked</c:if> value="1" />否
			</td>
		</tr>
		<tr>
			<td>广告语：</td>
			<td>
				<input type="text" id="title" data-name="广告语" name="advertisement" value="${advice.advertisement }" />
				<span id="advice.advertisement"></span>
			</td>
		</tr>
		<tr>
			<td>展示时间：</td>
			<td>
				<input type="text" id="title" name="showTime" data-name="展示时间" value="${advice.showTime }" />秒
				<span id="advice.showTime"></span>
			</td>
		</tr>
		<tr>
			<td>缓存时间：</td>
			<td>
				<input type="text" id="cooking" name="cooking"  value="${advice.cooking }" />小时
				<span id="cookingTip"></span>
			</td>
		</tr>
		<tr>
			<td>展示位置：</td>
			<td>
				<select name="sectionId" id="position">
<!--					<c:forEach var="section" items="${applicationScope.sectionList }">-->
<!--					 <c:if test="${section.id ne 2}"> -->
<!--						<option value="${section.id }" <c:if test="${advice.sectionId == section.id }">selected="selected"</c:if>>${section.title }</option>-->
<!--					 </c:if>-->
<!--					</c:forEach>-->
				</select>
				<span id="advice.sectionId"></span>
			</td>
		</tr>
		<tr>
			<td>是否有效：</td>
			<td>
				<input type="radio" name="status" checked="checked" value="0"/>是
				<input type="radio" name="status" <c:if test="${advice.status eq 1 }">checked</c:if> value="1" />否
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<button value="提交" class="button" onclick="check();">提交</button><input type="reset" value="清空" class="button" />
			</td>
		</tr>
	</table>
	<input type="hidden" name="id" value="${game.id }" />
	<input type="hidden" id="hot" name="hot" value="1"/>
	
	</form>
</div>
<script>
function showpos(obj){
  if(obj==1){
   $("#position option").remove(); 
   $("#position").append('<option value="3" <c:if test="${advice.sectionId==3 }">selected="selected"</c:if>>开屏图片</option>');  
 	 $("#position").append('<option value="4" <c:if test="${advice.sectionId==4 }">selected="selected"</c:if>>底部图片</option>');  
  }else{
    $("#position option").remove(); 
    $("#position").append('<option value="1" <c:if test="${advice.sectionId==1 }">selected="selected"</c:if>>TOP Banner</option>'); 
  }
}

$(function(){
   showpos(${advice.type});
});
</script>
</body>
</html>