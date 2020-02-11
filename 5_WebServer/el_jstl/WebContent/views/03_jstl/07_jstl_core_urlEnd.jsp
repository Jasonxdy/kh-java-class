<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL - core tag(url)</title>
</head>
<body>

	<p>
		상품명 : ${param.pName} <br>
		가격 : ${param.pPrice} <br>
		옵션1 : ${paramValues.option[0]} <br>
		옵션2 : ${paramValues.option[1]} <br>
	</p>
	
	

</body>
</html>