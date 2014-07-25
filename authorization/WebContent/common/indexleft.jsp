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
<link  href="css/base.css"  rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/CommonsUtil.js"></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>

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
			<td><a href="common/webInfoList?num=1" target="mainFrame"><fmt:message key="web_resource"/></a></td>
		  </tr>
		  <tr>
			<td>&nbsp;</td>
			<td><a href="common/webapiInfoList?num=1" target="mainFrame"><fmt:message key="webapi_resource"/></a></td>
		  </tr>
		  <tr>
			<td>&nbsp;</td>
			<td><a href="user/userinfoframeset.jsp" target="_top" ><fmt:message key="manage_userinfo"/></a></td>
		  </tr>
		</table>


</body>
</html>
</fmt:bundle>