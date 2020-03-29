<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL - core tag (if) End</title>
</head>
<body>

	<h3>&lt;c:if> 태그</h3>
	<p>
		- Java의 if문과 비슷한 역할을 하는 태그. <br>
		- &lt;c:if> 태그에서 조건식은 test라는 속성의 값으로 <br>
		    조건식을 지정해야 함.<br>
		    이때, 조건식은 반드시 EL 형식으로 작성해야만 함.
	</p>
	
	<hr>
	
	<c:if test="${param.num1 > param.num2}">
		${param.num1}은 ${param.num2}보다 큽니다
	</c:if>
	
	<!-- 
	전달된 parameter의 자료형은 String(문자열)으로 숫자로 parsing해주지 않으면
	문자열의 사전 배치 순서로 비교를 함
	-->
	<hr>
	
	<c:if test="${Integer.parseInt(param.num1) > Integer.parseInt(param.num2)}">
		${param.num1}은 ${param.num2}보다 큽니다
	</c:if>

	<hr>
	
	<c:if test="${param.num1 < param.num2}">
		${param.num1}은 ${param.num2}보다 작습니다
	</c:if>
	
	<!-- 
	전달된 parameter의 자료형은 String(문자열)으로 숫자로 parsing해주지 않으면
	문자열의 사전 배치 순서로 비교를 함
	-->
	<hr>
	
	<c:if test="${Integer.parseInt(param.num1) < Integer.parseInt(param.num2)}">
		${param.num1}은 ${param.num2}보다 작습니다
	</c:if>
	<c:if test="${param.num1 < param.num2}">
		${param.num1}은 ${param.num2}보다 작습니다
	</c:if>

	<hr>
	
	<c:if test="${Integer.parseInt(param.num1) < Integer.parseInt(param.num2)}">
		${param.num1}은 ${param.num2}보다 작습니다
	</c:if>
	
	<hr>
	<c:if test="${param.num1 == param.num2}">
		${param.num1}은 ${param.num2}보다 작습니다
	</c:if>
	
	<hr>
	
	<%-- <c:if test="${Integer.parseInt(param.num1) == Integer.parseInt(param.num2)}">
		${param.num1}은 ${param.num2}보다 작습니다
	</c:if> --%>
	
	<!-- == 연산자는 parsing 안해줘도 됨. 왜냐면 EL 내에서는 equals와 같은 동작을 하므로 비교 가능.. (String pool에 같은 참조변수를 참조함) -->

</body>
</html>