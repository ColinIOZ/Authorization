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
/*	function showWebapiById(id){
		$.post("judgeIsAuthorizedByWebapiId", {webapiId:id},function(data){
			if(data == "false"){
				if(confirm("<fmt:message key='tip1'/>")){
					$.post("applyForWebapiAuthorisation", {webapiId: id},function(data){
						if("true" == data)
							alert("<fmt:message key='tip2'/>");
					},"text");
				}
			}else if(data == "true0"){
				alert("<fmt:message key='tip3'/>");
			}else if(data == "true1"){
				$.post("countWebapiRequestTimes", {webapiId:id},function(data){
					if("limit" == data)
						alert("您已经超过了今天最大的访问次数");
					else
						window.open("showWebapiByWebapiId?id=" + id, "_blank");
				},"text");
				
			}else if(data == "true2"){
				alert("<fmt:message key='tip4'/>");
			}
		},"text");
	}*/
	function applyForWebapiAuthorisation(id, apiName){
		$("#apply").append("<form method='post' action='user/applyForWebapiAuthorisation' name='applyForm' >"
				+ "<fmt:message key='host_url_input'/>：<input id='hostUrl' type='text' name='hostUrl' style='width: 150px;line-height: 15px;height: 15px;'>"
				+ "<input id='webapiId' type='hidden' name='webapiId' value='"+id+"'>"
				+ "<input id='webapiName' type='hidden' name='webapiName' value='"+apiName+"'>"
				+ "<input type='button' value='<fmt:message key='submit_button'/>' style='width: 60px;' onclick='checkApplyFor()'>"//"+id+","+$("#hostUrl").val()+"
				+ "&nbsp;<span id='hostUrltip' style='color:red;'></span></form>");
	}
	function checkApplyFor(){
		if(trim($("#hostUrl").val()) != ""){
			$.post("user/applyForWebapiAuthorisation", {
					webapiId : $('#webapiId').val(),
					apiName : $('#webapiName').val(),
					hostUrl : trim($("#hostUrl").val())
			}, function(data) {
				if ("true" == data) {
					alert("<fmt:message key='send_apply_already'/>");
					$("#apply").css("display", "none");
					var a = $("<a/>");
					a.click(function() {
						window.location.href = "common/webapiInfoList";
					});
					a.click();
				}

			}, "text");
		} else {
			$("#hostUrltip").html("<fmt:message key='null_tip'/>");
		}

	}
	function queryWebapiListByapiName() {
		var flag = true;
		if ("" == $("#apiName").val()) {
			alert("<fmt:message key='tip5'/>");
			flag = false;
		}
		if (flag)
			$("form[name='querywebapi']").submit();
		return flag;
	}
</script>
</head>
<body>
<form name="querywebapi" method="post" action="common/queryWebapiListByapiName?num=1">
	<table width="810px;" border="0" cellpadding="0" cellspacing="0" align="center">
		<tr>
			<td><br><font style="font-size: 16px;font-weight: bold;color: gray;"><fmt:message key="webapi_resource_list"/></font><br><br></td>
		</tr>
		<tr>
			<td>
				<table width="810px;" bgcolor="#CCCCCC" border="0" cellspacing="1" cellpadding="1" class="contTable">
					
					<tr>
						<td align="center">
							<fmt:message key="find_by_webapiname"/>：
							<input id="apiName" type="text" name="apiName" style="width: 150px;line-height: 15px;height: 15px;ba">
						    <input type="button" onclick='queryWebapiListByapiName()' class="txtInp"  style="width: 60px;" value="<fmt:message key="look_up_button"/>" />&nbsp;&nbsp; 
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>
<br>
<div id="apply" align="center"></div>
<form name="" action="" method="post" >
	<table width="810px;" bgcolor="#CCCCCC" align="center" border="0" cellspacing="1" cellpadding="1" class="contTable">
		<tr class="slt_label_style" align="center">
			<th width="100px;"><fmt:message key="sequence_number"/></th>
			<th width="160px;"><fmt:message key="api_name"/></th>
			<th width="180px;"><fmt:message key="api_url"/></th>
			<th width="190px;"><fmt:message key="api_usage"/></th>
			<th width="180px;"></th>
		</tr>
		<c:forEach var="temp" items="${webapiInfoList}" varStatus="status">
			
			<tr  align="center">
				<td>${status.count }</td>
				<td>${temp.apiName }</td>
				<td>${temp.apiUrl }</td>
				<td>${temp.apiUsage }</td>
				<td>
					<a href="common/showWebapiByWebapiId?id=${temp.id}" target="_blank"><fmt:message key="look_button"/></a> 
					<c:if test="${!empty user }"> |
						<c:set var="flag1" value="false"></c:set>
						<c:set var="flag2" value="-1"></c:set>
						<c:forEach var="temp1" items="${authorizedWebapiList}">
							<c:if test="${temp.id == temp1.webapi.id}">
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
							<a href="javascript:void(0)" onclick="applyForWebapiAuthorisation('${temp.id}', '${temp.apiName }')"><fmt:message key='apply'/></a>
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
	 		<jsp:param value="common/webapiInfoList" name="url"/>
	 	</jsp:include>
	 </div>
</form>
</body>
</html>
</fmt:bundle>
