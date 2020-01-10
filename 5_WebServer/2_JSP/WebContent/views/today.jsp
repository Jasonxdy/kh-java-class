<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
    
<% 
	Date now = new Date();
	String today = String.format("%tY년 %tm월 %td일 %tA",
			now, now, now, now);


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<%= today %>
</body>
</html>