<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.kh.el.model.vo.Person"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL - operation</title>
</head>
<body>

	<h2>EL - 연산</h2>
	<p>
		원래 EL을 가지고 어떤 연산을 처리하지는 않음. <br>
		EL의 본 목적은 속성값을 화면에 출력하기 위한 용도임.<br>
		
		하지만, 간단한 산술연산과 논리연산을 지원함.
	</p>
	
	
	<h3>산술 연산</h3>
	10 더하기 5 = ${10 + 5} <br>
	10 빼기 5 = ${10 - 5} <br>
	10 곱하기 5 = ${10 * 5} <br>
	10 나누기 5 = ${10 / 5} 또는 ${10 div 5} <br>
	10 나머지 7 = ${10 % 7} 또는 ${10 mod 7} <br>
	
	<hr>
	
	<h3>객체 비교 연산</h3>
	<%
		String str1 = "점심시간"; // String pool에 생성
		String str2 = new String("점심시간"); // Heap 영역에 생성 ;;
		
		// Person 객체 생성
		Person person1 = new Person("홍길동", 20, '남');
		Person person2 = new Person("홍길동", 20, '남');
		
		
		// EL을 이용하여 선언된 변수에 접근할 수 있도록 pageScope에 담기
		pageContext.setAttribute("str1", str1);
		pageContext.setAttribute("str2", str2);
		pageContext.setAttribute("person1", person1);
		pageContext.setAttribute("person2", person2);
		
	%>
	
	
	<table border="1">
		<tr>
			<th>비교식</th>
			<th>스크립팅(표현식)</th>
			<th>EL</th>
		</tr>
		
		<tr>
			<td>str1 == str2</td>
			<td><%= str1 == str2 %></td> <!-- false -->
			<td>${str1 == str2} 또는 ${str1 eq str2}</td> <!-- true -->
			<!-- el의 == 연산은 equals()와 같은 동작을 함 -->
		</tr>
		
		<tr>
			<td>str1 != str2</td>
			<td><%= str1 != str2 %></td> <!-- true -->
			<td>${str1 != str2} 또는 ${str1 ne str2}</td> <!-- false -->
			<!-- el의 == 연산은 equals()와 같은 동작을 함 -->
		</tr>
		
		<tr>
			<td>person1 == person2</td>
			<td><%= person1 == person2 %></td> <!-- false -->
			<td>${person1 == person2} 또는 ${person1 eq person2}</td> <!-- true -->
			<!-- el의 == 연산은 equals()와 같은 동작을 함 -->
		</tr>
	
		<tr>
			<td>person1 != person2</td>
			<td><%= person1 != person2 %></td> <!-- true -->
			<td>${person1 != person2} 또는 ${person1 ne person2}</td> <!-- false -->
			<!-- el의 == 연산은 equals()와 같은 동작을 함 -->
		</tr>
	</table>
	
	<hr>
	
	<h3>숫자형 자동 형변환</h3>
	<%
		pageContext.setAttribute("big", 10);
		pageContext.setAttribute("small", 3);
		
	%>
	
	<%-- 스크립팅 (표현식) : <%= Integer.parseInt(pageContext.getAttribute("big")) + Integer.parseInt(pageContext.getAttribute("small")) %> --%>
	<!-- Object 타입이라 연산 불가 -->
	
	el : ${big + small } // 잘나옴! <br>
	<!-- el은 scope에 속성으로 담겨져 있는 Object 타입의 값이 숫자형인 경우 자동으로 인식하여 형변환을 진행한 후 연산을 처리함. -->
	<!-- oracle도 문자열로 저장된 숫자는 자동으로 형변환 해주듯이 같은 기능임 -->
	
	<hr>
	
	<h3>객체가 null 또는 비어있는지 확인하는 방법</h3>
	<%
		List<String> list = new ArrayList<String>();
		pageContext.setAttribute("list", list);
	%>
	
	\${empty list} = ${empty list}<br>
	
	논리연산
	&& and 
	|| or
	
	논리 부정 연산
	!
	
	
	

</body>
</html>