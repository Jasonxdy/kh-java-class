<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세 조회</title>
</head>
<body>
	<div class="container">
	
		<jsp:include page="../common/header.jsp"/>
		<jsp:include page="../common/nav.jsp"/>
		
		<div class="container">

			<div id="notice-area">

				<!-- Title -->
				<h1 class="mt-4">${notice.noticeTitle}</h1>

				<!-- Writer -->
				<p class="lead">
					
					작성자 : ${notice.noticeWriter}
				</p>

				<hr>

				<!-- Date -->
				<p>
					${notice.noticeModifyDate}
					 <span class="float-right">조회수  ${notice.noticeCount}</span>
				</p>

				<hr>


				<!-- Content -->
				<div id="notice-content"> ${notice.noticeContent} </div>
				

				<hr>
				
				<div>
					
					<c:url var="updateForm" value="updateForm">
						<c:if test="${!empty param.searchKey }">
							<c:param name="searchKey" value="${param.searchKey}" />
						</c:if>
						<c:if test="${!empty param.searchValue }">
							<c:param name="searchValue" value="${param.searchValue}" />
						</c:if>
						<c:param name="currentPage" value="${param.currentPage}" />
						<c:param name="no" value="${param.no}"></c:param>
					</c:url>
					
					<c:if test="${!empty loginMember && loginMember.memberId == notice.noticeWriter}">
						<button class="btn btn-primary float-right" id="deleteBtn" onclick="confirmDelete();">삭제</button>
						<a href="${updateForm}" class="btn btn-primary float-right ml-1 mr-1">수정</a>
					</c:if>

					<c:url var="backToList" value="list">
						<c:if test="${!empty param.searchKey }">
							<c:param name="searchKey" value="${param.searchKey}" />
						</c:if>
						<c:if test="${!empty param.searchValue }">
							<c:param name="searchValue" value="${param.searchValue}" />
						</c:if>
						<c:param name="currentPage" value="${param.currentPage}" />
					</c:url>

					<a class="btn btn-primary float-right" 
					  	href="${backToList}">
					목록으로</a>
				</div>
			</div>
		</div>
		
	</div>
	
	
	<script>
			
		function confirmDelete() {
			if(confirm("정말 삭제하시겠습니까?")) {location.href="delete?no=${param.no}";}
		}

	</script>
	
</body>
</html>