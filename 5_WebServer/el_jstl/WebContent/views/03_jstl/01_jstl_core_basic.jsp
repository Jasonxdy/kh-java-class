<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- c: 코어를 뜻함, ../jsp/jstl/core를 입력해야함 (자동완성 2개중에) -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL - core tag</title>
</head>
<body>
	<h3>JSTL 라이브러리 등록 방법</h3>
	<p>
		1) http://tomcat.apache.org/ 접속 <br>
		2) 좌측 Download -> Taglibs 클릭 <br>
		3) Standard-1.2.5 -> jar files -> 4개 다 다운로드 <br>
		4) WebContent/WEB-INF/lib 폴더에 추가 <br>
	</p>
	
	<hr>
	
	<h3>JSTL 선언 방법</h3>
	<pre>
	JSTL을 사용하고자하는 JSP 페이지의 상단에서 page 지시자 아래에 taglib 지시자를 작성.
	
	&lt;%@ taglib prefix="접두사" uri="tld 파일상의 uri" %&gt;
	
	prefix : 접두사. 다른 태그와 구별할 수 있는 namespace를 제공.
	uri(Uniform Resource Identifier)
		: 자원을 나타내는 '유일한' 주소 (식별자)
		실제 웹 주소 (= url)가 아닌 태그 라이브러리를 나타내는 식별자.
		tld 파일상의 uri 값을 가리키며, 이 지시자를 통해 작성한 태그 이름과 매칭되는 자바코드를 찾음.
		
		* tld (Tag Library Descriptor) 파일
			: 커스텀 태그 정보를 갖고 있는 라이브러리 파일
	</pre>
	
	<hr>
	
	<h3>JSTL Core Library</h3>
	변수와 url, 조건문, 반복문 등의 로직과 관련된 JSTL 문법을 제공. <br><br>
	
	<h3>1. 변수의 선언 - &lt;c:set&gt;</h3>
	<pre>
		- 변수를 선언하고 초기값을 대입하는 기능을 가진 태그
		- 변수를 선언할 때 scope를 지정할 수 있음.
		- 사용 방법
			1) 변수의 타입은 별도로 선언하지 않는다.
			2) 초기값을 반드시 기술한다.
			3) &lt;c:set&gt;으로 선언한 변수는 EL에서 사용할 수 있다.
			       하지만, scripting 요소에서는 사용할 수 없다.
	</pre>
	
	<c:set var="no1" value="${param.num1}"/>
	<c:set var="no2" value="${param.num2}" scope="request"/>
	<c:set var="result" value ="${no1 * no2}" scope="session"/>
	<!-- 기본값은 page, scope 작성시 영역 지정도 가능 -->
	
	${no1 } * ${no2 }의 값은 ${result}입니다.<br><br>
	
	scope로 표현<br>
	${pageScope.no1} * ${requestScope.no2}의 값은 ${sessionScope.result}입니다.
	
	<hr>
	
	<h3>2. 변수의 삭제 &lt;c:remove&gt;</h3>
	지정한 변수를 모든 scope에서 검색해 삭제함. <br>
	또는 지정한 scope만 삭제도 가능함.<br>
	
	<c:set var="result" value="9999" scope="request"/>
	삭제 전 : ${result} <br>
	삭제 전 (request) : ${requestScope.result} <br>
	삭제 전 (session) : ${sessionScope.result} <br><br> 
	
	1) 특정 scope에서만 제거<br>
	<c:remove var="result" scope="request"/>
	
	삭제 후 : ${result} <br>
	삭제 후 (request) : ${requestScope.result} <br><br>
	
	
	2) 모든 scope에서 제거<br>
	<c:remove var="result"/>
	삭제 후 : ${result} <br>
	
	<hr>
	
	<h3>3. 변수의 출력 - &lt;c:out></h3>
	
	&lt;c:out>태그는 데이터를 출력할 때 <br>
	&lt;, &gt;, &nbsp; &amp;, \$ 등의 특수문자를 <br>
	자동으로 이스케이프 시퀀스로 바꿔줌.<br>
	
	<c:out value="<h4> c:out 테스트입니다.</h4>"/> <br><br>
	
	* escapeXml="false" -> 특수문자를 그대로 인식함. <br>
	<c:out value="<h4> c:out 테스트입니다.</h4>" escapeXml="false"/> <br>
	
	
	
	
	
	
	
	
</body>
</html>