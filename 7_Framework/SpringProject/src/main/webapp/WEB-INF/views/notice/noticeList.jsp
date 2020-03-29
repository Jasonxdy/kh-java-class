<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
    <style>
    	.pagination {
            justify-content: center;
        }
        #searchForm{
            position: relative;
        }

        #searchForm>*{
            top : 0;
        }
	</style>
	
</head>
<body>
	<div class="container">
		<jsp:include page="../common/header.jsp"/>
		<jsp:include page="../common/nav.jsp"/>

		<div class="container">
	        <div>
	            <table class="table table-hover table-striped" id="list-table">
	                <thead>
	                    <tr>
	                        <th>글번호 </th>
	                        <th>제목</th>
	                        <th>작성자</th>
	                        <th>조회수</th>
	                        <th>작성일</th>
	                    </tr>
	                </thead>
	                <tbody>
						<c:if test="${empty list }">
							<tr>
								<td colspan="5">존재하는 공지사항이 없습니다.</td>
							</tr>
						</c:if>
						<c:if test="${!empty list }">
							<c:forEach var="notice" items="${list}" varStatus="vs">
								<tr>
									<td>${notice.noticeNo}</td>
									<td>${notice.noticeTitle}</td>
									<td>${notice.noticeWriter}</td>
									<td>${notice.noticeCount}</td>
									<td>${notice.noticeModifyDate}</td>
								</tr>
							</c:forEach>
						</c:if>
	                </tbody>
	            </table>
	        </div>
	
	        <hr>
	        
	        <%-- 로그인된 계정이 관리자 등급인 경우 --%>
	        <c:if test="${ !empty loginMember && loginMember.memberGrade == 'A' }">
	        	<button type="button" class="btn btn-success float-right" id="insertBtn" 
	        		onclick="location.href = 'insertForm';">글쓰기</button>
	        </c:if>
	        
  	       	<!--------------------------------- 페이징바  ---------------------------------->
			<div style="clear: both;">
	            <ul class="pagination">
	            	<c:if test="${pInf.currentPage > 1}">
		                <li>
		                	<!-- 맨 처음으로(<<) -->
	                    	<!-- 
	                    		c:url 태그에 var 속성이 존재하지 않으면
	                    		변수처럼 사용되는 것이 아닌, 작성된 자리에 바로 URL 형식으로 표기됨
	                    	-->
		                    <a class="page-link text-success" 
		                    	href=" 
		                    	<c:url value="list">
		                    		<c:if test="${!empty param.searchKey }">
						        		<c:param name="searchKey" value="${param.searchKey}"/>
						        	</c:if>
						        	
						        	<c:if test="${!empty param.searchValue }">
						        		<c:param name="searchValue" value="${param.searchValue}"/>
						        	</c:if>
		                    		<c:param name="currentPage" value="1"/>
		                    	</c:url>
	                    	">
			                    &lt;&lt;
			                </a>
		                </li>
		                
		                <li>
		                	<!-- 이전으로(<) -->
	                   		<a class="page-link text-success" 
		                    	href=" 
		                    	<c:url value="list">
		                    		<c:if test="${!empty param.searchKey }">
						        		<c:param name="searchKey" value="${param.searchKey}"/>
						        	</c:if>
						        	
						        	<c:if test="${!empty param.searchValue }">
						        		<c:param name="searchValue" value="${param.searchValue}"/>
						        	</c:if>
		                    		<c:param name="currentPage" value="${pInf.currentPage-1}"/>
		                    	</c:url>
	                    	">
			                    &lt;
			                </a>
		                </li>
	                </c:if>
	                
	                <!-- 10개의 페이지 목록 -->
	                <c:forEach var="p" begin="${pInf.startPage}" end="${pInf.endPage}">
	                
	                
	                	<c:if test="${p == pInf.currentPage}">
			                <li>
			                    <a class="page-link">${p}</a>
			                </li>
		                </c:if>
	                	
	                	<c:if test="${p != pInf.currentPage}">
	                		<li>
		                    	<a class="page-link text-success" 
			                    	href=" 
			                    	<c:url value="list">
			                    		<c:if test="${!empty param.searchKey }">
							        		<c:param name="searchKey" value="${param.searchKey}"/>
							        	</c:if>
							        	
							        	<c:if test="${!empty param.searchValue }">
							        		<c:param name="searchValue" value="${param.searchValue}"/>
							        	</c:if>
			                    		<c:param name="currentPage" value="${p}"/>
			                    	</c:url>
		                    	">
				                    ${p}
				                </a>
		                	</li>
	                	</c:if>
	                	
                	</c:forEach>
	                
	                <!-- 다음 페이지로(>) -->
	                <c:if test="${pInf.currentPage < pInf.maxPage }">
		                <li>
							<a class="page-link text-success" 
		                    	href=" 
		                    	<c:url value="list">
		                    		<c:if test="${!empty param.searchKey }">
						        		<c:param name="searchKey" value="${param.searchKey}"/>
						        	</c:if>
						        	
						        	<c:if test="${!empty param.searchValue }">
						        		<c:param name="searchValue" value="${param.searchValue}"/>
						        	</c:if>
		                    		<c:param name="currentPage" value="${pInf.currentPage+1}"/>
		                    	</c:url>
	                    	">
			                    &gt;
			                </a>
		                </li>
		                
		                <!-- 맨 끝으로(>>) -->
		                <li>
		                    <a class="page-link text-success" 
		                    	href=" 
		                    	<c:url value="list">
		                    		<c:if test="${!empty param.searchKey }">
						        		<c:param name="searchKey" value="${param.searchKey}"/>
						        	</c:if>
						        	<c:if test="${!empty param.searchValue }">
						        		<c:param name="searchValue" value="${param.searchValue}"/>
						        	</c:if>
		                    		<c:param name="currentPage" value="${pInf.maxPage}"/>
		                    	</c:url>
	                    	">
			                    &gt;&gt;
			                </a>
		                </li>
	                
	                </c:if>
	            </ul>
	        </div>
	        
	        <div>
	            <form action="list" method="GET" class="text-center" id="searchForm">
	                <select name="searchKey" class="form-control" style="width:100px; display: inline-block;">
	                    <!-- <option value="title" selected>글제목</option> -->
	                    <option value="title">글제목</option>
	                    <option value="content">내용</option>
	                    <option value="titcont">제목+내용</option>
	                </select>
	                <input type="text" name="searchValue" class="form-control" style="width:25%; display: inline-block;">
	                <button class="form-control btn btn-success" style="width:100px; display: inline-block;">검색</button>
	            </form>
	            
            	<script>
            		// 페이지 이동 후에도 검색 결과가 검색창 input 태그에 표시되도록 하는 script
					$(function(){
						var searchKey = "${param.searchKey}";
						var searchValue = "${param.searchValue}";
						
						if(searchKey != "null" && searchValue != "null"){
							$.each($("select[name=searchKey] > option"), function(index, item){
								if($(item).val() == searchKey){
									$(item).prop("selected","true");
								} 
							});
							
							$("input[name=searchValue]").val(searchValue);
						}
							
					});
				</script>
	            
	        </div>
    	</div>
		<jsp:include page="../common/footer.jsp"/>
	</div>
	
	<script>
		// 공지사항 상세보기 기능 (jquery를 통해 작업)
		$(function(){
			$("#list-table td").click(function(){
				var noticeNo = $(this).parent().children().eq(0).text();
				// 쿼리스트링을 이용하여 get 방식으로 글 번호를 server로 전달
				<c:url var="detailUrl" value="detail">
              		<c:if test="${!empty param.searchKey }">
	        		<c:param name="searchKey" value="${param.searchKey}"/>
		        	</c:if>
		        	<c:if test="${!empty param.searchValue }">
		        		<c:param name="searchValue" value="${param.searchValue}"/>
		        	</c:if>
		        	
                 	<c:param name="currentPage" value="${pInf.currentPage}"/>
               	</c:url>
				
				location.href="${detailUrl}&no="+noticeNo;
			
			}).mouseenter(function(){
				$(this).parent().css("cursor", "pointer");
			
			});
			
		});
		
		
	</script>
	
	
	
</body>
</html>
