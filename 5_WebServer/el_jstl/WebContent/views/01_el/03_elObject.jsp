<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL - Object</title>
</head>
<body>

	<h3>그 밖의 EL 내장객체 : cookie, initParam, header, pageContext</h3>
	
	<h3>cookie</h3>
	
	쿠키에서 가져온 saveId 값 : ${cookie.saveId.value} <br>
	쿠키에서 가져온 세션 ID 값 : ${cookie.JSESSIONID.value} <br>
	
	존재하지 않는 쿠키 값(savePwd) : ${cookie.savePwd.value} <br>
	
	<!--
		
		EL의 장점 중 하나는 EL을 내부적으로 처리하는 도중에 
		NullPointerException을 발생시키지 않는다는 것임.
		
		속성/프로퍼티/키값이 null이더라도 예외를 발생시키지 않고 아무것도 출력하지 않음.
		
		단, 산술연산에서 null은 0으로 처리
		논리 연산에서는 false로 처리됨.
		
	 -->
	 
	 
	 <hr>
	 
	 <h3>컨텍스트 초기화 파라미터 : initParam</h3>
	 <p>
	 	web.xml에 설정한 초기화 파라미터를 가져옴. <br>
	 	단, 서블릿 초기화 파라미터는 아님
	 </p>
	 
	 컨텍스트 파라미터 : ${initParam.email }<br>
	 컨텍스트 파라미터 (표현식) : <%= application.getInitParameter("email") %>
	 
	<hr>
	
	<h3>기타 request 객체의 메소드를 EL로 조회하기</h3>
	
	<p>
		전송방식(method) 조회
	</p>
	method(표현식) : <%= request.getMethod() %><br>
	method(el) : ${ request.method }<br>
	method(el) : ${ requestScope.method }<br>
	method(el) : ${pageContext.request.method} <br>
	
	<!-- 
		EL은 실제 request라는 JSP 내장 객체에 직접 접근하는게 아님
		
		requestScope라는 request 생존범위에 묶여있는 속성에 대한 단순한 맵구조를 제공하는 객체
		// request라는 실제 내장객체는 따로 있고, 그 안에 requestScope라는 파라미터를 다루는 객체가 있는 것
		
		실제 JSP 내장객체인 request에 접근하기 위해서는 pageContext를 통해 접근해야 함.
		
		pageContext가 가진 접근자
		* getErrorData()
		* getRequest() // request 얻어오기
		* getResponse() // response 얻어오기
		* getPage()
		* getServletConfig()
		* getServletContext() // application 얻어오기
		* getSession() // session 얻어오긴
		
	 -->
	
	

</body>
</html>