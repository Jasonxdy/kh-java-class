<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL - core tag (forEach2)</title>
</head>
<body>
<!-- 
	톰캣 8.0이상 부터 지원하는 EL 3.0에서는 EL을 이용하여 Collection 객체를 만들 수 있음.
-->

<c:set var="myList"
		value='${["A","B","C","D","E"]}'
		scope="request"/>
		<!-- [] : List의 기호 -->

<c:set var="mySet"
		value='${{"A","B","C","A","B"}}'
		scope="request"/>
		<!-- {} : Set의 기호 -->
		
<c:set var="myMap"
		value='${{"name":"홍길동", "job":"학생", "age":50}}'
		scope="request"/>
		<!-- Map 객체의 Key는 문자열로 전달함. -->
		<!-- 또한, Key가 Set의 성격을 가지고 있으므로 이 또한 중괄호 {}로 표현해줌 -->
		
<jsp:forward page="05_jstl_core_forEach2End.jsp"/>
<!-- jsp action tag중 forward 동작을 하는 태그... a href는 sendRedirect 느낌으로 페이지를 이동하기 때문 -->




</body>
</html>