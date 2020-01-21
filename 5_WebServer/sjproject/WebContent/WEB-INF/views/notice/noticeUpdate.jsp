<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.kh.sjproject.notice.model.vo.Notice"%>
<% 
	Notice notice = (Notice)request.getAttribute("notice");
%>
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

			<h3>공지사항 수정</h3>
		      <hr>
		      <div class="bg-white rounded shadow-sm container py-3">
		        <form method="POST" action="update?no=<%= notice.getNoticeNo()%>" role="form" onsubmit="return validate();">
		          <div class="form-inline mb-2">
		            <div class="input-group">
		              <label class="input-group-addon mr-3">제목</label>
		              <input type="text" class="form-control" id="title" name="title" size="70" value="<%= notice.getNoticeTitle()%>">
		            </div>
		          </div>
		
		          <div class="form-inline mb-2">
		            <div class="input-group">
		              <label class="input-group-addon mr-3">작성자</label>
		              <h5 class="my-0" id="writer"><%= notice.getNoticeWriter() %></h5>
		            </div>
		          </div>
		
		
		          <div class="form-inline mb-2">
		            <div class="input-group">
		              <label class="input-group-addon mr-3">작성일</label>
		              <h5 class="my-0" id="today"><%= notice.getNoticeModifyDt() %></h5>
		            </div>
		          </div>
		
		          <hr>
		
		          <div class="form-group">
		            <div><label for="content">내용</label> </div>
		            <textarea class="form-control" id="content" name="content" rows="10" 
		            style="resize: none;"><%= notice.getNoticeContent() %></textarea>
		          </div>
		
		
		        <hr class="mb-4">
		
		        <div class="text-center">
					<button type="submit" class="btn btn-primary">수정</button>
					<a href="detail?no=<%=notice.getNoticeNo() %>" class="btn btn-primary">취소</a>
				</div>
		        
		        </form>
		      </div>

			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
	
	<script>

		
	</script>
</body>
</html>
