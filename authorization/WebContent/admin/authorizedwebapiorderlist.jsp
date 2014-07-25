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

	function checkUpdateLimitRequest(id){
		
		if(trim($("#limitRequest"+id).val()) == ""){
			$("#limitRequestTip"+id).css({color:"red"});
			$("#limitRequestTip"+id).html("<fmt:message key='null_tip'/>");
		}else{
			$.post("admin/setWebapiLimitRequestTimes", {id : id,limitRequest : trim($('#limitRequest'+id).val()) }, function(data) {
				$("#limitRequestTip"+id).css({color:"red"});
				$("#limitRequestTip"+id).html("<fmt:message key='change_limit_request'/>");
			}, "text");
		}
	}
	function setWebapiAuthorization(id,value){
		if(value == "2"){
			$("#setAuthorization").append("<form method='post' action='admin/setWebapiAuthorization' name='setAuthorization' >"
					+ "<fmt:message key='remark_input'/>：<input id='remark' type='text' name='remark' style='height:15px;'/>"
					+ "<input id='id' type='hidden' name='id' value='"+id+"'>"
					+ "<input id='isAuthorization' type='hidden' name='isAuthorization' value='"+value+"'>"
					+ "<input type='button' value='<fmt:message key='submit_button'/>' class='txtInp' style='width: 60px;' onclick='checkSetAuthorization()'>"//"+id+","+$("#hostUrl").val()+"
					+ "&nbsp;<span id='remarkTip' style='color:red;'></span></form>");
		}else{
			$.post("admin/setWebapiAuthorization", {
				id : id,
				isauthorized : value,
				remark : ""
			}, function(data) {
				var a = $("<a/>");
				a.click(function() {
					window.location.href = "admin/authorizedWebapiList";
				});
				a.click();
			}, "text");
		}
	}
	function checkSetAuthorization() {
		if (trim($("#remark").val()) != "") {

			$.post("admin/setWebapiAuthorization", {
				id : $("#id").val(),
				isauthorized : $("#isAuthorization").val(),
				remark : trim($("#remark").val())
			}, function(data) {
				var a = $("<a/>");
				a.click(function() {
					window.location.href = "admin/authorizedWebapiList";
				});
				a.click();
			}, "text");
		} else {
			$("#remarkTip").html("<fmt:message key='null_tip'/>");
		}
	}
	function queryWebapiByAuthorization(){
		var flag = true;
		if("000" == $("select").val()){
			alert("<fmt:message key='tip5'/>");
			flag = false;
		}
		if(flag)
			$("form[name='querywebapi']").submit();
		return flag;	
	}
</script>
</head>
<body>

<form method="post" action="admin/queryAuthorizedWebapiListByAuthorization?num=1" name="querywebapi">
	<table width="800px;" border="0" cellpadding="0" cellspacing="0" align="center">
		<tr>
			<td><br>&nbsp;<font style="font-size: 15px;font-weight: bold;color: gray;"><fmt:message key='manage_webapi_authorization'/></font><br><br></td>
		</tr>
		<tr>
			<td>
				<table width="800px;" bgcolor="#CCCCCC" border="0" cellspacing="1" cellpadding="1" class="contTable">
					
					<tr>
						<td align="center">
							<fmt:message key='look_up_by_status'/>：
							<select name="isauthorized" style="width: 260px;"> 
								<option value="000"><fmt:message key='select_status'/></option>
								<option value="0"><fmt:message key='wait_status'/></option>
								<option value="1"><fmt:message key='agree_status'/></option>
								<option value="2"><fmt:message key='reject_status'/></option>
							</select>
						    <input type="button" onclick="queryWebapiByAuthorization()" class="txtInp" style="width: 60px;" value="<fmt:message key='look_up_button'/>" />&nbsp;&nbsp; 
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>
<div id="setAuthorization" align="center"></div>
<form action="" method="post" name="" >
	<table width="800px;" bgcolor="#CCCCCC" align="center" border="0" cellspacing="1" cellpadding="1" class="contTable">
		<tr class="slt_label_style" align="center">
			<th width="50px;"><fmt:message key='sequence_number'/></th>
			<th width="150px;"><fmt:message key='authorized_api_id'/></th>
			<th width="100px;"><fmt:message key='api_name'/></th>
			<th width="100px;"><fmt:message key='user_name'/></th>
			<th width="120px;"><fmt:message key='host_url'/></th>
			<th width="130px;"><fmt:message key='limit_request_times'/></th>
			<th width="130px;"><fmt:message key='status'/></th>
		</tr>
		<c:forEach var="temp" items="${authorizedWebapiList}" varStatus="status">
			
			<tr  align="center">
				<td>${status.count }</td>
				<td>${temp.webapi.apiName }</td>
				<td>${temp.user.userName }</td>
				<td>${temp.hostUrl }</td>
				<td>
					<input id="limitRequest${temp.id }" type="text" value="${temp.limitRequest }" style="width: 35px;height: 14px;line-height: 14px;" >
					<input type="button" value="<fmt:message key='change'/>" onclick="checkUpdateLimitRequest('${temp.id }')" style="width: 60px;">
					<br><span id="limitRequestTip${temp.id }"></span>
					
				</td>
				<td colspan="2">
					<select name="isauthorized" style="width: 120px;" onchange="setWebapiAuthorization('${temp.id }',this.value)">
						<option value="${temp.isauthorized }" >
							<c:if test="${temp.isauthorized==0}">
								<fmt:message key='wait_authorization'/>
							</c:if>
							<c:if test="${temp.isauthorized==1}">
								<fmt:message key='agree_authorization'/>
							</c:if>
							<c:if test="${temp.isauthorized==2}">
								<fmt:message key='reject_authorization'/>
							</c:if>
						</option>
						<c:if test="${temp.isauthorized==0}">
							<option value="1"><fmt:message key='agree_authorization'/></option>
							<option value="2"><fmt:message key='reject_authorization'/></option>
						</c:if>
						<c:if test="${temp.isauthorized==1}">
							<option value="2"><fmt:message key='reject_authorization'/></option>
						</c:if>
						<c:if test="${temp.isauthorized==2}">
							<option value="1"><fmt:message key='agree_authorization'/></option>
						</c:if>
					</select>
				</td>	
			</tr>
		</c:forEach>
	</table>
	<br>
	 <div align="center" style="width: 800px;">
	 	<jsp:include page="../pagination.jsp">
	 		<jsp:param value="admin/queryAuthorizedWebapiListByAuthorization" name="url"/>
	 	</jsp:include>
	 </div>
</form>
</body>
</html>
</fmt:bundle>
