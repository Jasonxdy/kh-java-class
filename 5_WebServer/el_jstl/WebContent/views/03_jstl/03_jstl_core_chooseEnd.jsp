<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL - core tag(choose)</title>
</head>
<body>
	<h3>&lt;c:choose> 태그</h3>
	<p>
		- Java의 if-else, switch 문과 비슷한 역할을 하는 태그 <br>
		- &lt;c:choose>는 &lt;c:when> / &lt;c:otherwise> 태그들과 함께 사용이 됨.
	</p>
	
	${param.age }세,
	<c:choose>
		<c:when test="${Integer.parseInt(param.age) > 60}">
			노약자입니다. 기본 요금은 0원입니다.
		</c:when>
		<c:when test="${Integer.parseInt(param.age) > 19}">
			성인입니다. 기본 요금은 1,250원입니다.
		</c:when>
		<c:when test="${Integer.parseInt(param.age) > 13}">
			청소년입니다. 기본 요금은 720원입니다.
		</c:when>
		<c:when test="${Integer.parseInt(param.age) > 7}">
			초등학생입니다. 기본 요금은 450원입니다.
		</c:when>
		<c:otherwise>
			영유아입니다. 무료입니다.
		</c:otherwise>
	</c:choose>
	

</body>
</html>