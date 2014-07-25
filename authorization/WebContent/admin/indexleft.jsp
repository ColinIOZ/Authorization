<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:bundle basename="mess">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link  href="css/style.css"  rel="stylesheet" type="text/css"/>
</head>

<body bgcolor="#FFCCCC">
		<table>
		  <tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		  </tr>
		  <tr>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		  </tr>
		  <tr>
			<td>&nbsp;</td>
			<td><a href="admin/manageWebInfoList?num=1" target="mainFrame"><fmt:message key="manage_web"/></a></td>
		  </tr>
		  <tr>
			<td>&nbsp;</td>
			<td><a href="admin/manageWebapiInfoList?num=1" target="mainFrame"><fmt:message key="manage_webapi"/></a></td>
		  </tr>
		  <tr>
			<td>&nbsp;</td>
			<td><a href="admin/authorizedWebList?num=1" target="mainFrame"><fmt:message key="manage_web_authorization"/></a></td>
		  </tr>
		   <tr>
			<td>&nbsp;</td>
			<td><a href="admin/authorizedWebapiList?num=1" target="mainFrame"><fmt:message key="manage_webapi_authorization"/></a></td>
		  </tr>
		  <tr>
			<td>&nbsp;</td>
			<td><a href="admin/webapiStatisticsList?num=1" target="mainFrame"><fmt:message key="webapi_statistics"/></a></td>
		  </tr>
		</table>


</body>
</html>
</fmt:bundle>