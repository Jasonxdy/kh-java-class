<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jQuery 방식의 Ajax</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!-- from google developers -->
<script type="text/javascript" src="<%= request.getContextPath() %>/js/JQueryAjax.js "></script>

</head>
<body>

	<h3>1. 버튼 클릭 시 GET 방식으로 서버에 데이터 전송 및 응답</h3>
	입력 : <input type="text" id="input1">
	<button id="btn1">GET 방식 전송</button><br>
	응답 : <input type="text" id="output1" style="width:300px" readonly>
	
	<hr>
	
	<h3>2. 버튼 클릭 시 POST 방식으로 서버에 데이터 전송 및 응답</h3>
	입력 : <input type="text" id="input2">
	<button id="btn2">POST 방식 전송</button><br>
	응답 : <input type="text" id="output2" style="width:300px" readonly>
	
	
	

</body>
</html>