<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
		<jsp:include page="../common/header.jsp"/>
		<jsp:include page="../common/nav.jsp"/>

		<div class="container">

			<h3>공지사항 수정</h3>
		      <hr>
		      <div class="bg-white rounded shadow-sm container py-3">
		        <form method="POST" action="update?no=${notice.noticeNo}" role="form" onsubmit="return validate();">
		          <div class="form-inline mb-2">
		            <div class="input-group">
		              <label class="input-group-addon mr-3">제목</label>
		              <input type="text" class="form-control" id="title" name="noticeTitle" size="70" value="${notice.noticeTitle }">
		            </div>
		          </div>
		
		          <div class="form-inline mb-2">
		            <div class="input-group">
		              <label class="input-group-addon mr-3">작성자</label>
		              <h5 class="my-0" id="writer">${notice.noticeWriter}</h5>
		            </div>
		          </div>
		
		
		          <div class="form-inline mb-2">
		            <div class="input-group">
		              <label class="input-group-addon mr-3">작성일</label>
		              <h5 class="my-0" id="today">${notice.noticeModifyDate }</h5>
		            </div>
		          </div>
		
		          <hr>
		
		          <div class="form-group">
		            <div><label for="content">내용</label> </div>
		            <textarea class="form-control" id="content" name="noticeContent" rows="10" 
		            style="resize: none;">${notice.noticeContent}</textarea>
		          </div>
		
		
		        <hr class="mb-4">
		
		        <div class="text-center">
					<button type="submit" class="btn btn-primary">수정</button>
					<a href="detail?no=${notice.noticeNo}" class="btn btn-primary">취소</a>
				</div>
		        
		        </form>
		      </div>

			<jsp:include page="../common/footer.jsp"/>	
		</div>
	</div>
	
	<script>
	
		function validate() {
			if($("#title").val() == ""){
				alert("제목을 입력해주세요");
				return false;
			} else if ($("#content").val() == ""){
				alert("내용을 입력해주세요");
				return false;
			}
		}

		
	</script>
</body>
</html>
