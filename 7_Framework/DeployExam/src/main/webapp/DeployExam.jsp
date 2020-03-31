<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<script type = "text/javascript">
function show() {
	var value = document.getElementById("btn").innerText;
	alert(value);
}
</script>
<body>
<button id="btn" onclick="show();">Already Clicked!</button>

</body>
</html>