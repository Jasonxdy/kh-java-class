<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL - core tag (forEach)</title>
</head>
<body>

	<h2>컬렉션 객체 선언 및 출력</h2>
	
	<h3>varStatus</h3>
	<pre>
	current : 현재 반복 횟수 또는 현재 반복 접근한 값
	index   : 반복 라운드의 제로 기반(zero-based) 인덱스(0...n-1)
	count   : 반복 라운드의 1 기반(one-based) 인덱스(1...n)
	first   : 현재 라운드가 반복을 통한 첫 번째임을 의미
	last    : 현재 라운드가 반복을 통한 마지막임을 의미
	</pre>


	<hr>
	
	<h3>List</h3>
	<c:forEach var="i" items="${myList}" varStatus="vs">
		현재 값  : ${vs.current} <br>
		index : ${vs.index} <br>
		myList[${vs.index}] == ${i}<br>
		count  : ${vs.count} <br>
		first : ${vs.first }<br>
		last : ${vs.last } <br><br>
		<p>----------------------------------</p>
	</c:forEach>
	
	
	<hr>
	
	<h3>Set</h3>
	mySet = {
	
		<c:forEach var="i" items="${mySet}" varStatus="vs">
			${i}
			<c:if test="${!vs.last}">
				,&nbsp;
			</c:if>
		
		</c:forEach>
	}
	
	<hr>
	
	<h3>Map</h3>
	<c:forEach var="i" items="${myMap}" varStatus="vs">
		\${i} => ${i}<br>
		key=value => ${i.key}=${i.value}<br><br>
	</c:forEach>
	
</body>
</html>