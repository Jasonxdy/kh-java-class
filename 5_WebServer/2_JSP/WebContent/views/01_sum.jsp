<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1부터 200까지의 합 구하기</title>

</head>
<body>
    <%
        // 스크립틀릿(scriptlet) : 내부에 자바코드 작성 가능
        int sum = 0;
        for(int i = 1; i <=200; i++){
            sum += i;
        }
        System.out.println("덧셈 끝!"); // 이클립스 콘솔에 출력
    %>

    <h1>
        1부터 200까지의 합은 
        <span style="color:red; font-weight: bold">
            <%= sum %>
        </span>
        입니다.
    </h1>
</body>
</html>