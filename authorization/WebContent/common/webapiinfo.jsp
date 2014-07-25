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
<title>webapiinfo</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link  href="css/style.css"  rel="stylesheet" type="text/css"/>
<link  href="css/base.css"  rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/CommonsUtil.js"></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>

</head>
<body>
	<table width="90%" height="100px;" bgcolor="#CCCCCC" align="center" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan="2"><jsp:include page="../common/top.jsp"></jsp:include></td>
		</tr>
	</table>
	<form name="" action="" method="post">
			<table width="90%" height="260px;" bgcolor="#CCCCCC" align="center" border="0" cellspacing="1" cellpadding="1" class="contTable">
				<tr>
					<th colspan="2" align="left">
						<font style="font-size: 16px;"><fmt:message key="from_page"/>--></font>
						<font style="font-size: 12px;color: red;"><fmt:message key="webapiinfo_details"/></font>
					</th>
				</tr>
					
				<tr>
					<td class="b" width="150px;"><fmt:message key="api_name"/>：</td>
					<td>${webapiInfo.apiName }</td>
				</tr>
				<tr>
					<td class="b" width="150px;"><fmt:message key="api_url"/>：</td>
					<td>${webapiInfo.apiUrl }</td>
				</tr>
				<tr>
					<td class="b" width="150px;"><fmt:message key="api_usage"/>：</td>
					<td>${webapiInfo.apiUsage }</td>
				</tr>

			</table>
			<br>
	</form>
	<table width="90%" height="100px;" bgcolor="#CCCCCC" align="center" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan="2"><jsp:include page="../common/bottom.jsp"></jsp:include></td>
		</tr>
	</table>
	
</body>
</html>
</fmt:bundle>
