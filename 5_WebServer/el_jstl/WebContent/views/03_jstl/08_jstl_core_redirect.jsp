<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL - core tag (redirect)</title>
</head>
<body>

	<h3>&lt;c:redirect> 태그</h3>
	<p>
		- response.sendRedirect()와 같은 동작<br>
	</p>
	
	<c:set var="test" value="${param.test}"/>
	<c:if test="${test == 1}">
<%-- 		<c:redirect url = "${pageContext.servletContext.contextPath}"/> --%>
								<!-- request.getContextPath()의 EL 표현식 -->
									<!-- 근데 이건 안되네 ㅋㅋ -->			
		<c:redirect url = "/"/>
	</c:if>

</body>
</html>