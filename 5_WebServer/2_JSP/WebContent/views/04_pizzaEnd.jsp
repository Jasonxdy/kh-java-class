<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
    
<%

String pizza = (String)request.getAttribute("pizza");
String sides = (String)request.getAttribute("sides");
String toppings = String.join(", " ,(String[])request.getAttribute("toppings"));
/* String toppings = (String)request.getAttribute("toppings"); */
/* Servlet에서 비즈니스 로직 (String.join(", ", toppings)을 처리한 경우 */
int price = (int)request.getAttribute("price");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 내역</title>
</head>
<body>
<h1>주문 내역</h1>
<h3>
피자는 <span style="color:red;"><%= pizza %></span>, 토핑은 <span style="color:green;"><%= toppings %></span>, 사이드는 <span style="color:blue;"><%= sides %></span> 주문하셨습니다.
<br>
<br>
<br>
<br>
총합 : <u><%= price %></u> 
</h3>

<h1 style="color : pink;">
즐거운 식사시간 되세요~
</h1>

</body>
</html>