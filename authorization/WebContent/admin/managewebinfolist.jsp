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

	function checkEach(id,value){
		value = trim(value);
		var color = "";
		var msg = "<img src='images/right.gif'/>";
		if(value == ""){
			color = "red";
			msg = "<img src='images/wrong.gif' /><fmt:message key='null_tip'/>";
		}
		document.getElementById(id+"tip").style.color = color;
		document.getElementById(id+"tip").innerHTML = msg;
	}
	function checkDel(id){
		if(confirm("<fmt:message key='sure_delete'/>")){
			window.location.href="admin/deleteWebInfo?id=" + id;
		}
		
	}
	function checkDelAll(){
		return confirm("<fmt:message key='sure_delete_all'/>");
	}
	function checkSelect(obj){
		var ids = document.getElementsByName("ids");
		if(obj.checked){
			for(var i = 0 ; i < ids.length ; i++){
				ids[i].checked = true;
			}
		}else{
			for(var i = 0 ; i < ids.length ; i++){
				ids[i].checked = false;
			}
		}	
	}
	function checkAll(){
		var flag = true;
		
		if(trim($("#webName").val()) == ""){
			$("#webName").focus();
			flag = false;
		}
		if(trim($("#webHost").val()) == ""){
			$("#webHost").focus();
			flag = false;
		}
		if(trim($("#logoUrl").val()) == ""){
			$("#logoUrl").focus();
			flag = false;
		}
		return  flag;
	}
	
</script>
</head>
<body>

<form action="admin/addWebInfo" method="post" name="addWebInfo">
	<table width="800px;" border="0" cellpadding="0" cellspacing="0" align="center">
		<tr>
			<td width="200px;">&nbsp;</td>
			<td width="500px;">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="2"><span style="color: red">${WebInfoTip }</span></td>
		</tr>
		<tr>
			<td>&nbsp;<font style="font-size: 15px;font-weight: bold;color: gray;"><fmt:message key='add_modify_web'/>:</font></td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td width="200px;">&nbsp;</td>
			<td width="500px;">&nbsp;</td>
		</tr>
		<tr>
			<td class="a"><fmt:message key='web_name'/>：<input name="id" type="hidden" value="${empty webinfo.id?'-1':webinfo.id }"/></td>
			<td><input id="webName" name="webName" type="text" onblur="checkEach(this.id,this.value)" value="${webinfo.webName }"/>
				<span id="webNametip" style="color: red">(*<fmt:message key='must_fill_in'/>)</span></td>
		</tr>
		<tr>
			<td class="a"><fmt:message key='web_host'/>：</td>
			<td><input id="webHost" name="webHost" type="text" onblur="checkEach(this.id,this.value)" value="${webinfo.webHost }"/>
				<span id="webHosttip" style="color: red">(*<fmt:message key='must_fill_in'/>)</span></td>
		</tr>
		<tr>
			<td class="a"><fmt:message key='logo_url'/>：</td>
			<td><input id="logoUrl" name="logoUrl" type="text" onblur="checkEach(this.id,this.value)" value="${webinfo.logoUrl }" />
				<span id="logoUrltip" style="color: red">(*<fmt:message key='must_fill_in'/>)</span></td>
		</tr>
		<tr>
			<td class="a"><fmt:message key='introduction'/>：</td>
			<td><textarea rows="5" cols="5" id="briefIntroduction" name="briefIntroduction">${webinfo.briefIntroduction }</textarea>
				<span id="briefIntroductiontip" style="color: red"></span></td>
		</tr>
		<tr>
		  	<td>&nbsp;</td>
		    <td><input type="submit" value="<fmt:message key='save'/>" onclick="return checkAll();" name="save" /></td>
	   </tr>
	</table>
</form>
<br>
<form action="admin/deleteWebInfos" method="post" onsubmit="return checkDelAll();" name="delform" >
	<div style="width: 800px;">&nbsp;<font style="font-size: 15px;font-weight: bold;color: gray;"><fmt:message key='web_resource_list'/>:</font></div>
	<table width="800px;" bgcolor="#CCCCCC" align="center" border="0" cellspacing="1" cellpadding="1" class="contTable">
		<tr class="slt_label_style" align="center">
			<th width="100px;"><fmt:message key='sequence_number'/></th>
			<th width="80px;"><fmt:message key='web_name'/></th>
			<th width="90px;"><fmt:message key='web_host'/></th>
			<th width="90px;"><fmt:message key='logo_url'/></th>
			<th width="280px;"><fmt:message key='introduction'/></th>
			<th colspan="2" width="110px;"><fmt:message key='operate'/></th>
		</tr>
		<c:forEach var="temp" items="${webinfoList}" varStatus="status">
			
			<tr  align="center">
				<td>${status.count }</td>
				<td>${temp.webName }</td>
				<td>${temp.webHost }</td>
				<td>${temp.logoUrl }</td>
				<td>${temp.briefIntroduction }</td>
				<td width="40px;"><a href="admin/modifyWebInfoPre?flag=modify&id=${temp.id}"><fmt:message key='modify'/></a></td>	
				<td width="70px;"><a href="javascript:void(0)" onclick="checkDel('${temp.id}')"><fmt:message key='delete'/></a>
					<input name="ids" type="checkbox" value="${temp.id }"></td>	
			</tr>
		</c:forEach>
		<tr class="slt_label_style" align="center">
			<th colspan="7" align="right">
				<input type="checkbox" onclick="checkSelect(this)" ><fmt:message key='check_all'/>&nbsp;
				<input name="delall" type="submit" value="<fmt:message key='delete_all'/>"></th>
		</tr>
	</table>
	<br>
	 <div align="center" style="width: 800px;">
	 	<jsp:include page="../pagination.jsp">
	 		<jsp:param value="admin/manageWebInfoList" name="url"/>
	 	</jsp:include>
	 </div>
</form>
</body>
</html>
</fmt:bundle>
