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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重置密码/reset password</title>
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
		}else{
			color = "red";
			if(id == "newpwd" && (value.length < 5)){
				msg = "<img src='images/wrong.gif'/><fmt:message key='userpwd_error'/>";
			}
			if(id == "repwd"  && (value != $("#newpwd").val())){
				msg = "<img src='images/wrong.gif'/><fmt:message key='repwd_error'/>";
			}
		}
		document.getElementById(id+"tip").style.color = color;
		document.getElementById(id+"tip").innerHTML = msg;
	}
	function checkAll(){
		var flag = true;
		
		if(trim($("#newpwd").val()) == "" ||(trim($("#newpwd").val()).length < 5)){
			$("#newpwd").focus();
			flag = false;
		}
		if(trim($("#repwd").val()) == "" || trim($("#repwd").val()) != trim($("#newpwd").val())){
			$("#repwd").focus();
			flag = false;
		}
		return  flag;
	}

</script>
</head>

<body>

头部……
<hr />

<form action="common/resetPwd" method="post" name="resetPwd">
<table width="700px;">
  <tr>
    <td colspan="2"><font size="+2"><fmt:message key="resetpwd_title"/></font></td>
  </tr>
  <tr>
  	<td>&nbsp;</td>
	<td>&nbsp;</td>
  </tr>
  <tr>
    <td class="a" width="130px;"><fmt:message key="newpwd"/>：</td>
    <td width="570px;"><input id="newpwd" name="newpwd" type="password" onblur="checkEach(this.id,this.value)"/>
   	 	<span id="newpwdtip"><fmt:message key="userpwd_tip"/></span></td>
  </tr>
  <tr>
    <td class="a" width="130px;"><fmt:message key="inputpwd_again"/>：</td>
    <td width="570px;"><input id="repwd" name="repwd" type="password" onblur="checkEach(this.id,this.value)"/>
    	<span id="repwdtip"><fmt:message key="repwd_tip"/></span></td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td><input type="submit" value="<fmt:message key="submit_button"/>" onclick="return checkAll();"/></td>
  </tr>
</table>
</form>

<hr />
底部……

</body>
</html>
</fmt:bundle>