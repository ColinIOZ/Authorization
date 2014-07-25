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
<title>用户登录/user login</title>
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
	function checkAll(){
		var flag = true;
		
		if(trim($("#username").val()) == "" 
				|| trim($("#username").val()) == "<fmt:message key='username_input_tip'/>"){
			$("#loginError").html("<fmt:message key='login_error1'/>");
			flag = false;
		}else if(trim($("#userpwd").val()) == ""){
			$("#loginError").html("<fmt:message key='login_error2'/>");
			flag = false;
		}else if(trim($("#validateCode").val()) == ""){
			$("#loginError").html("<fmt:message key='login_error3'/>");
			flag = false;
		}
		if(trim($("#username").val()) == "" 
				|| trim($("#username").val()) == "<fmt:message key='username_input_tip'/>"){
			if(trim($("#userpwd").val()) == ""){
				$("#loginError").html("<fmt:message key='login_error4'/>");
				flag = false;
			}else if(trim($("#validateCode").val()) == ""){
				$("#loginError").html("<fmt:message key='login_error5'/>");
				flag = false;
			}
			if(trim($("#userpwd").val()) == "" 
					&& trim($("#validateCode").val()) == ""){
				$("#loginError").html("<fmt:message key='login_error6'/>");
				flag = false;
			}
		}else{
			if(trim($("#userpwd").val()) == "" && trim($("#validateCode").val()) == ""){
				$("#loginError").html("<fmt:message key='login_error7'/>");
				flag = false;
			}
		}
		$("#validateCode").blur();
		return  flag;
	}
</script>
</head>

<body>

头部……
<hr />
<table width="900px;">
	<tr>
		<td align="center"><img src="login"  height="400" width="500"/></td>
		<td><form action="login" method="post" name="loginform" id="loginform">  
				<table width="420px;">
					
					<tr>
						<td>&nbsp;</td>
						<td><font size="+2"><fmt:message key="login"></fmt:message></font></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><span id="loginError" style="color: red;">
								${loginError }
							</span></td>
					</tr>
					<tr>
						<td align="right" width="140px;"><fmt:message key="username"></fmt:message>：</td>
						<td width="260px;">
							<c:if test="${empty loginName }">
								<input id="username" name="username"
								type="text"
								value="<fmt:message key='username_input_tip'/>"
								onFocus="if(value==defaultValue){value='';}"
								onBlur="if(!value){value=defaultValue;}"/>
							</c:if>
							<c:if test="${!empty loginName }">
								<input id="username" name="username" type="text"
								value="${loginName }"/>
							</c:if>
						</td>
				</tr>
					<tr>
						<td align="right" width="140px;"><fmt:message key="userpwd"></fmt:message>：</td>
						<td width="260px;"><input id="userpwd" name="userpwd" type="password" value="${loginPwd }"/></td>
					</tr>
					<tr>
						<td align="right" width="130px;"><fmt:message key="validateCode"></fmt:message>：</td>
						<td width="270px;">
							<div style="float: left;">
								<input id="validateCode" 
									name="validateCode" type="text" maxlength="6" /></div>
							<div id="showValidateCode" style="float: left;">&nbsp;&nbsp;
							<img id="validateCodeImg" src="validateCode"
								onclick="changeValidateCode(this)"
								title="<fmt:message key="validateCode_tip"/>" />&nbsp;</div>
						</td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="<fmt:message key="login"></fmt:message>" onclick="return checkAll();"/>&nbsp;&nbsp;&nbsp;&nbsp;
							<font size="-1"><a href="common/forgetpwd.jsp"><fmt:message key="forget_password"></fmt:message></a></font></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr><td></td>
						<td><font size="-1"><fmt:message key="no_account"></fmt:message><a href="common/register.jsp"><fmt:message key="reg_now"></fmt:message></a></font></td>
					</tr>
					
				</table>
			</form>
		</td>
	</tr>
</table>
<hr />
</body>
</html>
</fmt:bundle>
