<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Action Tag</title>
</head>
<body>

	<h3>JSP Action Tag</h3>
	<p>
		JSP Action Tag는 XML 기술을 이용하여 <br>
		기존의 JSP 문법을 확장하는 메커니즘을 제공하는 태그 <br><br>
		
		JSP Action Tag는 웹 브라우저에서 실행되는 것이 아니라, <br>
		웹 컨테이너에서 실행되고 결과만 브라우저로 보냄.
	</p>
	
	
	<hr>
	
	<h3>JSP Action Tag include (동적 include 방식)</h3>
	<p>
		&lt;jsp:include page="경로"&gt;<br>
			-> 부모 JSP 파일이 화면에 출력되는 시점에 삽입되어짐.<br>
		
		문제점 : include된 페이지에서 scriptlet 상에 선언된 변수를 같이 사용할 수 없음<br><br>
		
		특징 : 현재 페이지와 include된 페이지의 변수명 중복이 발생하지 않음. + include된 페이지로 값을 전달할 수 있는 방법이 존재 (동적이지만 정적인 특징을 가지게 할수도 있음)<br><br>
		
		<%-- <jsp:include page="includePage.jsp"/> --%>
		<jsp:include page="includePage.jsp">
			<jsp:param value="Galaxy Fold" name="pName"/>
		</jsp:include>
		
		<br>
		
		<% String year = "2030년"; %>
		year : <%= year %>
		
		
		
		
	</p>



</body>
</html>