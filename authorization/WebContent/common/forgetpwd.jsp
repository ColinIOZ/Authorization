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
	function changeValidateCode(obj) {
		/***  
		 *   获取当前的时间作为参数，无具体意义     
		 *   每次请求需要一个不同的参数，否则可能会返回同样的验证码      
		 *   这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。    
		 */
		var timenow = new Date().getTime();
		obj.src = "validateCode?d=" + timenow;
	}
	function checkEach(id,value){
		value = trim(value);
		var color = "green";
		var msg = "<img src='images/right.gif'/>";
		if(value == ""){
			color = "red";
			msg = "<img src='images/wrong.gif'/><fmt:message key='null_tip'/>";
		}else{
			color = "red";
			if(id == "email"){
				if(isEmail(value)){
					$.post("common/validateEmailOrUserName", {flag: "1",email: value},function(data){
						if("true" != data)
							$("#emailtip").html("<img src='images/wrong.gif'/><fmt:message key='email_not_registerd'/>");
					},"text");
				}else{
					msg = "<img src='images/wrong.gif'/><fmt:message key='email_error'/>";
				}
			}
			if(id == "validateCode"){
				$.post("checkValidateCode", {validateCode: value},function(data){
					if("false" == data){
						$("#validateCodetip").html("<img src='images/wrong.gif'/><fmt:message key='validateCodeError'/>");
						$("#validateCode").val("");
						$("#validateCodeImg").click();
					}
				},"text");
			}
		}
		document.getElementById(id+"tip").style.color = color;
		document.getElementById(id+"tip").innerHTML = msg;
	}
	function checkAll(){
		var flag = true;
		
		if(trim($("#email").val()) == "" || !isEmail(trim($("#email").val()))){
			$("#email").focus();
			flag = false;
		}
		if(trim($("#validateCode").val()) == ""){
			$("#validateCode").focus();
			flag = false;
		}
		return flag;
	}
	
</script>
</head>
<body>

头部……
<hr />
<form action="common/findPwdWithEmail" method="post" name="forgetpwd">
<table width="550px;">
  <tr>
   <td colspan="2"><font size="+2"><fmt:message key="findpwd_title"/></font></td>
  </tr>
  <tr>
  	<td width="140px;">&nbsp;</td>
	<td width="410px;"><span style="color: red">${findPwdError }</span></td>
  </tr>
  <tr>
    <td align="right" width="140px;"><fmt:message key="email"/>：</td>
    <td width="410px;"><input id="email" name="email" type="text" onblur="checkEach(this.id,this.value)" value="${emailInput }"/>
    	<span id="emailtip"><fmt:message key="findpwd_email_tip"/></span></td>
  </tr>
  <tr>
    <td align="right" width="140px;"><fmt:message key="validateCode"></fmt:message>：</td>
    <td width="410px;"><div style="width: 200px;float: left;">
    	<div style="float: left;">
    		<input id="validateCode" name="validateCode" type="text" maxlength="6" onblur="checkEach(this.id,this.value)"/></div>
		<div style="float: right;">
			<img id="validateCodeImg" src="validateCode"  onclick="changeValidateCode(this)" title="<fmt:message key="validateCode_tip"/>" />    
		</div></div>
		<div style="float: left;line-height: 20px;text-align: center; ">
			&nbsp;&nbsp;<span id="validateCodetip"></span></div></td>
  </tr>
  <tr>
  	<td>&nbsp;</td>
    <td><input type="submit" value="<fmt:message key="findpwd_next"/>" onclick="return checkAll();"/></td>
  </tr>
</table>
</form>

<hr />
底部……
</body>
</html>
</fmt:bundle>