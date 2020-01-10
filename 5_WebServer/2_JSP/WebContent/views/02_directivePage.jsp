<!--
	JSP는 page 지시자 중 이 줄이 가장 먼저 해석이 되는 것이 좋기 때문에
	자동완성을 통해 class가 import 되는 경우 import 줄을 아래로 내려주는 것이 좋다
 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList" errorPage="error.jsp"%>
<!-- page 지시자 내부에 추가하여 선언하는 import 방법(1) -->
    
<!-- 페이지 지시자를 새로 선언해서 import하는 방법(2) -->
<%-- <%@ page import="java.util.ArrayList"%> --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이지 지시자 (import, errorPage)</title>
</head>
<body>
	<!-- HTML 주석 -->
	<%-- JSP 주석 --%>
	
	<%-- <h1>주석 처리 테스트</h1> --%>
	<!-- HTML 주석은 클라이언트에게 전달됨 -->
	<%-- JSP 주석은 클라이언트에게 전달되지 않는다 --%>

	<%
		ArrayList<String> list = new ArrayList<String>();
	
		list.add("HTML");
		list.add("Servlet");
		list.add("JSP");
		
		// 강제로 에러 요소 추가
		list.add(10, null);
		
		for(String str : list){
			System.out.println(str);
		}
	%>
	
	
	<h3>단순 출력</h3>
	<%= list.get(0) %><br>
	<%= list.get(1) %><br>
	<%= list.get(2) %><br>
	
	
	<h3>Scriptlet + expression + HTML</h3>
	<% for(String str : list){ %>
		<%= str %>
		<br>
	<% } %>
	<!-- 스크립틀릿 2개가 한 세트로서 for문 안에서 HTML이 반복된다..ㄷㄷ -->
	
	
	<h3>Scriptlet + expression + Javascript</h3>
	<button onclick="test();">확인하기</button>
	<p id="area"></p>
	
	<script>
		function test(){
			var area = document.getElementById("area");
			
			// 이전 내용 삭제
			area.innerHTML = "";
			
			<% for (String str : list) {%>
				area.innerHTML += "<%= str %><br>";
				/* Javascript의 String과 Java의 String이 다르게 인식되어 쌍따옴표 안에 넣어줘야지 
				인식이 가능하다!! */
			<% } %>
			
		}
	</script>
	
	
</body>
</html>