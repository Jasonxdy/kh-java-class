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

	<h3>&lt;c:forEach> 태그</h3>
	<p>
		- Java의 for문에 해당하는 기능을 가진 태그. <br>
		- forEach는 여러가지 속성이 사용됨.
		
		<pre>
		var : 현재 반복 횟수에 해당하는 변수의 이름 (초기식에 사용되는 변수)
		begin : 반복이 시작할 요소 번호
		end : 반복이 끝나는 요소 번호(끝 번호)
		step : 반복 시 증가할 수 (증감식)
		items : 반복할 객체 명 (배열, Collection 객체)
		varStatus : 현재 반복에 해당하는 객체의 상태
		</pre>
	</p>
	
	<hr>
	<h3>forEach 기본 사용법</h3>
	<c:forEach var="i" begin="1" end="5">
		<!-- 별도로 step을 지정하지 않으면 1씩 증가 -->
		<h<c:out value="${i}"/>>
			c:forEach 기본 사용법
		<h<c:out value="${i}"/>/>
	</c:forEach>
	
	
	<hr>
	
	<h3>step 속성</h3>
	
	<c:forEach var="i" begin="1" end="5" step="2">
		<h${i}>
			현재 태그는 h${i}입니다.
		<h${i}/>
	</c:forEach>


	<%-- <c:forEach var="i" begin="5" end="1" step="-1">
		<h${i}>
			현재 태그는 h${i}입니다.
		<h${i}/>
	</c:forEach> --%>
	
	* step 속성은 0 이하의 값으로 설정할 수 없다.<br>
	
	<h3>역순</h3>
	<c:forEach var="i" begin="1" end="5">
		<h${6-i}>
			현재 태그는 h${6-i}입니다
		</h${6-i}>
	</c:forEach>
	


</body>
</html>