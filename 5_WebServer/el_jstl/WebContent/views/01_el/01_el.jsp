<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.el.model.vo.Person"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL</title>
</head>
<body>

	<h3>
		1. scriptlet, 표현식(expression)을 이용하여 request, session 객체에 저장된 속성 화면에 출력하기
	</h3>
	
	
	<%
	
		String classRoom = (String)request.getAttribute("classRoom");
		Person person = (Person)request.getAttribute("person");
		
		String academyName = (String)request.getSession().getAttribute("academyName");

	%>
	
	학원명 : <%= academyName %> <br>
	강의장 : <%= classRoom %> <br>
	<br>
	수강생 정보
	<ul>
		<li>이름 : <%= person.getName() %></li>
		<li>나이 : <%= person.getAge() %></li>
		<li>성별 : <%= person.getGender() %></li>
	</ul>
	
	
	<hr>
	
	<h3>EL 이란?</h3>
	<pre>
	Expression Language(표현 언어)라 하여 
	&lt;%= %&gt;(표현식), response.getWriter().print()와 같이 
	JSP에서 화면 출력에 사용되는 Java 코드를 간결하게 사용하는 방법.
	
	화면에 표현하고자 하는 코드를 \${  value }의 형식으로 표현하여 작성 가능.
	</pre>
	
	<h3>
		2. EL을 이용하여 request, session 객체에 저장된 속성 화면에 출력하기
	</h3>
	
	학원명 : ${academyName}<br>
	강의장 : ${classRoom} <br>
	<br>
	수강생 정보
	<ul>
		<li>이름 : ${person.name}</li>
		<li>나이 : ${person.age}</li>
		<li>성별 : ${person.gender}</li>
	</ul>
	
	
	<!-- 
		el은 scriptlet, 표현식과는 다르게 getter를 사용하지 않고 (person.getName()) 
		변수명을 직접 입력하면 해당 객체의 getter를 자동적으로 활용하여 변수에 저장된 값을 읽어옴.
		
		el은 request, session등 jsp 내장 객체를 구분하지 않아도 자동적으로 입력된 속성명(키값)을
		검색하여 존재하는 경우 값을 가져옴.
		
		el은 내장 객체에 저장된 속성을 사용할 경우 jsp 표현식처럼 scriptlet으로 미리 선언하지 않아도
		바로 내장 객체에 접근할 수 있다.
	 -->
	 
	 
	 <hr>
	 
	 <h3>
	 	3. EL 사용 시 내장객체에 저장된 속성명이 같은 경우
	 </h3>
	 
	 <pre>
	1. page 영역 (또는 scope)
	 - 하나의 페이지 (JSP) 내에서만 객체 공유
	 	-> forward된 페이지에서는 지정된 변수를 사용할 수 없다. (덮어쓰기 되므로)
	 - page 영역을 다루기 위해서는 pageContext라는 JSP 내장 객체를 사용해야 함. // page는 지시자 (맨위에)랑 이름도 겹치므로.. 
	 
	2. request 영역
	 - 요청을 하는 페이지 + 요청을 받는 (응답) 페이지에서 객체 공유
	 - forward시 객체가 공유가 됨.
	 
	 - Servlet -> jsp로 객체 공유하는 방법
 	  --> request.setAttribute("속성명", 객체); // 전달
 	  
 	  --> Object obj = request.getAttribute("속성명");
	 
	3. session 영역
	 - 하나의 브라우저당 1개의 session 객체가 생성되며 브라우저 내에서 객체를 공유함. (크롬에 있는게 아니고 서버에서 1개 할당하여 관리?)
	 - 브라우저가 종료되거나, session이 만료되면 반환됨
	 
	 - request.getSession() 메소드를 호출하여 session 객체를 얻어옴.
	 
	4. application 영역
	 - 하나의 웹 어플리케이션 당 하나의 application 내장 객체가 생성됨.
	 
	 - 웹 어플리케이션이 종료되기 전까지 모든 페이지에서 객체 공유 가능.
	 
	 - request.getServletContext() 메소드를 호출하여 application 영역 객체를 얻어옴.
	 
	 
	 
	 ** 모든 내장객체들은 setAttribute("속성명", 객체)로 세팅하고, getAttribute("속성명")으로 객체를 얻어올 수 있음. **
	 </pre>
	 
	<%
	 	// page scope -> 한페이지 내에서만 사용 가능하므로 servlet 단위에서 선언 불가
	 	pageContext.setAttribute("scope", "page 범위");
	 
	 %>
	 
	 
	 scope : ${ scope }<br><br>
	 EL은 page -> request -> session -> application 순으로 속성을 찾음.
	 
	 <hr>
	 
	 <h3>4. 특정 내장 객체에 저장된 속성 다루기</h3>
	 
	 pageScope : ${scope}  또는 ${pageScope.scope}<br>
	 requestScope : ${requestScope.scope}<br>
	 sessionScope : ${sessionScope.scope}<br>
	 applicationScope : ${applicationScope.scope}<br>
	 
	 


</body>
</html>