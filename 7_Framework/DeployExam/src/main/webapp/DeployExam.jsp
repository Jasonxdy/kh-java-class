<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function show() {
		var value = document.getElementById("btn").innerText;
		alert(value);
	}
</script>
</head>
<body>
	<button id="btn" onclick="show();">Already Clicked!</button>
</body>
</html>