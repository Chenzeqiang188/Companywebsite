<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<%@page import="java.util.List" %>
<%
	String username = (String)request.getSession().getAttribute("USERNAME");
	if(username==null){
		response.sendRedirect("/login.jsp");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>谷得cms</title>
</head>
<frameset rows="170,*" cols="*" frameborder="no" border="1" framespacing="0">
  <frame src="${pageContext.request.contextPath }/top.jsp" name="top" scrolling="no" noresize="noresize" id="top" title="top" style="border-bottom:10px solid #cfcfcf;"/>
  <frameset cols="250,*" frameborder="no" border="1" framespacing="0">
    <frame src="${pageContext.request.contextPath }/menu.jsp" name="left" noresize="noresize" id="left" title="left" style="border-right:10px solid #cfcfcf;" />
    <frame src="${pageContext.request.contextPath }/main.jsp" name="main" id="main" title="main" />
  </frameset>
</frameset>
<noframes><body>
</body></noframes>
</html>
