<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="org.jasig.cas.client.authentication.AttributePrincipal"%>
<%@ page import="org.jasig.cas.client.validation.Assertion" %>
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

<body bgcolor=""> 
<fmt:bundle basename="mess">
	<div style="height: 100px;background-color: #CCCCCC">
		<br /><br /><br /><br />
		<div align="right">
			
			<%
				//AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
				Assertion assertion = (Assertion)request.getSession().getAttribute("_const_cas_assertion_");
				
				if(assertion == null){
			%>
					<a href="http://localhost:8080/authorization/protect/iflogin.jsp" target="_parent"><fmt:message key="login"></fmt:message></a>
			<%	}else{
					//String username = principal.getName();
					String username = assertion.getPrincipal().getName();
			%>
					<fmt:message key="suctip">
						<fmt:param><font color="red"><%=username %></font></fmt:param>
					</fmt:message>	
			<%  }
			%>
			<!-- 
			<c:choose>
				<c:when test="${empty sessionScope.user }">
					<a href="https://castest:8443/casweb/login" target="_parent"><fmt:message key="login"></fmt:message></a>
				</c:when>
				<c:otherwise>
					<fmt:message key="suctip">
						<fmt:param><font color="red">${sessionScope.user.userName }</font></fmt:param>
					</fmt:message>	
				</c:otherwise>
			</c:choose> 
			
			common/loginOut?flag=user
			${pageContext.request.contextPath}/logout.jsp
			-->
			
			|<a href="https://castest:8443/casweb/logout?service=https://castest:8443/casweb/login?service=http://localhost:8080/authorization/protect/iflogin.jsp" target="_parent"><fmt:message key="login_out"></fmt:message></a>&nbsp;&nbsp;
		</div>
	</div>
</fmt:bundle>		
</body>
</html>