<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Action Tag</title>
</head>
<body>
	<h3>Scripting include (정적 include 방식)</h3>
	
	<p>
	
		&lt;%@ include file="경로" %&gt; <br>
		 -> 부모 JSP 파일이 컴파일 되기 전에 페이지에 삽입됨. // 전부 삽입된 후 하나의 java 파일이 되어 컴파일 됨 <br><br>
		 특징 : include 된 페이지에서 scriptlet 상에 선언된 변수를 그대로 사용할 수 있다. // 하나의 java 파일이 되기 때문
	</p>
	
	<hr>
	
	<%@ include file = "includePage.jsp" %>
	
	<br><br>
	
	include된 페이지의 year 값 : <%= year %><br><br>
	
	문제점 : 현재 페이지와 include한 페이지의 변수명 중복 문제가 발생함. <br>
			(협업 시 잦은 유지보수가 일어남)
			
		<% 
//		String year = "year";
// 			-> 중복으로 인해 error 발생 
		%>
	
	
	

</body>
</html>