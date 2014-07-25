<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.jasig.cas.client.authentication.AttributePrincipal"%>
<%@ page import="org.jasig.cas.client.validation.Assertion"%>
<%@ page import="java.util.*"%>
<%@ page import="com.big.authorization.util.DBConnUtil"%>
<%@ page import="com.big.authorization.dao.jdbcdao.JdbcBaseDao"%>
<%@ page import="com.big.authorization.po.User"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body>
<%-- <c:redirect url="common/index.jsp"></c:redirect> --%>

<%
	
	AttributePrincipal principal = (AttributePrincipal) request
			.getUserPrincipal();
	if (principal != null) {
		String username = principal.getName();
		int indexmark = username.indexOf("@");
		User user = null;
		if(indexmark == -1)
			user = JdbcBaseDao.getUserByName(username);
		else
			user = JdbcBaseDao.getUserByEmail(username);
		//HttpSession hs = request.getSession();
		int role = user.getRole();
		if (role == 1) {
			//hs.setAttribute("admin",user.getUserName());
%>
<c:redirect url="../admin/index.jsp"></c:redirect>
<%
		} else if (role == 0) {
			//hs.setAttribute("user",user.getUserName());
%>
<c:redirect url="../common/index.jsp"></c:redirect>
<%
		} else {
%>
页面出差错了...
<%
		}
	} else {
%>
页面出差错了
<%
	}
%>

<br>
</body>
</html>
