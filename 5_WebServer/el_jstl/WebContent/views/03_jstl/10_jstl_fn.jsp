<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL Function Tag</title>
</head>
<body>
	<h2>fn : 문자열 관련 함수 태그</h2>
	문자열 처리에 관한 메소드들을 EL 형식에서 사용할 수 있게 제공<br>
	
	<c:set var="str" value="How Are You?" scope="request"/>
	str : ${str}<br>
	모두 대문자 : ${fn:toUpperCase(str)}<br>
	모두 소문자 : ${fn:toLowerCase(str)}<br>
	문자열 길이 : ${fn:length(str) }<br>
	You라는 단어의 시작 인덱스 : ${fn:indexOf(str,"You")} <br>
	? (물음표) -> ! (느낌표) 변경 : ${fn:replace(str, "?", "!!!")} <br>
	replace 후 str : ${str} <br>
	
	
	
</body>
</html>