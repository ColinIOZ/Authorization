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
<title></title>
<link  href="css/style.css"  rel="stylesheet" type="text/css"/>
<link  href="css/base.css"  rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
		/*
		 * 凡是在javascript中遇到的路径跳转问题  解决方案：在路径前先加/代表服务器的端口号路径  再加项目名
		 */
		function testForward(url,path){
			var num = document.getElementById("num").value;
			url = path + "/" + url + "num=" + num;
			window.location.href = url;
		}
	</script>

  </head>
  
  <body>
  	<%
  		//凡是使用该页面做分页的页面 必须传递过来一个参数名为url的地址
  		String url = request.getParameter("url");
  		url = url + (url.indexOf("?")>0?"&":"?");
  	 %>
    <!-- 使用此页面的前提是和PageUtil类一起使用  并且将PageUtil类放到作用域的属性名应该为page -->
  	<c:if test="${page.pageCount!=0 }">
		<c:if test="${page.num!=page.first }">
			<a href="<%=url %>num=${page.first }">【<fmt:message key="home_page"/>】</a>
			<a href="<%=url %>num=${page.prev }">【<fmt:message key="previous_page"/>】</a>
		</c:if>
		
		<c:forEach begin="${page.start }" end="${page.end }" var="i">
			<c:choose>
				<c:when test="${page.num==i }">
					<b style="color: purple">【${i }】</b>
				</c:when>
				<c:otherwise>
					<a href="<%=url %>num=${i }">【${i }】</a>
				</c:otherwise>
			</c:choose>
		</c:forEach> 
		
		<c:if test="${page.num!=page.last }">
			<a href="<%=url %>num=${page.next }">【<fmt:message key="next_page"/>】</a>
			<a href="<%=url %>num=${page.last }">【<fmt:message key="last_page"/>】</a>
		</c:if>
		<fmt:message key="forward_page">
			<fmt:param><input type="text" id="num" style="width: 30px;height: 14px"/></fmt:param>
		</fmt:message>
		<input type="button" value="<fmt:message key="forward_button"/>" onclick="testForward('<%=url %>','<%=path %>')" style="width: 50px;">
		<br>	
		<fmt:message key="all_number">
			<fmt:param><b style="color: red">&nbsp;${page.rowCount }&nbsp;</b></fmt:param>
		</fmt:message>	
		&nbsp;
		<fmt:message key="current_page">
			<fmt:param>&nbsp;<b style="color: red">${page.num }</b>/<b style="color: red">${page.pageCount }</b>&nbsp;</fmt:param>
		</fmt:message>
  	</c:if>
  </body>
</html>
</fmt:bundle>