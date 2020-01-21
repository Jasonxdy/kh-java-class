<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kh.sjproject.notice.model.vo.Notice"%>
<% Notice notice = (Notice)request.getAttribute("notice");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<style>
	#notice-area{ margin-bottom:200px;}
	#notice-content{ padding-bottom:150px;}
</style>
</head>
<body>
	<div class="container">
		<%@ include file="../common/header.jsp"%>
		<%@ include file="../common/nav.jsp"%>

		<div class="container">

			<div id="notice-area">

				<!-- Title -->
				<h1 class="mt-4"><%=notice.getNoticeTitle() %></h1>

				<!-- Writer -->
				<p class="lead">
					<%-- 작성자 : <a href="#"><%=notice.getNoticeWriter() %></a> --%>
					작성자 : <%=notice.getNoticeWriter() %>
				</p>

				<hr>

				<!-- Date -->
				<p>
					<%=notice.getNoticeModifyDt() %>
					 <span class="float-right">조회수  <%=notice.getNoticeCount() %></span>
				</p>

				<hr>


				<!-- Content -->
				<div id="notice-content"> <%=notice.getNoticeContent() %> </div>
				

				<hr>
				
				<div>
					<% if(loginMember != null && (notice.getNoticeWriter().equals(loginMember.getMemberId()))) {%>
					<button class="btn btn-primary float-right" id="deleteBtn">삭제</button>
					<a href="updateForm?no=<%=notice.getNoticeNo() %>" class="btn btn-primary float-right ml-1 mr-1">수정</a>
					<% } %>
					
					<a href="list" class="btn btn-primary float-right">목록으로</a>
				</div>
			</div>

			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
	
	<script>
		$("#deleteBtn").on("click",function(){
			if(confirm("정말 삭제 하시겠습니까?")) location.href = "delete?no=<%=notice.getNoticeNo() %>";
		});
	</script>
</body>
</html>
