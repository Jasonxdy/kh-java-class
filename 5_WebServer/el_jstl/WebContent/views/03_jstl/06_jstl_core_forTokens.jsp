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

	<c:set var="device1" scope="request">
		컴퓨터,핸드폰,TV,냉장고,에어컨,세탁기
	</c:set>
	
	<c:set var="device2" scope="request">
		컴퓨터.핸드폰,TV/냉장고.에어컨,세탁기
	</c:set>
	
	<jsp:forward page="06_jstl_core_forTokensEnd.jsp"/>

</body>
</html>