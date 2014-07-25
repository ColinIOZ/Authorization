<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页/index</title>
</head>
<frameset cols="*,1024,*" frameborder="no" border="0" framespacing="0">
	<frame src="about:blank"></frame> 
	<frameset rows="100,*,100" cols="*" frameborder="no" border="0" framespacing="0">
	  <frame src="common/top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" />
	  <frameset cols="200,*" frameborder="no" border="0" framespacing="0">
		<frame src="common/indexleft.jsp" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" />
		<frame src="common/indexmain.jsp" name="mainFrame" id="mainFrame"  scrolling="auto"/>
	  </frameset>
	  <frame src="common/bottom.jsp" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" />
	</frameset>
	<frame src="about:blank"></frame> 
</frameset>
<noframes><body>
</body>
</noframes></html>