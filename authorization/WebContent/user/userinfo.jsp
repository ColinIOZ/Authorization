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
<title>userinfo</title>
<link  href="css/style.css" rel="stylesheet" type="text/css"/>
</head>

<body>

<form action="user/completeUserInfo" method="post" name="userinfo">
	<table width="300px" height="200px">
	  <tr>
		<td colspan="2"><br>&nbsp;<font style="font-size: 16px;font-weight: bold;color: gray;"><fmt:message key="baseinfo"/>:</font></td>
	  </tr>
	  <tr>
		<td>&nbsp;</td>
		<td><span style="color: red">${completeError }</span></td>
	  </tr>
	  <tr>
    	<td class="a"><fmt:message key="username"/>：</td>
    	<td><input name="username" type="text"  value="${sessionScope.user.userName }" readonly="readonly"/></td>
  	  </tr>
	  <tr>
    	<td class="a"><fmt:message key="email"/>：</td>
    	<td><input name="email" type="text"  value="${sessionScope.user.email }" readonly="readonly"/></td>
  	  </tr>
	  <tr>
		<td class="a"><fmt:message key="insititute"/>：</td>
		<td><input name="institute" type="text"  value="${sessionScope.user.institute }"/></td>
	  </tr>
	  <tr>
		<td class="a"><fmt:message key="user_home_page"/>：</td>
		<td><input name="homepage" type="text"  value="${sessionScope.user.homepage }"/></td>
	  </tr>
	  <tr>
		<td class="a"><fmt:message key="introduction"/>：</td>
		<td><textarea name="selfIntroduction" cols="15" rows="5">${sessionScope.user.selfIntroduction }</textarea></td>
	  </tr>
	  <tr>
  		<td>&nbsp;</td>
    	<td><input type="submit" value="<fmt:message key="save"/>"/></td>
  	  </tr>
	</table>
</form>
</body>
</html>
</fmt:bundle>