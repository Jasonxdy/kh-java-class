<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL - core tag (url)</title>
</head>
<body>

	<h3>&lt;c:url> 태그</h3>
	<p>
		- url 경로를 생성하고, 해당 url의 param 속성을 선언하여 쿼리스트링을 정의할 수 있는 태그<br>
		- 해당 태그를 통해 url 경로와 관련 쿼리스트링의 값을 미리 설정하여 이를 제어할 수 있다.
	</p>

	<c:url var="url" value="07_jstl_core_urlEnd.jsp">
		<c:param name="pName" value="LG gram 2020"/>
		<c:param name="pPrice" value="2000000"/>
		<c:param name="option" value="RAM_32G"/>
		<c:param name="option" value="SSD_1TB"/>
	</c:url>
	
	<br>
	
	<a href="${url}" style="text-decoration:none; color:orange;">07_jstl_core_urlEnd.jsp 이동</a>

</body>
</html>