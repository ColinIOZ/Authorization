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
<script type="text/javascript">
	function remindWebAuthorisation(webName){
		$.post("remindWebAuthorisation",{webName: webName},function(data){
			if("true" == data)
				alert("<fmt:message key='tip6'/>");
			else
				alert("<fmt:message key='tip7'/>");
		},"text");
	}

</script>
</head>
<body>

<form action="" method="post" name="" >
	<div style="width: 810px;"><br>&nbsp;<font style="font-size: 16px;font-weight: bold;color: gray;"><fmt:message key='web_resource_list'/></font><br><br>
		<span style="color: red">${applyWebAuthorisationTip }</span>
	</div>
	<c:if test="${!empty authorizedWebListByUserId}">
	<table width="810px;" bgcolor="#CCCCCC" align="center" border="0" cellspacing="1" cellpadding="1" class="contTable">
		<tr class="slt_label_style" align="center">
			<th width="100px;"><fmt:message key='sequence_number'/></th>
			<th width="220px;"><fmt:message key='web_name'/></th>
			<th width="190px;"><fmt:message key='operate'/></th>
			<th colspan="2" width="300px;"><fmt:message key='authorization_status'/></th>
		</tr>
		<c:forEach var="temp" items="${authorizedWebListByUserId}" varStatus="status">
			
			<tr  align="center">
				<td>${status.count }</td>
				<td>${temp.web.webName }</td>
				<td>
					<a href="common/showWebInfoByWebId?id=${temp.web.id}" target="_blank"><fmt:message key='look_button'/></a>
				</td>
				<td colspan="2">
					<c:if test="${temp.isauthorized==0}">
						<%--
							<a href="javascript:void(0)" onclick="remindWebAuthorisation('${temp.web.webName }')"><fmt:message key='remind'/></a>
						 --%>
						 <fmt:message key="tip1"></fmt:message>
					</c:if>
					<c:if test="${temp.isauthorized==1}">
						<fmt:message key='tip2'/>
					</c:if>
					<c:if test="${temp.isauthorized==2}">
						<a href="user/applyForWebAuthorisation?flag=applyAgain&webid=${temp.web.id }"><fmt:message key='apply_again'/></a>
						<br>(${temp.remark})
					</c:if>
				</td>	
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<c:if test="${empty authorizedWebListByUserId}"><div align="center" style="width: 810px;"><fmt:message key='resource_empty'/></div></c:if>
	<br>
	 <div align="center" style="width: 810px;">
	 	<jsp:include page="../pagination.jsp">
	 		<jsp:param value="user/showWebResourceListByUser" name="url"/>
	 	</jsp:include>
	 </div>
</form>
<br><br>

</body>
</html>
</fmt:bundle>
