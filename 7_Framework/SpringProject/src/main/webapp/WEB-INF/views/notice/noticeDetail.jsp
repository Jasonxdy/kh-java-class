<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
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

			<div id="notice-area">

				<!-- Title -->
				<h1 class="mt-4">${notice.noticeTitle }</h1>
				

				<!-- Writer -->
				<p class="lead">
					작성자 : ${notice.noticeWriter}
				</p>

				<hr>

				<!-- Date -->
				<p>
					${notice.noticeModifyDate }
					<span class="float-right">조회수  ${notice.noticeCount }</span>
				</p>

				<hr>


				<!-- Content -->
				<div id="notice-content">${notice.noticeContent}</div>
				

				<hr>
				
				<div>
					<c:if test="${!empty loginMember && notice.noticeWriter == loginMember.memberId}" >
						<button class="btn btn-success float-right" id="deleteBtn">삭제</button>
						<a href="updateForm?no=${param.no}" class="btn btn-success float-right ml-1 mr-1">수정</a>
					</c:if>
					<a class="btn btn-primary float-right"
						href="
						<c:url value="list">
	                   		<c:if test="${!empty param.searchKey }">
				        		<c:param name="searchKey" value="${param.searchKey}"/>
				        	</c:if>
				        	
				        	<c:if test="${!empty param.searchValue }">
				        		<c:param name="searchValue" value="${param.searchValue}"/>
				        	</c:if>
	                   		<c:param name="currentPage" value="${param.currentPage}"/>
	                   	</c:url>" >목록으로
                   	</a>
				</div>
			</div>

			<jsp:include page="../common/footer.jsp"/>
		</div>
	</div>
	
	<script>
		$("#deleteBtn").on("click",function(){
			if(confirm("정말 삭제 하시겠습니까?")) location.href = "delete?no=${param.no}";
		});
	</script>
</body>
</html>
