<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>谷得cms</title>
<script src="<%=request.getContextPath() %>/javascript/top/Clock.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/javascript/public/jquery.js"></script>
<link href="<%=request.getContextPath() %>/css/top/top-menu.css" rel="stylesheet" type="text/css" />
<link href="<%=request.getContextPath() %>/css_3/main.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	#menu #nav{
		width:600px;
		margin:0px auto;
		margin-right:88px;
		padding-bottom:0px;
		padding-top:1px;
	}
	#menu #nav ul {
		list-style:none;
		margin-top:27px;
		margin-bottom:0px;
	}
	#menu #nav ul li {
		float: right; 
		height:25px; 
		line-height: 2.1em; 
		text-align: center;
	}
	#menu .inactive { 
		background-image:url(<%=request.getContextPath() %>./images/top/nav_bg_inactive2.png) !important;
		margin-left: 8px; 
		margin-right:8px;
		margin-bottom:0px;
	}
	#nav ul li a{
		display:block;
		float:left;
		width:82px; 
		color:#1F385C;
		text-decoration:none;
	}
	#nav ul li a:hover{
		color:#ff0000;
		text-decoration:underline;
	}
	#nav ul li a img {
		border:0px;
		margin:5px;
		margin-right:0px;
		float:left;
	}
	.aa a{float:left; display:block; text-decoration:none; font-weight:bold; width:120px; height:48px; margin-top:3px; border-left:1px solid #1c66a7; border-right:1px solid #094b88; text-align:center}
	.aa a:hover{ background:url(<%=request.getContextPath() %>/images/tops/menubg.png); background-position:0px -1px; height:51px; line-height:54px; cursor:pointer; position:relative; top:-3px;}
</style>
<script type="text/javascript" src="<%=request.getContextPath() %>/javascript/public/jquery.js"></script>
<script type="text/javascript">
		/*$(document).ready(function(){
			$("#areaId").blur(function(){
				var areaId2 = document.getElementById("areaId").value;
				alert(areaId2);
				
				$.post("/area.action",null,function(result){
					alert(result);
				});
			});
		});*/ 
		$(function(){   
            $("#areaId").blur(function(){   
	            var areaId=document.getElementById("areaId").value;
	            var params = {areaId:areaId};
                $.ajax({   
                        url:"area.action",   
                        data:params,   
                        type:'post',   
                        dataType:'json',   
                        success:function(data){   
                        //data += decodeURI(data.shtml);   
                            },   
                        error:  function(){   
                                   
                            }                      
                })   
            });
        });
	function updGame(){
		var status="updGame";
		var areaId=document.getElementById("areaId").value;
        var params = {status:status,areaId:areaId};
            $.ajax({   
                    url:"notify.action",   
                    data:params,   
                    type:'post',   
                    dataType:'json',   
                    success:function(data){   
                    //data += decodeURI(data.shtml); 
                         alert("update OK")  
                        },   
                    error:  function(){   
                        }                      
            });
	}

	function showSeed(){
		var status="setLang";
		var manage = document.getElementById("manage").value;
		var params = {status:status,id:manage};
        $.ajax({   
                url:"systemSet.action",   
                data:params,   
                type:'post',   
                dataType:'json',   
                success:function(data){   
                //data += decodeURI(data.shtml); 
                	alert(data.result);
                    },   
                error:  function(){   
                    }                      
        });
	}
</script>
</head>

<!--<body style="background-image:url(./images/top/bg.gif); margin: 0px; background-repeat: repeat-x">-->
<body style="margin: 0px; background-repeat: repeat-x">

<div class="top_body">
	<div class="top_top">
        <div class="top_title">CMS管理平台</div>
        <div id="datetime"></div>
        <div class="crear"></div>
	</div>
    <div class="top_bottom">
        <div class="logo"></div>
        <div class="top_bottom_menu_box">
            <a href="####" title="网站首页"><div class="top_bottom_menu_left1"></div></a>
            <div class="top_bottom_menu_left2">
            <strong style="color:#e4f9ff;">
                			欢迎您:<span style="margin:0px 5px;color:#e4f9ff;"><c:choose><c:when test="${sessionScope.USERNAME!=null}" >
                				${sessionScope.USERNAME}
                			</c:when>
								<c:otherwise>
									<span>null</span>
								</c:otherwise>
							</c:choose></span> 
                			上次登陆时间:<span style="margin:0px 5px;color:#e4f9ff;"><c:choose><c:when test="${sessionScope.LOGINDATE!=null}" >
                				<fmt:formatDate value="${sessionScope.LOGINDATE}" pattern="yyyy年MM月dd日HH点mm分" />
                			</c:when>
								<c:otherwise>
									<span style="color:#e4f9ff;">null</span>
								</c:otherwise>
							</c:choose></span>
                			</strong>
            </div>
            <div class="top_bottom_menu_right"></div>
            <div id="menu">
                <a href="#" target="main">修改密码</a>
                <a href="/logout.do">退出登录</a>
            </div>
   		</div>
    </div>
</div>







</body>
</html>