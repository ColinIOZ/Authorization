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
<title>modifypwd</title>
<link  href="css/style.css"  rel="stylesheet" type="text/css"/>
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
			if(id == "userpwd"){
				$.post("user/validateUserPwd", {userpwd: value},function(data){
					if("true" == data)
						$("#userpwdtip").html("<img src='images/wrong.gif'/><fmt:message key='oldpwd_error'/>");
				},"text");
			}
			if(id == "newpwd" && (value.length < 5)){
				msg = "<img src='images/wrong.gif'/><fmt:message key='userpwd_error'/>";
				
			}
			if(id == "renewpwd" && (value != $("#newpwd").val())){
				msg = "<img src='images/wrong.gif'/><fmt:message key='repwd_error'/>";
			}
		}
		document.getElementById(id+"tip").style.color = color;
		document.getElementById(id+"tip").innerHTML = msg;
	}
	function checkAll(){
		var flag = true;
	
		if(trim($("#userpwd").val()) == "" ){
			$("#userpwd").focus();
			flag = false;
		}
		if(trim($("#newpwd").val()) == "" || trim($("#newpwd").val()).length < 5){
			$("#newpwd").focus();
			flag = false;
		}
		if(trim($("#renewpwd").val()) != trim($("#newpwd").val())){
			$("#renewpwd").focus();
			flag = false;
		}
		return  flag;
	}
</script>
</head>

<body>

<form action="user/modifyUserPwd" method="post" name="userinfo">
	<table width="600px" height="200px" align="left">
	  <tr>
		<td colspan="2"><br>&nbsp;<font style="font-size: 16px;font-weight: bold;color: gray;"><fmt:message key='modifypwd'/>:</font></td>
	  </tr>
	  <tr>
		<td width="120px;">&nbsp;</td>
		<td width="480px;"><span style="color: red">${modifyError }</span></td>
	  </tr>
	  <tr>
    	<td class="a"><fmt:message key='oldpwd'/>：</td>
    	<td><input id="userpwd" name="userpwd" type="password"  value="" onblur="checkEach(this.id,this.value)"/>
    		<span id="userpwdtip" style="width: 180px;"></span></td>
  	  </tr>
	  <tr>
    	<td class="a"><fmt:message key='newpwd'/>：</td>
    	<td><input id="newpwd"  name="newpwd" type="password"  value="" onblur="checkEach(this.id,this.value)"/>
    		<span id="newpwdtip"></span></td>
  	  </tr>
	  <tr>
		<td class="a"><fmt:message key='inputpwd_again'/>：</td>
		<td><input id="renewpwd"  name="renewpwd" type="password"  value="" onblur="checkEach(this.id,this.value)"/>
			<span id="renewpwdtip"></span></td>
	  </tr>
	  <tr>
  		<td>&nbsp;</td>
    	<td><input type="submit" value="<fmt:message key='sure_modify'/>" onclick="return checkAll();"/></td>
  	  </tr>
	</table>
</form>
</body>
</html>
</fmt:bundle>