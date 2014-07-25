<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.jasig.cas.client.authentication.AttributePrincipal"%>
<%@ page import="org.jasig.cas.client.validation.Assertion" %>
<%@ page import="java.util.*"%>
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
<title></title>
<link  href="css/style.css"  rel="stylesheet" type="text/css"/>
</head>

<body bgcolor="#CCCCCC"> 

<fmt:bundle basename="mess">
	<div style="height: 100px;background-color: #CCCCCC">
		<br /><br /><br /><br />
		<div align="right">
			<fmt:message key="suctip"><!-- ${sessionScope.admin.userName } -->
				<fmt:param><font color="red">
				<%
					AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
					String username = principal.getName();
				%>
				<%=username %>
				</font></fmt:param>
			</fmt:message>	
			<!-- common/loginOut?flag=admin 
			http://localhost:8080/casweb/logout?service=https://castest:8443/casweb/login
			-->
			|<a href="https://castest:8443/casweb/logout?service=https://castest:8443/casweb/login?service=http://localhost:8080/authorization/protect/iflogin.jsp" target="_parent"><fmt:message key="login_out"></fmt:message></a>&nbsp;&nbsp;
		</div>
	</div>
</fmt:bundle>
		
</body>
</html>