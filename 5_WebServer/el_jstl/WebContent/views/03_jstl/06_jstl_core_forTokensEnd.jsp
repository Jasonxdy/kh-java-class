<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL - core tag (forTokens)</title>
</head>
<body>

	<h3> &lt;c:forTokens> 태그</h3>
	<p>
		- 문자열에 포함된 구분자를 통해 토큰을 분리해서 반복 처리하는 태그 <br>
		- items 속성에 구분자를 포함하고 있는 문자열을 지정하고, <br>
		  delims 속성에 토큰을 분리하는데 사용할 구분자(구획문자)를 지정.
	</p>
	
	<h3>device1</h3>
	<c:forTokens var="dev" items="${device1}" delims=",">
		<h4>${dev}</h4>
	</c:forTokens>

	<hr>

	<h3>device2</h3>
	<c:forTokens var="dev" items="${device2}" delims=",./">
		<h4>${dev}</h4>
	</c:forTokens>



</body>
</html>