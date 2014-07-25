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
<title>找回密码/find password</title>
<link  href="css/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/CommonsUtil.js"></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript">
	function checkEach(id,value){
		value = trim(value);
		var color = "green";
		var msg = "";
		if(value == ""){
			color = "red";
			msg = "<img src='images/wrong.gif'/><fmt:message key='null_tip'/>";
		}
		document.getElementById(id+"tip").style.color = color;
		document.getElementById(id+"tip").innerHTML = msg;
	}
	function checkAll(){
		var flag = true;
		
		if(trim($("#validateCode").val()) == ""){
			$("#validateCode").focus();
			document.getElementById("validateCodetip").style.color = "red";
			document.getElementById("validateCodetiptip").innerHTML = "<img src='images/wrong.gif'/><fmt:message key='null_tip'/>";
			flag = false;
		}
		return flag;
	}
</script>
</head>

<body>
头部……
<hr />

<form action="common/findPwdNext" method="post" name="getAuthenticode">
<table width="550px;">
  <tr>
     <td colspan="2"><font size="+2"><fmt:message key="findpwd_title"/></font></td>
  </tr>
  <tr>
  	<td width="450px;">&nbsp;</td>
	<td width="100px;">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="2">&nbsp;&nbsp;
    	<fmt:message key="submit_findpwd_application">
    		<fmt:param><font color="red">${email }</font></fmt:param>
		</fmt:message>
	</td>
  </tr>
  <tr>
  	<td>&nbsp;</td>
	<td>&nbsp;</td>
  </tr>
  <tr>
  	<table width="450px;" style="margin-left: 1px;">
  		<tr>
  			<td align="right"><fmt:message key="validateCode"/>：</td>
    		<td><input id="validateCode" name="validateCode" type="text"  onblur="checkEach(this.id,this.value)" maxlength="6"/>
    		<span id="validateCodetip"></span></td>
  		</tr>
  		<tr><td></td><td><input type="submit" value="<fmt:message key="findpwd_next"/>" onclick="return checkAll();"/></td></tr>
  	</table>
  </tr>
   <tr>
  	<td>&nbsp;</td>
	<td>&nbsp;</td>
  </tr>
  <!-- 
  	  <tr>
  	<td colspan="2">
  		<hr style="width: 80%;color: silver;" align="left"><br>
  		<div>
  			没有收到验证邮件？<br>
			&nbsp;&nbsp;请在您邮箱中的垃圾邮件、广告邮件目录里找找看
  		</div>
  	</td>
  </tr>
   -->

</table>
</form>

<hr />
底部……
</body>
</html>
</fmt:bundle>
