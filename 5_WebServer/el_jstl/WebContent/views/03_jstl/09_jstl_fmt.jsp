<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL - fmt</title>
</head>
<body>
	<h2>JSTL Formatting Tag</h2>
	날짜, 시간, 숫자 데이터의 출력 형식을 지정할 때 사용하는 태그
	
	<hr>
	
	<h3>
		숫자 데이터 포멧 지정<br>
		&lt;fmt:formatNumber> 태그
	</h3>
	
	- 표현하고자 하는 숫자의 포맷을 <br>
	    통화 기호, ',' (콤마) 자리수 구분, % 표시 등 원하는 쓰임에 맞게 지정 가능.
	<br><br>
	
	<h3>groupingUsed : 숫자 단위의 구분자(,) 표시 여부</h3>
	숫자 그대로 출력 : <fmt:formatNumber value="123456789" groupingUsed="false"/><br>
	세자리마다 구분 출력 : <fmt:formatNumber value="123456789" groupingUsed="true"/><br>
	
	
	<hr>
	
	<h3>pattern : 화면에 표현할 데이터 스타일을 지정</h3>
	- 실수값 소수점 아래 자릿수 지정<br>
	- # 또는 0을 자릿수 지정에 사용<br>
	- 지정된 자릿수를 초과한 부분은 반올림됨<br><br>
	
	#을 사용한 경우 : <fmt:formatNumber value="1.234567" pattern="#.####"/><br>
	0, #을 사용한 경우 : <fmt:formatNumber value="1.234567" pattern="#.000"/>


	<hr>
	
	
	<h3>
		type: 백분율(%), 통화 기호 등의 타입을 지정하는 속성
	</h3>
	type 속성의 종류 : number, percent, currency<br><br>
	<fmt:formatNumber value="0.12" type="percent"/><br><br>
	<fmt:formatNumber value="123456789" type="currency"/><br><br>  <!-- 원표시 -->
	<fmt:formatNumber value="123456789" type="currency" currencySymbol="$"/><br><br>  <!-- $표시 -->
	
</body>
</html>