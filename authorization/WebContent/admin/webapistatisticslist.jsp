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
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link  href="css/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/CommonsUtil.js"></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>

</head>
<body>
<div style="width: 800px;"><br>&nbsp;<font style="font-size: 15px;font-weight: bold;color: gray;"><fmt:message key='webapi_statistics_list'/></font><br></div>
<form action="" method="post" name="" >
	<div class="blank10"></div>
	<table width="800px" bgcolor="#CCCCCC" align="center" border="0" cellspacing="1" cellpadding="1" class="contTable">
		<tr class="slt_label_style" align="center">
			<th width="50px"><fmt:message key="sequence_number"/></th>
			<th width="230px"><fmt:message key="authorized_api_id"/></th>
			<th width="110px"><fmt:message key="api_name"/></th>
			<th width="110px"><fmt:message key="user_name"/></th>
			<th width="100px"><fmt:message key="request_date"/></th>
			<th width="100px"><fmt:message key="request_times"/></th>
			<th width="100px"><fmt:message key="limit_request_times"/></th>
		</tr>
		<c:forEach var="temp" items="${webapiStatisticsList}" varStatus="status">
			
			<tr  align="center">
				<td>${status.count }</td>
				<td>${temp.authorizedWebapi.id }</td>
				<td>${temp.authorizedWebapi.webapi.apiName }</td>
				<td>${temp.authorizedWebapi.user.userName }</td>
				<td><fmt:formatDate value="${temp.requestDate }" pattern="yyyy-MM-dd"/></td>
				<td>${temp.requestTimes }</td>
				<td>${temp.authorizedWebapi.limitRequest }</td>
				
			</tr>
		</c:forEach>

	</table>
	<br>
	 <div align="center" style="width: 800px">
	 	<jsp:include page="../pagination.jsp">
	 		<jsp:param value="admin/webapiStatisticsList" name="url"/>
	 	</jsp:include>
	 </div>
</form>
</body>
</html>
</fmt:bundle>
