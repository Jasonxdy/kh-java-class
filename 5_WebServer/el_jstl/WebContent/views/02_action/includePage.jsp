<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.Date" %>

<% String year = String.format("%tY", new Date()); %>

copyright 2005 - <%= year %> 이 웹페이지의 저작권은 KH 정보교육원에 있습니다.<br>


<% String pName = request.getParameter("pName"); %>
pName : <%= pName %> <br>
pName(el) : ${param.pName}


