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
<link  href="css/style.css"  rel="stylesheet" type="text/css"/>
<link  href="css/base.css"  rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/CommonsUtil.js"></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">

	function applyForWebAuthorisation(id, webName){
		$.post("user/applyForWebAuthorisation", {webid: id, webName: webName},function(data){
			if("true" == data){
				alert("<fmt:message key='send_apply_already'/>");
				var a = $("<a/>");
				a.click(function(){
					window.location.href = "common/webInfoList";
				});
				a.click();
			}
		},"text");
	}
	function queryWebInfoListByWebName(){
		var flag = true;
		if("" == $("#webName").val()){
			alert("<fmt:message key='tip5'/>");
			flag = false;
		}
		if(flag)
			$("form[name='queryweb']").submit();
		return flag;	
	}
</script>
</head>
<body>

<form name="queryweb" method="post" action="common/queryWebInfoListByWebName?num=1">
	<table width="810px;" border="0" cellpadding="0" cellspacing="0" align="center">
		<tr>
			<td><br><font style="font-size: 16px;font-weight: bold;color: gray;"><fmt:message key="web_resource_list"/></font><br><br></td>
		</tr>
		<tr>
			<td>
				<table width="810px;" bgcolor="#CCCCCC" border="0" cellspacing="1" cellpadding="1" class="contTable">
					
					<tr>
						<td align="center">
							<fmt:message key="find_by_webname"/>：
							<input id="webName" type="text" name="webName" style="width: 150px;line-height: 15px;height: 15px;">
						    <input type="button" onclick='queryWebInfoListByWebName()' class="txtInp" style="width: 60px;" value="<fmt:message key="look_up_button"/>" />&nbsp;&nbsp; 
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>
<br>
<form name="" action="" method="post">
	<table width="810px;" bgcolor="#CCCCCC" align="center" border="0" cellspacing="1" cellpadding="1" class="contTable">
		<tr class="slt_label_style" align="center">
			<th width="100px;"><fmt:message key="sequence_number"/></th>
			<th width="160px;"><fmt:message key="web_name"/></th>
			<th width="180px;"><fmt:message key="web_host"/></th>
			<th width="190px;"><fmt:message key="logo_url"/></th>
			<th width="180px;"></th>
		</tr>
		<c:forEach var="temp" items="${webinfoList}" varStatus="status">
			
			<tr  align="center">
				<td>${status.count }</td>
				<td>${temp.webName }</td>
				<td>${temp.webHost }</td>
				<td>${temp.logoUrl }</td>
					<td>
					<a href="common/showWebInfoByWebId?id=${temp.id}" target="_blank" ><fmt:message key="look_button"/></a>
					<c:if test="${!empty user}"> | 
						<c:set var="flag1" value="false"></c:set>
						<c:set var="flag2" value="-1"></c:set>
						<c:forEach var="temp1" items="${authorizedWebList}">
							<c:if test="${temp.id == temp1.web.id}">
								<c:set var="flag1" value="true"></c:set>
								<c:if test="${temp1.isauthorized == 0}">
									<c:set var="flag2" value="0"></c:set>
								</c:if>
								<c:if test="${temp1.isauthorized == 1}">
									<c:set var="flag2" value="1"></c:set>
								</c:if>
								<c:if test="${temp1.isauthorized == 2}">
									<c:set var="flag2" value="2"></c:set>
								</c:if>
							</c:if>
						</c:forEach>
						<c:if test="${flag1 != true }">
							<a href="javascript:void(0)" onclick="applyForWebAuthorisation('${temp.id}', '${temp.webName }')"><fmt:message key='apply'/></a>
						</c:if>
						<c:if test="${flag1 == true && flag2 == '0' }">
						    <fmt:message key='tip1'/>
						</c:if>
						<c:if test="${flag1 == true && flag2 == '1' }">
							<fmt:message key='tip2'/>
						</c:if>
						<c:if test="${flag1 == true && flag2 == '2' }">
							<fmt:message key='tip3'/>
						</c:if>
					</c:if>
				</td>	
			</tr>
		</c:forEach>
	</table>
	<br>
	 <div align="center" style="width: 810px;">
	 	<jsp:include page="../pagination.jsp">
	 		<jsp:param value="common/queryWebInfoListByWebName" name="url"/>
	 	</jsp:include>
	 </div>
</form>
</body>
</html>
</fmt:bundle>
