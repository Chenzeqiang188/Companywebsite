<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="truess">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>
			谷得cms
		</title>
<link href="<%=request.getContextPath() %>/css/index/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function reloadImage(url)
 	{  
 		document.getElementById("img1").src = url+Math.random();//增加 t 参数时解决IE8不刷新  
 	}
</script>
<script type="text/javascript" src="/javascript/public/jquery.js"></script>
<script type="text/javascript" src="/plug/auto/jquery.autocomplete.js"></script>
<script type="text/javascript" src="/javascript/public/public.js"></script>
<script type="text/javascript" src="/javascript/public.js"></script>
<script type="text/javascript" src="/javascript/public/jquery.validate.js"></script>
<script type="text/javascript" src="/javascript/public/getElementById.js"></script><!-- 设置id值、获取id值、获取id -->
<script type="text/javascript">
	$("document").ready(function(){
		$("#myform input[type='text']").addClass("input_style1");
		$("#myform input[type='text']").focus(function(){
			$(this).removeClass("input_style2");
			$(this).addClass("input_style1");
		});
		$("#myform input[type='password']").addClass("input_style1");
		$("#myform input[type='password']").focus(function(){
			$(this).removeClass("input_style2");
			$(this).addClass("input_style1");
		});
		$("#myform").validate({
			debug:false,
			errorClass:"error_style1",
			focusCleanup:true,
			highlight:function(element){
				$(element).removeClass("input_style1");
				$(element).addClass("input_style2");
			},
			rules:{
				loginname:{required:true},
				password:{required:true},
				accode:{required:true}
			},
			messages:{
				loginname:{required:$getid("login2")},
				password:{required:$getid("login3")},
				accode:{required:""}
			}
		});// 参数名:参数值,参数名:参数值
	});
</script>
<script type="text/javascript">
	function showDate(){//焦点定位到账号名称
		$("#loginname").focus();
	}
</script>
	</head>
	<body onload="showDate()">
		<input type="hidden" id="login2" value="请输入用户名" />
		<input type="hidden" id="login3" value="请输入密码" />
		<div id="login">
			<div id="top">
				<div id="top_left">
					<img src="<%=request.getContextPath() %>/images/login/login_03.gif" />
				</div>
				<div id="top_center" style=""></div>
			</div>

			<div id="center">
				<div id="center_left" style="background: url('<%=request.getContextPath() %>/images/login/login_09.gif')"></div>
				<div id="center_middle">
					<form action="login.do" method="post" name="myform" id="myform">
						<div id="user">
							用 户
							<c:choose>
								<c:when test="${sessionScope.LOGINNAME!=null}">
								<input type="text" name="username" id="loginname" value="${sessionScope.LOGINNAME}"/>
								</c:when>
								<c:otherwise>
								<input type="text" name="username" id="loginname" value=""/>
								</c:otherwise>
							</c:choose>
							<span style="margin-left:5px; color:#ff0000"></span>
						</div>
						<div id="password">
							密 码
							<input type="password" name="password" value=""/><span style="margin-left:5px; color:#ff0000"></span>
						</div>
						<div class="bo">
		                	验 证
		                    <input type="text" class="text1" style="width:50px;" name="accode" size="4" id="ccode"/>
		                    <img src="<%=request.getContextPath()%>/image.jsp" id="img1" style="width:55px;height: 18px;" align="middle"/>
		                </div>
						<div id="warn">
							<span style="margin:4px 0px 0px 5px; color:#ff0000"><s:text name="message" /></span>
							<a href="javascript:reloadImage('image.jsp?t=');" style="margin:4px 0px 0px 3px; color:#0066CC">换一张试试</a>
						</div>
						<div id="btn">
							<input type="submit" value="登录" style="cursor:pointer" />
							<input type="reset" value="清空" style="cursor:pointer" />
						</div>
						<input type="hidden" name="status" value="alogin"/>
					</form>
				</div>
				<div id="center_right"></div>
			</div>
			<div id="down">
				<div id="down_left">
					<div id="inf">
						<span class="inf_text">版本信息</span>
						<span class="copyright">谷得统计平台 2013 v1.0</span>
					</div>
				</div>
				<div id="down_center"></div>
			</div>
		</div>
	</body>
</html>