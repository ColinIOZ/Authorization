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
<title>创建账户/register</title>
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
	function checkProtocolOnLoad(){
		if(id="protocol" && !$("#protocol").attr("checked")){
			$("#submit").attr("disabled", "disabled") ;
		}
	}
	function checkProtocol(){
		if($("#protocol").attr("checked")){
			$("#submit").removeAttr("disabled");
		}else{
			$("#submit").attr("disabled", "disabled") ;
		}
	}
	function checkEach(id,value){
		value = trim(value);
		var color = "";
		var msg = "<img src='images/right.gif'/>";
		if(value == ""){
			color = "red";
			msg = "<img src='images/wrong.gif' /><fmt:message key='null_tip'/>";
		}else{
			color = "red";
			if(id == "email"){
				if(isEmail(value)){
						
					$.post("common/validateEmailOrUserName", {flag: "1",email: value},function(data){
						if("true" == data)
							$("#emailtip").html("<img src='images/wrong.gif'/><fmt:message key='email_occupy'/>");
						},"text");
					
				}else{
					msg = "<img src='images/wrong.gif'/><fmt:message key='email_error'/>";
				}
			}
			if(id == "username"){
				if(value.length < 3 || value.length > 20){
					msg = "<img src='images/wrong.gif'/><fmt:message key='username_error'/>";
				}else{
					$.post("common/validateEmailOrUserName", {flag: "2",username: value},function(data){
						if("true" == data)
							$("#usernametip").html("<img src='images/wrong.gif'/><fmt:message key='username_occupy'/>");
					});
				}
			
			}
			if(id == "userpwd" && (value.length < 5)){
				msg = "<img src='images/wrong.gif'/><fmt:message key='userpwd_error'/>";
			}
			if(id == "repwd"  && (value != $("#userpwd").val())){
				msg = "<img src='images/wrong.gif'/><fmt:message key='repwd_error'/>";
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
		if(trim($("#email").val()) == "" || (!isEmail(trim($("#email").val())))){
			$("#email").focus();
			flag = false;
		}
		if(trim($("#username").val()) == "" 
				|| (trim($("#username").val()).length < 3) 
				|| (trim($("#username").val()).length > 20)){
			$("#username").focus();
			flag = false;
		}
		if(trim($("#userpwd").val()) == "" ||(trim($("#userpwd").val()).length < 5)){
			$("#userpwd").focus();
			flag = false;
		}
		if(trim($("#repwd").val()) == "" || trim($("#repwd").val()) != trim($("#userpwd").val())){
			$("#repwd").focus();
			flag = false;
		}
		if(trim($("#validateCode").val())== ""){
			$("#validateCode").focus();
			flag = false;
		}
		$("#validateCode").blur();
		return  flag;
	}

</script>
</head>

<body onload="checkProtocolOnLoad()">

头部……
<hr />
<form action="common/register" method="post" name="regForm" id="regForm">
<table width="800px;">
  <tr>
  	<td>&nbsp;</td>
    <td><font size="+2"><fmt:message key="register"></fmt:message></font></td>
  </tr>
  <tr>
  	<td>&nbsp;</td>
	<td><span id='regtip' style="color: red;">${regtip }</span></td>
  </tr>
  <tr>
    <td class="a" width="160px;"><fmt:message key="email"/>：</td>
    <td width="640px;"><input id="email" name="email" type="text" onblur="checkEach(this.id,this.value)" value="${regUser.email }"/>
		<c:if test="${empty email_occupy }">
			<span id="emailtip"><fmt:message key="email_tip"/></span>
		</c:if>
		<c:if test="${!empty email_occupy}">
			<span id="emailtip" style="color: red"><img src='images/wrong.gif'/>${email_occupy }</span>
		</c:if>
	</td>
  </tr>
  <tr>
    <td class="a" width="160px;"><fmt:message key="username"></fmt:message>：</td>
    <td width="640px;"><input id="username" name="username" type="text" maxlength="20" onblur="checkEach(this.id,this.value)" value="${regUser.userName }"/>
		<c:if test="${empty username_occupy }">
			<span id="usernametip"><fmt:message key="username_tip"/></span>
		</c:if>
		<c:if test="${!empty username_occupy }">
			<span id="usernametip" style="color: red"><img src='images/wrong.gif'/>${username_occupy }</span>
		</c:if>
	</td>
  </tr>
  <tr>
    <td class="a" width="160px;"><fmt:message key="userpwd"></fmt:message>：</td>
    <td width="640px;"><input id="userpwd" name="userpwd" type="password"  maxlength="20" onblur="checkEach(this.id,this.value)"/>
		<span id="userpwdtip"><fmt:message key="userpwd_tip"/></span></td>
  </tr>
  <tr>
    <td class="a" width="160px;"><fmt:message key="repwd"></fmt:message>：</td>
    <td width="640px;"><input id="repwd" name="repwd" type="password" onblur="checkEach(this.id,this.value)"/>
		<span id="repwdtip"><fmt:message key="repwd_tip"/></span></td>
  </tr>
  <tr>
    <td class="a" width="160px;"><fmt:message key="validateCode"></fmt:message>：</td>
    <td width="640px;"><div style="width: 200px;float: left;">
    	<div style="float: left;">
    		<input id="validateCode" name="validateCode" type="text" maxlength="6" onblur="checkEach(this.id,this.value)"/>
    	</div>
		<div style="float: right;">
			<img id="validateCodeImg" src="validateCode"  onclick="changeValidateCode(this)" title="<fmt:message key="validateCode_tip"/>" />    
		</div></div>
		<div style="float: left;line-height: 20px;text-align: center; ">
			&nbsp;&nbsp;
			<c:if test="${empty validateCodeError }">
				<span id="validateCodetip"></span>
			</c:if>
			<c:if test="${!empty validateCodeError }">
				<span id="validateCodetip" style="color: red"><img src='images/wrong.gif'/>${validateCodeError }</span>
			</c:if>
		</div></td>
  </tr>
  <tr>
  	<td>&nbsp;</td>
    <td><input id="protocol" name="protocol" type="checkbox" value="protocol" onchange="checkProtocol()"/>
		<font size="-1"><fmt:message key="protocol"></fmt:message></font></td>
  </tr>
  <tr>
  	<td>&nbsp;</td>
    <td><input id="submit" type="submit" value="<fmt:message key="register"></fmt:message>" onclick="return checkAll();" />
    </td>
  </tr>
</table>
</form>


<hr />
底部……

</body>
</html>
</fmt:bundle>