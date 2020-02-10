<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL - param</title>
</head>
<body>
	<pre>
	param : 전달된 파라미터 값을 얻어올 때 사용
	 == request.getParameter()
	 
	paramValues : 전달된 파라미터 값들을 배열로 받아올 때 사용
	 == request.getParameterValues()
	 
	 * jsp -> servlet -> jsp로 가야하는 경우 jsp->jsp로 바로 넘어갈 때 사용하면 편리함
	</pre>
	
	<h2>주문 내역</h2>
	상품명 : ${param.pName} <br>
	수량 : ${param.pCount} <br>
	
	옵션(param.option) : ${param.option} <br>
	<!-- 
		같은 name 속성의 input 태그가 여러개 있어도 (값이 배열로 전달되는 경우) 이런 경우에도 param을 통해서 접근 가능.
	 	하지만 첫 번째 (0번 인덱스) 값만 접근 가능.
	-->
	 
	 옵션 1 : ${paramValues.option[0]} <br>
	 옵션 2 : ${paramValues.option[1]} <br>
	

</body>
</html>